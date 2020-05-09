package pt.ual.android.bhjencryption.utils.cipher;

public class HorizontalCipher extends Cipher {
    private int password;

    public HorizontalCipher(String message, int password) {
        super(message);

        this.password = password;
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return null;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        return null;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(CipherUtils.horizontalEncode(getMessage(), password,"esquerda"));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(CipherUtils.horizontalDecode(getMessage(), this.password,"esquerda"));
    }
}
