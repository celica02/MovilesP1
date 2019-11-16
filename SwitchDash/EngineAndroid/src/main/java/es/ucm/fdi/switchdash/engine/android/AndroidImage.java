package es.ucm.fdi.switchdash.engine.android;

import android.graphics.Bitmap;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class AndroidImage implements es.ucm.fdi.switchdash.engine.Image
{
    // ATRIBUTES:

    private Bitmap bitmap;

    // ------------------------------------------------ //


    public AndroidImage(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() { return  bitmap; }

    @Override
    public int getWidth() { return bitmap.getWidth(); }

    @Override
    public int getHeight() { return bitmap.getHeight(); }

    @Override
    public MyRect getFullRect() { return new MyRect(0, 0, getWidth(), getHeight()); }

    @Override
    public MyRect getRect(int left, int top, int right, int bottom) { return new MyRect(left, top, right, bottom); }

    @Override
    public void dispose() { bitmap.recycle(); }
}
