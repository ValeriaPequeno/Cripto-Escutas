package pt.ual.android.bhjencryption.utils.cipher;

public class VerticalCipher extends Cipher {
    private int password;

    public VerticalCipher(String message, int password) {
        super(message);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(CipherUtils.verticalEncode(getMessage(), this.password, "cima"));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(CipherUtils.verticalDecode(getMessage(), this.password, "cima"));
    }
}
