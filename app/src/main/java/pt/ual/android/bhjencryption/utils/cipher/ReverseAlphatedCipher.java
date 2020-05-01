package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class ReverseAlphatedCipher extends Cipher {

    public static String ALPHABET_LOWER = "aàáâãbcçdeéêfghiíjklmnoóôõpqrstuúvwxyz"; // abcdefghijklmnopqrstuvwxyz

    private static final String MESSAGE_HAS_DIGITS = "IAC00";

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

        if(!super.validate().hasErrors())
            if(StringUtils.hasDigit(getMessage()))
                return new CipherResult(new CipherErrorCode(MESSAGE_HAS_DIGITS));

        return result;
    }

    public static String encrypt(String message) {
        return encryptCustomAlphabet(message, ALPHABET_LOWER);
    }

    public static String decrypt(String message) {
        return encryptCustomAlphabet(message, new StringBuilder(ALPHABET_LOWER).reverse().toString());
    }

    /**
     * Trata de letras maiúsculas e minúsculas. Só o alfabeto Português é tratado.
     * @param message
     * @param alphabet
     * @return string codificada
     */
    public static String encryptCustomAlphabet(String message, String alphabet) {
        StringBuilder sbOut = new StringBuilder();
        String upperAlpha = alphabet.toUpperCase();
        String reversedAlpha = new StringBuilder(alphabet).reverse().toString();
        String upperReversedAlpa = reversedAlpha.toUpperCase();
        String input = message.trim();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int alphaChIdx;

            if(Character.isUpperCase(ch)) {
                alphaChIdx = upperAlpha.indexOf(ch);
                sbOut.append(upperReversedAlpa.charAt(alphaChIdx));
            }
            else {
                alphaChIdx = alphabet.indexOf(ch);
                sbOut.append(reversedAlpha.charAt(alphaChIdx));
            }

        }

        return sbOut.toString();
    }
}
