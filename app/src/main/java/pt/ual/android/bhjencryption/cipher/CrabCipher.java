package pt.ual.android.bhjencryption.cipher;

public class CrabCipher extends Cipher {

    public CrabCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
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
        return new CipherResult(new StringBuilder(getCipherMessage().getMessageAsText()).reverse().toString());
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(new StringBuilder(getCipherMessage().getMessageAsText().trim()).reverse().toString());
    }
}
