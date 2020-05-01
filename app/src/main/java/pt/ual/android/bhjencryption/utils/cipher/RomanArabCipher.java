package pt.ual.android.bhjencryption.utils.cipher;

public class RomanArabCipher extends Cipher {
    public RomanArabCipher(String message) {
        super(message);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(CipherUtils.romaArabEncode(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(CipherUtils.romaArabDecode(getMessage()));
    }
}
