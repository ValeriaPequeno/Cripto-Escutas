package pt.ual.android.bhjencryption.utils.cipher;

public class MorseCipher extends Cipher {

    public MorseCipher(String message) {
        super(message);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(CipherUtils.morseEncode(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(CipherUtils.morseDecode(getMessage()));
    }
}
