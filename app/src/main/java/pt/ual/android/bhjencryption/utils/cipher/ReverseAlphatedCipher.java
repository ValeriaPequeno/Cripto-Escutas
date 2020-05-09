package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class ReverseAlphatedCipher extends Cipher {

    ReverseAlphatedCipher(String message) {
        super(message);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage()));
    }

    @Override
    public CipherValidationResult validate() {
        CipherValidationResult result = super.validate();

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
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

    public static String encrypt(String message) {
        return encryptCustomAlphabet(message, CipherUtils.ASCII_ALPHABET_LOWER, false);
    }

    public static String decrypt(String message) {
        return encryptCustomAlphabet(message, new StringBuilder(CipherUtils.ASCII_ALPHABET_LOWER).reverse().toString(), false);
    }

    /**
     * Trata de letras maiúsculas e minúsculas.
     *
     * @param message
     * @param alphabet
     * @param isCaseSensitive implementado após a implementação inicial. Funcionalidade que permite ignorar o output em minúsculas
     * @return string codificada
     */
    public static String encryptCustomAlphabet(String message, String alphabet, boolean isCaseSensitive) {
        StringBuilder sbOut = new StringBuilder();
        String upperAlpha = alphabet.toUpperCase();
        String lowerReversedAlpha = new StringBuilder(alphabet.toLowerCase()).reverse().toString();
        String upperReversedAlpa = lowerReversedAlpha.toUpperCase();
        String input = message.trim();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int alphaChIdx;

            if(ch == ' ')  {
                sbOut.append(ch);
                continue;
            }

            if(Character.isUpperCase(ch)) {
                alphaChIdx = upperAlpha.indexOf(ch);
                sbOut.append(upperReversedAlpa.charAt(alphaChIdx));
            }
            else {
                alphaChIdx = alphabet.indexOf(ch);
                sbOut.append(lowerReversedAlpha.charAt(alphaChIdx));
            }
        }

        return isCaseSensitive ? sbOut.toString() : sbOut.toString().toUpperCase();
    }
}
