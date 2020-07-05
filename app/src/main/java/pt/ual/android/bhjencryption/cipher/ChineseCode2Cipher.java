package pt.ual.android.bhjencryption.cipher;

import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;
import pt.ual.android.bhjencryption.utils.StringUtils;

public class ChineseCode2Cipher extends ImageCipher {

    public ChineseCode2Cipher(CipherMessage cipherMessage) {
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
        return this.validate(false);
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(getCipherMessage().getImageTextMessage());
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(getCipherMessage().getMessageAsText());
    }
}
