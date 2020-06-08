package pt.ual.android.bhjencryption.cipher;

public class Code3PlusCipher extends CesarCipher {

    public Code3PlusCipher(CipherMessage cipherMessage) {
        super(new CipherMessage(cipherMessage.getImageTextMessage(), "3"));
    }
}
