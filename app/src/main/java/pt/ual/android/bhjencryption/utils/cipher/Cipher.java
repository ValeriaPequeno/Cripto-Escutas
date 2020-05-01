package pt.ual.android.bhjencryption.utils.cipher;

public abstract class Cipher {
    private String message;

    Cipher(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public CipherValidationResult validate() {
        if(getMessage() == null)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_MESSAGE));

        if(getMessage().isEmpty())
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_MESSAGE));

        return new CipherResult();
    }

    public abstract CipherResult encrypt();

    public abstract CipherResult decrypt();
}
