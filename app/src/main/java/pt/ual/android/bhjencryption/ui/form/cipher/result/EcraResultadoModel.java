package pt.ual.android.bhjencryption.ui.form.cipher.result;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.io.Serializable;

import pt.ual.android.bhjencryption.ui.utils.ImagesUtils;

/**
 * Este Model será necessário pois a Activity de Cifra e Decifra necessitam de forçosamente passar
 * o resultado da cifra/decifra
 */
public class EcraResultadoModel implements Serializable {
    private static final String STRING_IS_EMPTY = "O resultado obtido é nulo ou vazio.";
    private String strResult;

    public EcraResultadoModel() {

    }

    public EcraResultadoModel(String result) {
        setResult(result);
    }

    public void setResult(String result) {
        this.strResult = result;
    }

    public String getResultAsString() {
        if (this.strResult != null && !this.strResult.isEmpty())
            return this.strResult;
        else
            return STRING_IS_EMPTY;
    }

    public Bitmap getResultAsBitmap() {
        if (this.strResult != null && !this.strResult.isEmpty())
            return ImagesUtils.textAsBitmap(this.strResult, 100, Color.BLACK);
        else
            return ImagesUtils.textAsBitmap(STRING_IS_EMPTY, 100, Color.BLACK);
    }
}
