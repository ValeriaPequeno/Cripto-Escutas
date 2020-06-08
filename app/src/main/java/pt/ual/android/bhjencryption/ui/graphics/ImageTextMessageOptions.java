package pt.ual.android.bhjencryption.ui.graphics;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageTextMessageOptions implements Parcelable {
    private String strOptions;
    private String resourceType;

    public ImageTextMessageOptions(String options) {
        this.strOptions = options;

        decodeOptions();
    }

    protected ImageTextMessageOptions(Parcel in) {
        strOptions = in.readString();
        resourceType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strOptions);
        dest.writeString(resourceType);
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
            }
    }

    public String getResourceType() {
        return resourceType;
    }
}
