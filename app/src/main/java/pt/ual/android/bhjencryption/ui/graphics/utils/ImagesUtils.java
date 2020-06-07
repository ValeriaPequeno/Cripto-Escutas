package pt.ual.android.bhjencryption.ui.graphics.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.List;

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

    /**
     * <p>This method combines two images into one by rendering them side by side (horizontally).</p>
     *
     * @param left The image that goes on the left side of the combined image.
     * @param right The image that goes on the right side of the combined image.
     * @return The combined image.
     */
    public static Bitmap combineBitmapsHorizontally(final Bitmap left, final Bitmap right){
        // Get the size of the images combined side by side.
        int width = left.getWidth() + right.getWidth();
        int height = left.getHeight() > right.getHeight() ? left.getHeight() : right.getHeight();

        // Create a Bitmap large enough to hold both input images and a canvas to draw to this
        // combined bitmap.
        Bitmap combined = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(combined);
        canvas.drawColor(Color.TRANSPARENT);

        // Render both input images into the combined bitmap and return it.
        canvas.drawBitmap(left, 0f, 0f, null);
        canvas.drawBitmap(right, left.getWidth() + right.getWidth(), 0f, null);

        return combined;
    }

    public static Bitmap combineBitmapsVertically(final Bitmap top, final Bitmap down) {
// Get the size of the images combined side by side.
        int width = top.getWidth() > down.getWidth() ? top.getWidth() : down.getWidth();
        int height = top.getHeight() + down.getHeight();

        // Create a Bitmap large enough to hold both input images and a canvas to draw to this
        // combined bitmap.
        Bitmap combined = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(combined);
        canvas.drawColor(Color.TRANSPARENT);

        // Render both input images into the combined bitmap and return it.
        canvas.drawBitmap(top, 0f, 0f, null);
        canvas.drawBitmap(down, 0f, top.getHeight() + down.getHeight(), null);

        return combined;

//        int topWidth = top.getWidth();
//        int topHeight = top.getHeight();
//        int downWidth = down.getWidth();
//        int downHeight = down.getHeight();
//
//        Bitmap finalTopBitmap = Bitmap.createBitmap(topWidth, topHeight, Bitmap.Config.ARGB_8888);
//        Bitmap finalDownBitmap = Bitmap.createBitmap(downWidth, downHeight, Bitmap.Config.ARGB_8888);
//        Bitmap finalBitmap = Bitmap.createBitmap(topWidth, topHeight + downHeight, Bitmap.Config.ARGB_8888);
//
//        Canvas c1 = new Canvas(b1);
//        view1.draw(c1);
//
//        Canvas c2 = new Canvas(b2);
//        view2.draw(c2);
//
//        Canvas canvas = new Canvas(b3);
//
//        canvas.drawBitmap(b2, new Matrix(), null);
//        canvas.drawBitmap(b1, 0, view2.getHeight(), null);
    }

    public static Bitmap printBitmap(int width, int height, List<Bitmap> messageBitmaps) {
        // Create a Bitmap large enough to hold both input images and a canvas to draw to this
        // combined bitmap.
        Bitmap combined = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(combined);
        canvas.drawColor(Color.TRANSPARENT);
        float printWidth = 0f;

        for (int i = 0; i < messageBitmaps.size(); i++) {
            Bitmap bitmap = messageBitmaps.get(i);
            // Render both input images into the combined bitmap and return it.
            if(i > 0)
                printWidth += (float) ((bitmap.getWidth() * 2) + 10);

            canvas.drawBitmap(bitmap, printWidth, 0f, null);
        }

        return combined;
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

    public static Bitmap getEmptyTransparentBitmap(int width, int height) {
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.TRANSPARENT);

        return image;
    }
}
