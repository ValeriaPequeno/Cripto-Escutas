package pt.ual.android.bhjencryption.cipher;

public abstract class Cipher {
    private CipherMessage cipherMessage;

    Cipher(CipherMessage cipherMessage) {
        this.cipherMessage = cipherMessage;
    }

    public CipherMessage getCipherMessage() {
        return cipherMessage;
    }

    protected CipherValidationResult validate(boolean isEncrypt) {
        if(getCipherMessage().getMessageAsText() == null)
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_MESSAGE));

        if(getCipherMessage().getMessageAsText().isEmpty())
            return new CipherResult(new CipherErrorCode(CipherErrorCode.EMPTY_MESSAGE));

        if(isEncrypt) {
            if (getCipherMessage().getMessageAsText().length() > getCipherMessage().getMessageMaxLengthEncrypt())
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_MAX_LENGTH_REACHED, new String[]{String.valueOf(getCipherMessage().getMessageMaxLengthEncrypt())}));
        }
        else {
            if (getCipherMessage().getMessageAsText().length() > getCipherMessage().getMessageMaxLengthDecrypt())
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_MAX_LENGTH_REACHED, new String[]{String.valueOf(getCipherMessage().getMessageMaxLengthDecrypt())}));
        }

        return new CipherResult();
    }

    public abstract CipherValidationResult validateEncrypt();

    public abstract CipherValidationResult validateDecrypt();

    public abstract CipherResult encrypt();

    public abstract CipherResult decrypt();
}
