package pt.ual.android.bhjencryption.utils.cipher;

public class Code3PlusCipher extends CesarCipher {

    public Code3PlusCipher(String message) {
        super(message, 3);
    }
}
