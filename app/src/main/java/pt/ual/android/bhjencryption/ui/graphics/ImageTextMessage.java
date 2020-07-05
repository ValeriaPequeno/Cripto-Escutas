package pt.ual.android.bhjencryption.ui.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ual.android.bhjencryption.cipher.CipherImageMessage;
import pt.ual.android.bhjencryption.cipher.CipherImageMessageOptions;
import pt.ual.android.bhjencryption.ui.graphics.utils.ImagesUtils;

public class ImageTextMessage implements CipherImageMessage, Parcelable {
    private static final String CHARACTER_RESOURCE_NAME_FORMAT = "%s_%s";
    private static final String CHARACTER_DOT = "dot";
    private static final String CHARACTER_IPHEN = "iphen";

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

    /* Código de Parcelablização */

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

    /* Fim de código de Parcelablização */

    public void addTextToMessage(String text) {
        if(textMessage != null)
            if(!text.isEmpty())
                this.textMessage.append(text.trim());
    }

    @Override
    public void setMessageText(String text) {
        this.textMessage = new StringBuilder();

        addTextToMessage(text);
    }

    public String getMessageAsText() {
        return this.textMessage.toString();
    }

    private String getResourceableTextMessage() {
        return this.textMessage.toString().toLowerCase().replaceAll(" ", "_");
    }

    /**
     * Fazer o split do texto por palavras, isto é, por espaços.
     *
     * @return
     */
    private String[] getTextMessageWordByWord() {
        return getResourceableTextMessage().split("_");
    }

    /**
     * A estratégia deste método é dividir segamente a mensagem por linhas, sem olhar à lógica das palavras e frases.
     *
     * Numa versão futura poder-se-á melhorar este algoritmo para realizar algo mais inteligente.
     *
     * @return
     */
    private List<String> getTextMessageLineByLine() {
        String resourceableTextMessage = getResourceableTextMessage();
        List<String> textMessageLines = new ArrayList<String>();
        int idx = 0;

        while (idx < resourceableTextMessage.length()) {
            textMessageLines.add(resourceableTextMessage.substring(idx, Math.min(idx + this.resourceTypeOptions.getMaxLineCharImages(), resourceableTextMessage.length())));
            idx += this.resourceTypeOptions.getMaxLineCharImages();
        }

        return textMessageLines;
    }

    /**
     * Estratégia definida:
     *  Uma vez que existem limitações em torno da imagem que é desenhada, em que seria necessário, provavelmente, criar funcionalidades de zoom in/zoom out na imagem resultante da cifra e eventualmente
     *  até criar thumbnail da imagem final gerada para que ao clicar na imagem para zoom in e zoom out, decidiu-se impor restrições no número de caracteres com base em parâmetros definidos nos resources.
     *
     *  Uma vez que a imagem tem dimensões fixas, apesar de dinamicamente à cabeça definidas, foi necessário criar valores máximos de caracteres por linha. Como as palavras podem exceder o taamnho de uma linha,
     *  ou existir mais que uma palavra numa linha em que a última palavra acabe por exceder o tamanho da linha, esta será cortada e continuará na linha de baixo.
     *
     *  Estamos certos de que existem outras estratégias, mais inteligentes, mas iria limitar o número de caracteres máximos a cfirar da mensagem. Deixaremos essas estratégias mais evoluidas e avançadas
     *  para uma segunda release da aplicação
     *
     * @param value aid object to build the target image of the message text
     * @return
     */
    public <T> Bitmap getMessageAsImage(T value) {
        Context context = (Context) value;
        List<List<Bitmap>> messageBitmaps = decodeTextToImage(context);

        // calcula o valor final do bitmap dinamicamente, só com o tamanho que for estritamente necessário.
        return ImagesUtils.drawBitmapsLines(700, resourceTypeOptions.getImageFinalHeight(messageBitmaps.size()), messageBitmaps, resourceTypeOptions);
//        return ImagesUtils.drawBitmaps(700, 700, messageBitmaps);
    }

    @Override
    public CipherImageMessageOptions getImageMessageOptions() {
        return this.resourceTypeOptions;
    }

    private List<List<Bitmap>> decodeTextToImage(Context context) {
        Map<String, Bitmap> loadedDrawable = loadCharacterImagesResources(context);
        List<String> messageLineByLine = getTextMessageLineByLine();
        List<List<Bitmap>> messageLinesBitmaps = new ArrayList<List<Bitmap>>();

        for (String strLine : messageLineByLine) {
            List<Bitmap> messageBitmaps = new ArrayList<Bitmap>();
            char[] chArrLine = strLine.toCharArray();

            for (char ch : chArrLine) {
                Bitmap bitmap = loadedDrawable.get(String.valueOf(ch));

                messageBitmaps.add(bitmap);
            }

            messageLinesBitmaps.add(messageBitmaps);
        }

        return messageLinesBitmaps;
    }

    /**
     * @return
     */
    private Map<String, Bitmap> loadCharacterImagesResources(Context context) {
        char[] chArrMessage = getResourceableTextMessage().toCharArray();
        Map<String, Bitmap> loadedBitmap = new HashMap<String, Bitmap>();

        for (char ch : chArrMessage) {
            if(!loadedBitmap.containsKey(String.valueOf(ch))) {
                Bitmap scaledBitmapResource = null;

                switch(ch) {
                    case '_':
                        scaledBitmapResource = ImagesUtils.getEmptyTransparentBitmap(this.resourceTypeOptions.getBetweenCharSpacing(), this.resourceTypeOptions.getBetweenCharSpacing()); // a height é pouco relevante. O que interessa é dar largura.
                        break;
                    case '.':
                        scaledBitmapResource = loadCharacterResource(CHARACTER_DOT, context);
                        break;
                    case '-':
                        scaledBitmapResource = loadCharacterResource(CHARACTER_IPHEN, context);
                        break;
                    default:
                        scaledBitmapResource = loadCharacterResource(String.valueOf(ch), context);
                }

                loadedBitmap.put(String.valueOf(ch), scaledBitmapResource);
            }
        }

        return loadedBitmap;
    }

    private String getCharacterResourceName(String value, Context context) {
        return String.format(ImageTextMessage.CHARACTER_RESOURCE_NAME_FORMAT, this.resourceTypeOptions.getResourceType(), value);
    }

    private Bitmap loadCharacterResource(String value, Context context) {
        String resourceName = getCharacterResourceName(value, context);
        int id = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
        Bitmap bitmapResource;

        if(this.resourceTypeOptions.getIsSquared()) // Verificar se é um resource squared ou não e escolher o load conforme.
            bitmapResource = ImagesUtils.decodeBitmapFromResource(context, id, this.resourceTypeOptions.getCharImageWidth(), this.resourceTypeOptions.getCharImageHeight());
        else bitmapResource = ImagesUtils.decodeSampledBitmapFromResource(context.getResources(), id, this.resourceTypeOptions.getCharImageWidth(), this.resourceTypeOptions.getCharImageHeight());

//&        Log.e("Original dimensions", String.valueOf(bitmapResource.getByteCount()) + " " + bitmapResource.getWidth() + "x" + bitmapResource.getHeight());

        return bitmapResource;
    }
}
