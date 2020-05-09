package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class HorizontalKeyPhraseCipher extends Cipher {
    private String password;

    public HorizontalKeyPhraseCipher(String message, String password) {
        super(message);

        this.password = password;
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER, true))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            if (!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER.toUpperCase(), true))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            // TODO: validate password
        }

        return result;
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return this.validate();
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return this.validate();
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(HorizontalKeyPhraseCipher.encryptWithCustomAlphabet(getMessage(), password, CipherUtils.ALPHABET_LOWER, false));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(HorizontalKeyPhraseCipher.encryptWithCustomAlphabet(getMessage(), password, CipherUtils.ALPHABET_LOWER, true));
    }

    /**
     * O alfabeto tem de ter um tamanho par, caso contrário terá de se encontrar estratégia de preenchimento de caracteres
     * para formar a encoding table final.
     * @param message
     * @return
     */
    public static String encryptWithCustomAlphabet(String message, String password, String alphabet, boolean isPasswordAlphabet) {
        String inputMessage = message.trim();
        String inputPassword = password.trim();
        String lowerAlpha = alphabet.toLowerCase();
        String upperAlpha = alphabet.toUpperCase();
        String alphaLowerEncodingTable = VerticalKeyPhraseCipher.buildEncodingTable(inputPassword.toLowerCase(), lowerAlpha);
        String alphaUpperEncodingTable = VerticalKeyPhraseCipher.buildEncodingTable(inputPassword.toUpperCase(), upperAlpha);
        StringBuilder sbOutput = new StringBuilder();

        for(int i = 0; i < inputMessage.length(); i++) {
            char ch = inputMessage.charAt(i);

            if(ch == ' ')  {
                sbOutput.append(ch);
                continue;
            }

            if(Character.isLowerCase(ch))
                sbOutput.append(findEncodingChar(ch, alphaLowerEncodingTable, lowerAlpha, isPasswordAlphabet));
            else sbOutput.append(findEncodingChar(ch, alphaUpperEncodingTable, upperAlpha, isPasswordAlphabet));
        }

        return sbOutput.toString();
    }

    private static char findEncodingChar(char ch, String alphaEncodingTable, String passwordEncodingTable, boolean isPasswordAlphabet) {
        if(isPasswordAlphabet)
            return alphaEncodingTable.charAt(passwordEncodingTable.indexOf(ch));

        return passwordEncodingTable.charAt(alphaEncodingTable.indexOf(ch));
    }
}
