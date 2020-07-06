package pt.ual.android.bhjencryption.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.utils.StringUtils;

/**
 * Aqui, não se utilizou o alfabeto completo Português, visto que a tabela requer um número par de letras.
 * Aqui utiliza-se o alfabeto ASCII: abcdefghijklmnopqrstuvwxyz
 */
public class TheRightBattleshipCipher extends Cipher {

    public TheRightBattleshipCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate(boolean isEncrypt) {
        CipherValidationResult result = super.validate(isEncrypt);

        if(!result.hasErrors()) {
            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(getCipherMessage().getPasswordAsText() == null) // validate password
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(getCipherMessage().getPasswordAsText().length() != 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));

        if (!StringUtils.matchingChars(getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, false, false)) // validate password
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate(true);

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate(false);

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), new String(
                            TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD + TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD) + ".",
                    true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            if (!TheRightBattleshipCipherCoord.validateCoords(this.getCipherMessage().getMessageAsText().split(" "), false)) // Validar se contém coordenadas válidas
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        Map<String, String> encodingTable = buildEncodingTable(getCipherMessage().getPasswordAsText().toUpperCase(), false);
        String upperMessage = getCipherMessage().getMessageAsText().toUpperCase();
        String upperPassword = getCipherMessage().getPasswordAsText().toUpperCase();
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < upperMessage.length(); i++) {
            char ch = upperMessage.charAt(i);

            if(ch == ' ') // validar se a letra a cifrar é igual à pw e se for um espaço, fazer skip
                continue;

            if(ch == upperPassword.charAt(0))
                sbOutput.append(".");
            else
                sbOutput.append(encodingTable.get(String.valueOf(ch)));

            sbOutput.append(" ");
        }

        return new CipherResult(sbOutput.toString().trim());
    }

    @Override
    public CipherResult decrypt() {
        Map<String, String> encodingTable = buildEncodingTable(getCipherMessage().getPasswordAsText().toUpperCase(), true);
        String[] arrMessage = getCipherMessage().getMessageAsText().toUpperCase().split(" ");
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < arrMessage.length; i++) {
            if(arrMessage[i].compareTo(".") == 0)
                sbOutput.append(getCipherMessage().getPasswordAsText().toUpperCase());
            else
                sbOutput.append(encodingTable.get(arrMessage[i]));
        }

        return new CipherResult(sbOutput.toString());
    }

    /**
     * countAlpha % 5 - determinar a coluna
     * countAlpha / 5 - determinar a linha
     *
     * Criar uma tabela com base no alfabeto ASCII
     * @param password permite definir a partir de que letra a tabela deve começar. A letra imediatamente seguinte
     * @param isToDecrypt permite definir se o map é construído para cifrar ou decifrar
     * @return
     */
    private static Map<String, String> buildEncodingTable(String password, boolean isToDecrypt) {
        String upperAlpha = CipherUtils.ASCII_ALPHABET_LOWER.toUpperCase();
        Map<String, String> encodingTable = new HashMap<String, String>();
        int passIdx = upperAlpha.indexOf(password.toUpperCase());
        int countAlpha = 0;

        for(int i = passIdx + 1; i != passIdx; i = (i + 1) % 26) {
            TheRightBattleshipCipherCoord coord = new TheRightBattleshipCipherCoord(countAlpha % 5, countAlpha / 5);

            if(isToDecrypt)
                encodingTable.put(coord.toString(), Character.toString(upperAlpha.charAt(i)));
            else encodingTable.put(Character.toString(upperAlpha.charAt(i)), coord.toString());

            countAlpha++;
        }

        return encodingTable;
    }
}
