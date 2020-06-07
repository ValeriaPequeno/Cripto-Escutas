package pt.ual.android.bhjencryption.cipher;

public class CesarCipher extends Cipher {

    public CesarCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
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
        if(getCipherMessage().getPasswordAsInt() == Integer.MIN_VALUE)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_PASSWORD));

        if(getCipherMessage().getPasswordAsInt() == Integer.MIN_VALUE + 1)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.PASSWORD_HAS_NOT_ALLOWED_CHARS));

        if(getCipherMessage().getPasswordAsInt() < 0)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.NEGATIVE_INTEGER_PASSWORD));

        return new CipherResult();
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getCipherMessage().getMessageAsText(), getCipherMessage().getPasswordAsInt()));
    }

    public static String encrypt(String enc, int offset) {
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        String[] palavra = enc.split(" ");
        for(String p : palavra){
            for (char i : p.toCharArray()) {
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
            encoded.append(" ");
        }
        return encoded.toString();
    }

    public static String decrypt(String enc, int offset) {
        return encrypt(enc, 26 - offset);
    }
}
