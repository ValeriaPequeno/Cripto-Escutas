package pt.ual.android.bhjencryption.utils.cipher;

public class CrabCipher extends Cipher {

    public CrabCipher(String message) {
        super(message);
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return validate();
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return validate();
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(new StringBuilder(getMessage()).reverse().toString());
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(new StringBuilder(getMessage().trim()).reverse().toString());
    }
}
