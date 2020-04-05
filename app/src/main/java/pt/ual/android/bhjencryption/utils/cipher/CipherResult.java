package pt.ual.android.bhjencryption.utils.cipher;

import android.graphics.Bitmap;

public class CipherResult {
    private String strResult;
    private Bitmap bitmapResult;

    public CipherResult() {

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
}
