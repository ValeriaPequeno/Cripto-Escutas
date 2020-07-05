package pt.ual.android.bhjencryption.ui.graphics;

import android.os.Parcel;
import android.os.Parcelable;

import pt.ual.android.bhjencryption.cipher.CipherImageMessageOptions;
import pt.ual.android.bhjencryption.utils.IntegerUtils;

public class ImageTextMessageOptions implements Parcelable, CipherImageMessageOptions {
    private String strOptions;
    private String resourceType;
    private int charImageWidth;
    private int charImageHeight;
    private int maxLineCharImages;
    private int betweenCharSpacing;
    private int betweenLinesSpacing;
    private int lineCharSpaceWidth;
    private int maxMessageCharsEncrypt;
    private int maxMessageCharsDecrypt;
    private int isSquared;

    public ImageTextMessageOptions(String options) {
        this.strOptions = options;

        decodeOptions();
    }

    protected ImageTextMessageOptions(Parcel in) {
        this.strOptions = in.readString();
        this.resourceType = in.readString();
        this.charImageWidth = in.readInt();
        this.charImageHeight = in.readInt();
        this.maxLineCharImages = in.readInt();
        this.betweenCharSpacing = in.readInt();
        this.betweenLinesSpacing = in.readInt();
        this.lineCharSpaceWidth = in.readInt();
        this.maxMessageCharsEncrypt = in.readInt();
        this.maxMessageCharsDecrypt = in.readInt();
        this.isSquared = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strOptions);
        dest.writeString(resourceType);
        dest.writeInt(charImageWidth);
        dest.writeInt(charImageHeight);
        dest.writeInt(maxLineCharImages);
        dest.writeInt(betweenCharSpacing);
        dest.writeInt(betweenLinesSpacing);
        dest.writeInt(lineCharSpaceWidth);
        dest.writeInt(maxMessageCharsEncrypt);
        dest.writeInt(maxMessageCharsDecrypt);
        dest.writeInt(isSquared);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageTextMessageOptions> CREATOR = new Creator<ImageTextMessageOptions>() {
        @Override
        public ImageTextMessageOptions createFromParcel(Parcel in) {
            return new ImageTextMessageOptions(in);
        }

        @Override
        public ImageTextMessageOptions[] newArray(int size) {
            return new ImageTextMessageOptions[size];
        }
    };

    private void decodeOptions() {
        if(strOptions != null)
            if(!strOptions.isEmpty()) {
                String[] optionsArr = strOptions.split(",");

                this.resourceType = optionsArr[0];
                this.charImageWidth = IntegerUtils.tryParseInt(optionsArr[1]) ? Integer.parseInt(optionsArr[1]) : -1;
                this.charImageHeight = IntegerUtils.tryParseInt(optionsArr[2]) ? Integer.parseInt(optionsArr[2]) : -1;
                this.maxLineCharImages = IntegerUtils.tryParseInt(optionsArr[3]) ? Integer.parseInt(optionsArr[3]) : -1;
                this.betweenCharSpacing = IntegerUtils.tryParseInt(optionsArr[4]) ? Integer.parseInt(optionsArr[4]) : -1;
                this.betweenLinesSpacing = IntegerUtils.tryParseInt(optionsArr[5]) ? Integer.parseInt(optionsArr[5]) : -1;
                this.lineCharSpaceWidth = IntegerUtils.tryParseInt(optionsArr[6]) ? Integer.parseInt(optionsArr[6]) : -1;
                this.maxMessageCharsEncrypt = IntegerUtils.tryParseInt(optionsArr[7]) ? Integer.parseInt(optionsArr[7]) : -1;
                this.maxMessageCharsDecrypt = IntegerUtils.tryParseInt(optionsArr[8]) ? Integer.parseInt(optionsArr[8]) : -1;
                this.isSquared = IntegerUtils.tryParseInt(optionsArr[9]) ? Integer.parseInt(optionsArr[9]) : -1;
            }
    }

    public String getResourceType() {
        return resourceType;
    }

    public int getCharImageWidth() {
        return charImageWidth;
    }

    public int getCharImageHeight() {
        return charImageHeight;
    }

    public int getMaxLineCharImages() {
        return maxLineCharImages;
    }

    public int getBetweenCharSpacing() {
        return betweenCharSpacing;
    }

    public int getLineCharSpaceWidth() {
        return lineCharSpaceWidth;
    }

    @Override
    public int getMaxMessageCharsEncrypt() {
        return maxMessageCharsEncrypt;
    }

    @Override
    public int getMaxMessageCharsDecrypt() { return maxMessageCharsDecrypt; }

    public boolean getIsSquared() {
        return isSquared == 1 ? true : false;
    }

    public int getBetweenLinesSpacing() {
        return betweenLinesSpacing;
    }

    /**
     * x2 visto que a API de gráficos do Android tem um offset desconhecido ao fazer draw.
     * @return
     */
    public int getCharImageTotalWidth() {
        return this.charImageWidth * 2 + this.betweenCharSpacing;
    }

    /**
     * x2 visto que a API de gráficos do Android tem um offset desconhecido ao fazer draw.
     * @return
     */
    public int getCharImageTotalHeight() {
        return this.charImageHeight * 2 + this.betweenLinesSpacing;
    }

    public int getImageTotalWidth() {
        return this.maxLineCharImages * getCharImageTotalWidth();
    }

    public int getImageTotalHeight() {
        return (this.maxMessageCharsEncrypt / this.maxLineCharImages) * getCharImageTotalHeight();
    }

    public int getImageFinalWidth(int numLines, int numCharFirstLine) {
        if(numLines == 1)
            return numCharFirstLine * getCharImageTotalWidth();

        return getImageTotalWidth();
    }

    public int getImageFinalHeight(int numLines) {
        return getCharImageTotalHeight() * numLines;
    }
}
