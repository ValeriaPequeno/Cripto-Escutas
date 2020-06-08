package pt.ual.android.bhjencryption.cipher;

import android.graphics.Bitmap;

public class CipherResult implements CipherValidationResult {
    private String strResult;
    private CipherImageMessage cipherImageMessage;
    private CipherErrorCode cipherErrorCode;

    public CipherResult() {

    }

    public CipherResult(CipherErrorCode cipherErrorCode) {
        this.cipherErrorCode = cipherErrorCode;
    }

    public CipherResult(String strResult) {
        this.strResult = strResult;
        this.cipherImageMessage = null;
    }

    public CipherResult(CipherImageMessage cipherImageMessage) {
        this.cipherImageMessage = cipherImageMessage;
        this.strResult = null;
    }

    public String getResultAsString() {
        return strResult;
    }

    public CipherImageMessage getResultAsCipherImageMessage() {
        return cipherImageMessage;
    }

    public CipherErrorCode getCipherErrorCode() {
        return cipherErrorCode;
    }

    public boolean hasErrors() {
        return cipherErrorCode != null;
    }

    public boolean hasResultAsCipherImageMessage() {
        return this.cipherImageMessage != null;
    }
}
