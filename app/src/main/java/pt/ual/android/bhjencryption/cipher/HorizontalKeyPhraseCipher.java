package pt.ual.android.bhjencryption.cipher;

import pt.ual.android.bhjencryption.utils.StringUtils;

public class HorizontalKeyPhraseCipher extends Cipher {

    public HorizontalKeyPhraseCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));

            result = validatePassword();
        }

        return result;
    }

    public CipherValidationResult validatePassword() {
        if(getCipherMessage().getPasswordAsText() == null) // validate password
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(getCipherMessage().getPasswordAsText().length() == 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.INVALID_PASSWORD_SIZE));

        if (!StringUtils.matchingChars(getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        return new CipherResult();
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
        return new CipherResult(HorizontalKeyPhraseCipher.encryptWithCustomAlphabet(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, false, false));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(HorizontalKeyPhraseCipher.encryptWithCustomAlphabet(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false));
    }

    /**
     * O alfabeto tem de ter um tamanho par, caso contrário terá de se encontrar estratégia de preenchimento de caracteres
     * para formar a encoding table final.
     * @param message
     * @return
     */
    public static String encryptWithCustomAlphabet(String message, String password, String alphabet, boolean isPasswordAlphabet, boolean isCaseSensitive) {
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

        return isCaseSensitive ? sbOutput.toString() : sbOutput.toString().toUpperCase();
    }

    private static char findEncodingChar(char ch, String alphaEncodingTable, String passwordEncodingTable, boolean isPasswordAlphabet) {
        if(isPasswordAlphabet)
            return passwordEncodingTable.charAt(alphaEncodingTable.indexOf(ch));

        return alphaEncodingTable.charAt(passwordEncodingTable.indexOf(ch));
    }
}
