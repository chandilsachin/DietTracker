package com.chandilsachin.diettracker.io;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class BitmapsManager
{

    /**
     * <h1>protected static int calculateInSampleSize(BitmapFactory.Options
     * options,int reqWidth, int reqHeight)</h1>
     * <p>
     * Calculates sample size of bitmap.
     * </p>
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    protected static int calculateInSampleSize(BitmapFactory.Options options,
                                               int reqWidth, int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth)
        {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth)
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * <h1>public static Bitmap decodeSampledBitmap(String filePath,int
     * reqWidth, int reqHeight)</h1>
     * <p>
     * Decodes bitmap of given size.
     * </p>
     *
     * @param filePath  - file path of image.
     * @param reqWidth  - requested width
     * @param reqHeight - requested height
     * @return a bitmap of given width and height.
     */
    public static Bitmap decodeSampledBitmap(String filePath, int reqWidth,
                                             int reqHeight)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

   /* public static Bitmap decodeScaledSampleBitmap(Resources res, int resId,
        int reqWidth, int reqHeight)
    {

	// First decode with inJustDecodeBounds=true to check dimensions
	final BitmapFactory.Options options = new BitmapFactory.Options();
	options.inJustDecodeBounds = true;
	BitmapFactory.decodeResource(res, resId, options);

	// Calculate inSampleSize
	options.inSampleSize = calculateInSampleSize(options, reqWidth,
		reqHeight);

	// Decode bitmap with inSampleSize set
	options.inJustDecodeBounds = false;
	return BitmapFactory.decodeResource(res, resId, options);
    }*/

    /**
     * <h1>public static Bitmap decodeSampledBitmap(String filePath)</h1>
     * <p>
     * Decodes bitmap of given size.
     * </p>
     *
     * @param filePath - file path of image.
     * @return a bitmap of given width and height.
     */
    public static Bitmap decodeSampledBitmap(String filePath)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, options.outWidth,
                options.outHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
    /*ByteArrayOutputStream out = new ByteArrayOutputStream();
    if(bitmap.compress(CompressFormat.JPEG, 20, out))
	{
	    System.out.println("Done!---------------------------------");
	}*/
        return bitmap;
    }

    public static BitmapDrawable decodeSampledBitmapDrawable(Resources res,
                                                             String filePath)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, options.outWidth,
                options.outHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return new BitmapDrawable(res, BitmapFactory.decodeFile(filePath,
                options));
    }

    public static NinePatchDrawable decodeNinePatchDrawable(Resources res,
                                                            String filePath)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, options.outWidth,
                options.outHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        NinePatch patch = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
        return new NinePatchDrawable(res, patch);
    }

    public static NinePatchDrawable getNinePatchDrawable(Resources res, Bitmap bitmap)
    {
        NinePatch patch = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
        return new NinePatchDrawable(res, patch);
    }

    public static BitmapDrawable decodeSampledBitmapDrawable(Resources res,
                                                             String filePath, int heightInPixel)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        float widthRatio = options.outWidth / options.outHeight;

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,
                (int) (widthRatio * heightInPixel), heightInPixel);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap b = Bitmap.createScaledBitmap(
                BitmapFactory.decodeFile(filePath, options),
                (int) (widthRatio * heightInPixel), heightInPixel, true);

        return new BitmapDrawable(res, b);
    }

    public static Bitmap decodeScaledSampleBitmap(Resources res,
                                                  int resourceId, int width, int height)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resourceId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,
                width, height);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap b = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(res, resourceId, options),
                width, height, true);

        return b;
    }

    public static BitmapDrawable decodeSampledBitmapDrawableInDp(Resources res,
                                                                 String filePath, int heightInPixel)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        float widthRatio = 0f;
        try
        {
            widthRatio = (float) options.outWidth / options.outHeight;
        } catch (ArithmeticException e)
        {
        }
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,
                (int) (widthRatio * heightInPixel) + 30, heightInPixel);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap b = null;
        try
        {
            b = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(filePath,
                    options),
                    (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            (int) (widthRatio * heightInPixel),
                            res.getDisplayMetrics()), (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                    heightInPixel, res.getDisplayMetrics()),
                    true);


        } catch (NullPointerException e)
        {
            return null;
        }
        return new BitmapDrawable(res, b);
    }

    public static Bitmap decodeSampledBitmapInDp(Resources res,
                                                 String filePath, int heightInPixel)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        float widthRatio = 0f;
        try
        {
            widthRatio = options.outWidth / options.outHeight;
        } catch (ArithmeticException e)
        {
        }
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,
                (int) (widthRatio * heightInPixel) + 30, heightInPixel);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap b = null;
        try
        {
            b = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(filePath,
                    options),
                    (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            (int) (widthRatio * heightInPixel),
                            res.getDisplayMetrics()), (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                    heightInPixel, res.getDisplayMetrics()),
                    true);
        } catch (NullPointerException e)
        {
            return null;
        }
        return b;
    }

    public static BitmapDrawable decodeBitmapDrawable(Resources res,
                                                      String filePath)
    {

        return (BitmapDrawable) BitmapDrawable.createFromPath(filePath);
    }

    /**
     * <h1>public static Bitmap decodeSampledBitmap(Resources res, int resId,int
     * reqWidth, int reqHeight)</h1>
     * <p>
     * Decodes bitmap of given size.
     * </p>
     *
     * @param res       - resource instance
     * @param resId     - resource id of file
     * @param reqWidth  - requested width
     * @param reqHeight - requedted height
     * @return a bitmap of given width and height.
     */
    public static Bitmap decodeSampledBitmap(Resources res, int resId,
                                             int reqWidth, int reqHeight)
    {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    /**
     * <h1>public static Bitmap createBitmap(View v)</h1>
     * <p>
     * Creates a bitmap from a view.
     * </p>
     */
    public static Bitmap createBitmap(View v)
    {
        if (v.getMeasuredHeight() <= 0)
        {
            v.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(),
                    v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            return b;
        } else
        {
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                    Bitmap.Config.ARGB_8888);

            // Bitmap b =
            // Bitmap.createBitmap(v.getLayoutParams().width,v.getLayoutParams().height,
            // Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getRight());
            v.draw(c);
            return b;
        }

    }

}
