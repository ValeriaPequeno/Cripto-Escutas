package pt.ual.android.bhjencryption.utils.cipher;

import android.graphics.Bitmap;

public class CipherResult implements CipherValidationResult {
    private String strResult;
    private Bitmap bitmapResult;
    private CipherErrorCode cipherErrorCode;

    public CipherResult() {

    }

    public CipherResult(CipherErrorCode cipherErrorCode) {
        this.cipherErrorCode = cipherErrorCode;
    }

    public CipherResult(String strResult) {
        this.strResult = strResult;
    }

    public CipherResult(Bitmap bitmapResult) {
        this.bitmapResult = bitmapResult;
    }

    public String getResultAsString() {
        return strResult;
    }

    public Bitmap getResultAsBitmap() {
        return bitmapResult;
    }

    public CipherErrorCode getCipherErrorCode() {
        return cipherErrorCode;
    }

    public boolean hasErrors() {
        return cipherErrorCode != null;
    }
}
