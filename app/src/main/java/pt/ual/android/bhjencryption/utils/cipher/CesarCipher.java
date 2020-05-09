package pt.ual.android.bhjencryption.utils.cipher;

public class CesarCipher extends Cipher {
    private int password;

    private CesarCipher(String message) {
        super(message);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
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
