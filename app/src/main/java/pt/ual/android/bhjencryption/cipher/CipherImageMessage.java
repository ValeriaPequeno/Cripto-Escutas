package pt.ual.android.bhjencryption.cipher;

import android.graphics.Bitmap;

/**
 * TODO: transformar o Bitmap em algo que não esteja attached ao Android.
 */
public interface CipherImageMessage {
    public void setMessageText(String text);
    public String getMessageAsText();
    public <T>  Bitmap getMessageAsImage(T value);
    public CipherImageMessageOptions getImageMessageOptions();
}
