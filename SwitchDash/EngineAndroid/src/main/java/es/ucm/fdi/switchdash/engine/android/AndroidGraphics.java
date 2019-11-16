package es.ucm.fdi.switchdash.engine.android;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.content.res.AssetManager;

import es.ucm.fdi.switchdash.engine.Image;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class AndroidGraphics extends es.ucm.fdi.switchdash.engine.AbstractGraphics
{

    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;


    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer)
    {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }


    @Override
    public Image newImage(String pathname)
    {
        Options options = new Options();
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(pathname);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + pathname + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + pathname + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) { }
            }
        }
        return new AndroidImage(bitmap);
    }

    @Override
    public void clear(int color)
    {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() { return frameBuffer.getHeight(); }



    @Override
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha)
    {
        Rect srcRect = transformRect(src);
        Rect dstRect =transformRect(dst);

        Paint paint = new Paint();
        paint.setAlpha(alpha);
        canvas.drawBitmap(((AndroidImage)image).getBitmap(), srcRect, dstRect, paint);
    }


    private Rect transformRect(MyRect rect) { return new Rect(rect.left, rect.top, rect.right, rect.bottom); }
}