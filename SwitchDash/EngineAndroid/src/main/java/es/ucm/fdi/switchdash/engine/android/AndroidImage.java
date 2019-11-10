package es.ucm.fdi.switchdash.engine.android;

import android.graphics.Bitmap;
import es.ucm.fdi.switchdash.engine.Graphics.ImageFormat;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class AndroidImage implements es.ucm.fdi.switchdash.engine.Image
{
    // ATRIBUTES:

    private ImageFormat format;
    private Bitmap bitmap;

    // ------------------------------------------------ //


    public AndroidImage(Bitmap bitmap, ImageFormat format)
    {
    this.bitmap = bitmap;
    this.format = format;
    }

    public Bitmap getBitmap() { return  bitmap; }

    @Override
    public int getWidth() { return bitmap.getWidth(); }

    @Override
    public int getHeight() { return bitmap.getHeight(); }

    @Override
    public MyRect getFullRect() { return new MyRect(0, 0, getWidth(), getHeight()); }

    @Override
    public void dispose() { bitmap.recycle(); }
}
