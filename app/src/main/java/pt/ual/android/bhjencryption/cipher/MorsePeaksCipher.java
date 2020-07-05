package pt.ual.android.bhjencryption.cipher;

import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;
import pt.ual.android.bhjencryption.utils.StringUtils;

public class MorsePeaksCipher extends ImageCipher {

    public MorsePeaksCipher(CipherMessage cipherMessage) {
        super(cipherMessage);
    }

    @Override
    public CipherValidationResult validate(boolean isEncrypt) {
        CipherValidationResult result = super.validate(isEncrypt);

        if(!result.hasErrors()) {
            if (!StringUtils.matchingChars(getCipherMessage().getMessageAsText(), CipherUtils.ASCII_ALPHABET_LOWER, true, false))
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
        }

        return result;
    }

    @Override
    public CipherValidationResult validateEncrypt() {
        return this.validate(true);
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        MorseCipher morseCipher = new MorseCipher(getCipherMessage());

        return morseCipher.validateDecrypt();
    }

    @Override
    public CipherResult encrypt() {
        MorseCipher morseCipher = new MorseCipher(getCipherMessage());
        String messageAsMorse = morseCipher.encrypt().getResultAsString().replace("/", " ");

        getCipherMessage().getImageTextMessage().setMessageText(messageAsMorse);

        return new CipherResult(getCipherMessage().getImageTextMessage());
    }

    @Override
    public CipherResult decrypt() {
        MorseCipher morseCipher = new MorseCipher(getCipherMessage());

        return morseCipher.decrypt();
    }
}
