package pt.ual.android.bhjencryption.cipher;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class VerticalKeyPhraseCipher extends Cipher {

    public VerticalKeyPhraseCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate(boolean isEncrypt) {
        CipherValidationResult result = super.validate(isEncrypt);

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(getCipherMessage().getPasswordAsText() == null)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(getCipherMessage().getPasswordAsText().length() == 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));

        if (!StringUtils.matchingChars(getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return this.validate(true);
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return this.validate(false);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(VerticalKeyPhraseCipher.encryptWithCustomAlphabet(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, false));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(VerticalKeyPhraseCipher.encryptWithCustomAlphabet(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, false));
    }

    /**
     * O alfabeto tem de ter um tamanho par, caso contrário terá de se encontrar estratégia de preenchimento de caracteres
     * para formar a encoding table final.
     * @param message
     * @return
     */
    public static String encryptWithCustomAlphabet(String message, String password, String alphabet, boolean isCaseSensitive) {
        String inputMessage = message.trim();
        String inputPassword = password.trim();
        String alphaLowerEncodingTable = buildEncodingTable(inputPassword.toLowerCase(), alphabet.toLowerCase());
        String alphaUpperEncodingTable = buildEncodingTable(inputPassword.toUpperCase(), alphabet.toUpperCase());
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < inputMessage.length(); i++) {
            char ch = inputMessage.charAt(i);

            if(ch == ' ')  {
                sbOutput.append(ch);
                continue;
            }

            if(Character.isLowerCase(ch))
                sbOutput.append(findEncodingChar(ch, alphaLowerEncodingTable));
            else sbOutput.append(findEncodingChar(ch, alphaUpperEncodingTable));
        }

        return isCaseSensitive ? sbOutput.toString() : sbOutput.toString().toUpperCase();
    }

    private static char findEncodingChar(char ch, String encodingTable) {
        int idx = encodingTable.indexOf(ch);

        if(idx >= (encodingTable.length() / 2))
            return encodingTable.charAt(idx - (encodingTable.length() / 2));

        return encodingTable.charAt(idx + (encodingTable.length() / 2));
    }

    protected static String buildEncodingTable(String password, String alphabet) {
        char[] alphaEncodingTable = new char[alphabet.length()];
        String passwordAux = password.replace(" ", "");
        String alphaAux = alphabet;
        int count = 0;

        while(passwordAux.length() > 0) { // preencher a tabela com os caracteres existentes
            char ch = passwordAux.charAt(0);

            alphaEncodingTable[count++] = ch;
            passwordAux = passwordAux.replace(String.valueOf(ch), "");
            alphaAux = alphaAux.replace(String.valueOf(ch), "");
        }

        if(alphaAux != null) {
            for (int i = 0; i < alphaAux.length(); i++) { // preencher o resto da tabela com os restantes (se existirem) chars do alfabeto escolhido
                alphaEncodingTable[count++] = alphaAux.charAt(i);
            }
        }

        return new String(alphaEncodingTable);
    }
}
