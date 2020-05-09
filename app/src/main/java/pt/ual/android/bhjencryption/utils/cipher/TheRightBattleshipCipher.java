package pt.ual.android.bhjencryption.utils.cipher;

import java.util.HashMap;
import java.util.Map;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

/**
 * Aqui, não se utilizou o alfabeto completo Português, visto que a tabela requer um número par de letras.
 * Aqui utiliza-se o alfabeto ASCII: abcdefghijklmnopqrstuvwxyz
 */
public class TheRightBattleshipCipher extends Cipher {
    private String password;

    public TheRightBattleshipCipher(String message, String password) {
        super(message);

        this.password = password;
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(this.password == null) // validate password
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(this.password.length() != 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));

        if (!StringUtils.matchingChars(this.password.toUpperCase(), CipherUtils.ASCII_ALPHABET_LOWER.toUpperCase(), false)) // validate password
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getMessage().toUpperCase(), CipherUtils.ASCII_ALPHABET_LOWER.toUpperCase(), true))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getMessage().toUpperCase(), new String(
                            TheRightBattleshipCipherCoord.BATTLESHIP_MAP_COLUM_COORD + TheRightBattleshipCipherCoord.BATTLESHIP_MAP_LINE_COORD),
                    true))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            if (!TheRightBattleshipCipherCoord.validateCoords(this.getMessage().split(" "))) // Validar se contém coordenadas válidas
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_INVALID_FORMAT));
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        Map<String, String> encodingTable = buildEncodingTable(password.toUpperCase(), false);
        String upperMessage = getMessage().toUpperCase();
        String upperPassword = this.password.toUpperCase();
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < upperMessage.length(); i++) {
            char ch = upperMessage.charAt(i);

            if(ch == ' ' || ch == upperPassword.charAt(0)) // validar se a letra a cifrar é igual à pw e se for um espaço, fazer skip
                continue;

            sbOutput.append(encodingTable.get(String.valueOf(ch)));
            sbOutput.append(" ");
        }

        return new CipherResult(sbOutput.toString().trim());
    }

    @Override
    public CipherResult decrypt() {
        Map<String, String> encodingTable = buildEncodingTable(password.toUpperCase(), true);
        String[] arrMessage = getMessage().toUpperCase().split(" ");
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < arrMessage.length; i++) {
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
