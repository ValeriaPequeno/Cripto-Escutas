package pt.ual.android.bhjencryption.ui.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ual.android.bhjencryption.cipher.CipherImageMessage;
import pt.ual.android.bhjencryption.ui.graphics.utils.ImagesUtils;

public class ImageTextMessage implements CipherImageMessage, Parcelable {
    private static final String CHARACTER_RESOURCE_NAME_FORMAT = "%s_%c";

    private ImageTextMessageOptions resourceTypeOptions;
    private StringBuilder textMessage;

    public ImageTextMessage(String resourceTypeOptions) {
        this(null, resourceTypeOptions);
    }

    public ImageTextMessage(String textMessage, String resourceTypeOptions) {
        this.textMessage = new StringBuilder();
        this.resourceTypeOptions = new ImageTextMessageOptions(resourceTypeOptions);

        addTextToMessage(textMessage);
    }

    protected ImageTextMessage(Parcel in) {
        this.textMessage = new StringBuilder(in.readString());
        this.resourceTypeOptions = in.readParcelable(ImageTextMessageOptions.class.getClassLoader());
    }

    public static final Creator<ImageTextMessage> CREATOR = new Creator<ImageTextMessage>() {
        @Override
        public ImageTextMessage createFromParcel(Parcel in) {
            return new ImageTextMessage(in);
        }

        @Override
        public ImageTextMessage[] newArray(int size) {
            return new ImageTextMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.textMessage.toString());
        dest.writeParcelable(this.resourceTypeOptions, flags);
    }

    public void addTextToMessage(String text) {
        if(textMessage != null)
            if(!text.isEmpty())
                this.textMessage.append(text.trim());
    }

    public String getMessageAsText() {
        return this.textMessage.toString();
    }

    private String getResourceableTextMessage() {
        return this.textMessage.toString().toLowerCase().replaceAll(" ", "_");
    }

    /**
     * fazer o split do texto por palavras, isto é, por espaços
     * @return
     */
    private String[] getTextMessageWordByWord() {
        return getResourceableTextMessage().split("_");
    }

    /**
     *  TODO: definir estratégia: Quantos caracteres/palavras por linhas? E se eles não forem divisíveis? Fica tudo numa só linha.. Mas e se forem 3 palavras em que a primeira e a última excede os caracteres?
     *
     * @param value aid object to build the target image of the message text
     * @return
     */
    public <T> Bitmap getMessageAsImage(T value) {
        Context context = (Context) value;
        Map<String, Bitmap> loadedDrawable = loadCharacterImagesResources(context);
        char[] chArrMessage = getResourceableTextMessage().toCharArray();
        Bitmap imageMessage = null;
        List<Bitmap> messageBitmaps = new ArrayList<Bitmap>();

        for (char ch : chArrMessage) {
            Bitmap bitmap = loadedDrawable.get(String.valueOf(ch));
//            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            //Log.e("Original   dimensions", String.valueOf(bitmap.getByteCount()) + " " + bitmap.getWidth() + "x" + bitmap.getHeight());

            messageBitmaps.add(bitmap);



            // obter a dimensão máxima por frase por tipo de recurso (prefixo, neste caso)
            // Definir dimensão da imagem e quantos
            // Obter uma Lista de frases (aqui vai ter de aplicar o algoritmo para definir o tamanho da frase)
            // Neste método, lista de imagens, uma imagem por frase em que esta vai sendo concatenada na horizontal cada caracter
            // No final, junta todas as imagens verticalmente.

            // NOTA: mesmo que não exista caracteres suficientes para fazer fill do tamanho máximo por frase, tem de se fazer fill com espaços quando fechar a frase Fica tipo matriz
        }

        imageMessage = ImagesUtils.printBitmap(550, 80, messageBitmaps);

        Log.e("Original dimensions", String.valueOf(imageMessage.getByteCount()) + " " + imageMessage.getWidth() + "x" + imageMessage.getHeight());

        return imageMessage;
        //return  Bitmap.createScaledBitmap(imageMessage, 550, 80, false);
    }

    /**
     * TODO: carregar as imagens já com um formato/tamano específico, ou só no final se trata do size do bitmap e reduz-se o tamanho?
     * @return
     */
    private Map<String, Bitmap> loadCharacterImagesResources(Context context) {
        char[] chArrMessage = getResourceableTextMessage().toCharArray();
        Map<String, Bitmap> loadedBitmap = new HashMap<String, Bitmap>();

        for (char ch : chArrMessage) {
            if(!loadedBitmap.containsKey(String.valueOf(ch))) {
                Drawable drawable = loadCharacterResource(ch, context);
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 20, 20, false);

                loadedBitmap.put(String.valueOf(ch), scaledBitmap);
            }
        }

        return loadedBitmap;
    }

    private String getCharacterResourceName(char ch, Context context) {
        return String.format(ImageTextMessage.CHARACTER_RESOURCE_NAME_FORMAT, this.resourceTypeOptions.getResourceType(), ch);
    }

    private Drawable loadCharacterResource(char ch, Context context) {
        String resourceName = getCharacterResourceName(ch, context);
        int id = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());

        return context.getResources().getDrawable(id, context.getTheme());
    }
}
