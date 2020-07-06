package pt.ual.android.bhjencryption.ui.form.cipher.result;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import pt.ual.android.bhjencryption.cipher.CipherImageMessage;
import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessage;
import pt.ual.android.bhjencryption.ui.graphics.utils.ImagesUtils;

/**
 * Este Model será necessário pois a Activity de Cifra e Decifra necessitam de forçosamente passar
 * o resultado da cifra/decifra
 */
public class EcraResultadoModel implements Parcelable {
    private static final String RESULT_IS_EMPTY = "O resultado obtido é nulo ou vazio.";
    private String errorCode;
    private String[] errorParams;
    private String strResult;
    private ImageTextMessage imageTextMessage;

    public EcraResultadoModel() {

    }

    public EcraResultadoModel(String strResult) {
        setImageTextMessage(null);
        setStrResult(strResult);
    }

    public EcraResultadoModel(String strResult, String errorCode, String[] errorParams) {
        this(strResult);

        this.errorParams = errorParams;
        this.errorCode = errorCode;
    }

    public EcraResultadoModel(ImageTextMessage imageTextMessage) {
        setStrResult(null);
        setImageTextMessage(imageTextMessage);
    }

    public String getStrResult() {
        return strResult;
    }

    public ImageTextMessage getImageTextMessage() {
        return imageTextMessage;
    }

    public void setImageTextMessage(ImageTextMessage imageTextMessage) {
        this.imageTextMessage = imageTextMessage;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }

    public String getResultAsString() {
        if (this.strResult != null && !this.strResult.isEmpty())
            return this.strResult;
        else
            return RESULT_IS_EMPTY;
    }

    public Bitmap getResultAsBitmap(Context context) {
        if(this.imageTextMessage != null)
            return this.imageTextMessage.getMessageAsImage(context);

        if (this.strResult != null && !this.strResult.isEmpty())
            return ImagesUtils.textAsBitmap(this.strResult, 100, Color.BLACK);

        return ImagesUtils.textAsBitmap(RESULT_IS_EMPTY, 100, Color.BLACK);
    }

    public boolean hasErrors() {
        return errorCode != null;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /* Implementação do Parcelable - Não é possível serializar o android.graphics.Bitmap, logo, foi preciso mudar a estratégia*/

    protected EcraResultadoModel(Parcel in) {
        this.errorCode = in.readString();
        this.strResult = in.readString();
        this.imageTextMessage = in.readParcelable(ImageTextMessage.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errorCode);
        dest.writeString(strResult);
        dest.writeParcelable(imageTextMessage, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EcraResultadoModel> CREATOR = new Creator<EcraResultadoModel>() {
        @Override
        public EcraResultadoModel createFromParcel(Parcel in) {
            return new EcraResultadoModel(in);
        }

        @Override
        public EcraResultadoModel[] newArray(int size) {
            return new EcraResultadoModel[size];
        }
    };

    public String[] getErrorParams() {
        return errorParams;
    }

    public boolean hasErrorParams() {
        return errorParams != null;
    }
}
