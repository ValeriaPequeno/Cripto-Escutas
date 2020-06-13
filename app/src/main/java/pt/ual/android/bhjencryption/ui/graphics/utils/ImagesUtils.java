package pt.ual.android.bhjencryption.ui.graphics.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.List;

import pt.ual.android.bhjencryption.ui.graphics.ImageTextMessageOptions;

public class ImagesUtils {

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);

        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);

        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawText(text, 0, baseline, paint);

        return image;
    }

//    public Bitmap drawMultilineTextToBitmap(Context gContext,
//                                            int gResId,
//                                            String gText) {
//        Canvas canvas = new Canvas(bitmap);
//
//        TextPaint paint= new TextPaint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.rgb(61, 61, 61));
//        paint.setTextSize((int) (14 * scale));
//
//
//        // init StaticLayout for text
//        StaticLayout textLayout = new StaticLayout(
//                gText, paint, textWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
//
//        // get height of multiline text
//        int textHeight = textLayout.getHeight();
//
//        // get position of text's top left corner
//        float x = (bitmap.getWidth() - textWidth)/2;
//        float y = (bitmap.getHeight() - textHeight)/2;
//
//        // draw text to the Canvas center
//        canvas.save();
//        canvas.translate(x, y);
//        textLayout.draw(canvas);
//        canvas.restore();
//
//        return bitmap;
//    }

    /**
     * Solução alternativa mas que aparentemente não lida com todos os tipos de Drawable:
     * Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
     *
     * Ainda como alternativa: (esta solução carrega o recurso com um tamanho (bytes) bastante elevado
     * Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Bitmap de uma única cor de 1x1
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap drawBitmaps(int width, int height, List<Bitmap> messageBitmaps) {
        Bitmap finalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float printWidth = 0f;
        Canvas canvas = new Canvas(finalBitmap);

        canvas.drawColor(Color.TRANSPARENT);

        for (int i = 0; i < messageBitmaps.size(); i++) {
            Bitmap bitmap = messageBitmaps.get(i);
            // Render both input images into the finalBitmap bitmap and return it.
            if(i > 0)
                printWidth += (float) ((bitmap.getWidth() * 2) + 10);

            canvas.drawBitmap(bitmap, printWidth, 0f, null);
        }

        return finalBitmap;
    }

    public static Bitmap drawBitmapsLines(int width, int height, List<List<Bitmap>> messageBitmapsLines, ImageTextMessageOptions options) {
        Bitmap finalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        float printWidth = 0f;
        float printHeight = 0f;
        Canvas canvas = new Canvas(finalBitmap);

        canvas.drawColor(Color.WHITE);

        for(int i = 0; i < messageBitmapsLines.size(); i++) {
            List<Bitmap> messageBitmaps = messageBitmapsLines.get(i);

            for (int j = 0; j < messageBitmaps.size(); j++) {
                Bitmap bitmap = messageBitmaps.get(j);

                if (j > 0)
                    printWidth += (float) ((bitmap.getWidth() * 2) + options.getBetweenCharSpacing()); // basear o deslocamento com base na largura da imagem, que pode variar.

                canvas.drawBitmap(bitmap, printWidth, printHeight, null);
            }

            printHeight += (float) ((options.getCharImageHeight() * 2) + options.getBetweenLinesSpacing()); // forçar a altura da linha, pois as imagens terão sempre todas a mesma altura
            printWidth = 0f;
        }

        return finalBitmap;
    }

    /**
     * Faz um retrieve, poupando a memória, do size (width x height) de uma imagem. Indica também qual o MimeType.
     *
     * Basta consultar os parâmetros outHeight, outWidth e outMimeType das Options returnadas.
     * @param resource
     * @param id
     * @return
     */
    public static BitmapFactory.Options getResourceBitmapDetails(Resources resource, int id) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resource, id, options);

//        int imageHeight = options.outHeight;
//        int imageWidth = options.outWidth;
//        String imageType = options.outMimeType;

        return options;
    }

    /**
     * Facultado pela documentação do Android
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private static int calculateInSampleSizeByRatio(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * Disponibilizado pela documentação do Android
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeBitmapFromResource(Context context, int resId, int finalWidth, int finalHeight) {
        Drawable drawable = context.getResources().getDrawable(resId, context.getTheme());
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        return Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, false);
    }

    public static Bitmap getEmptyTransparentBitmap(int width, int height) {
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.TRANSPARENT);

        return image;
    }
}
