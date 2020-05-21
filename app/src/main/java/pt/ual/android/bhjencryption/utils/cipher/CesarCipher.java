package pt.ual.android.bhjencryption.utils.cipher;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

public class CesarCipher extends Cipher {
    private int password;

    private CesarCipher(String message) {
        super(message);
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
        if(this.password == Integer.MIN_VALUE)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(this.password == Integer.MIN_VALUE + 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(this.password < 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.NEGATIVE_INTEGER_PASSWORD));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_ALPHABET_LOWER, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    public CesarCipher(String message, int password) {
        this(message);

        this.password = password;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage(), this.password));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage(), this.password));
    }

    public static String encrypt(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    public static String decrypt(String enc, int offset) {
        return encrypt(enc, 26 - offset);
    }
}
