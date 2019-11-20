package es.ucm.fdi.switchdash.engine.android;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
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


    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer, int resWidth, int resHeight)
    {
        super(resWidth, resHeight);
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }


    public Image newImage(String pathname)
    {
        InputStream in = null;
        Bitmap bitmap;

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

    public void clear(int color)
    {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    public void dispose(){}

    @Override
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, float alpha)
    {
        Rect srcRect = transformRect(src);
        Rect dstRect = transformRect(dst);

        Paint paint = new Paint();
        paint.setAlpha((int)(alpha*255));
        canvas.drawBitmap(((AndroidImage)image).getBitmap(), srcRect, dstRect, paint);
    }


    public int getWidth() { return frameBuffer.getWidth(); }
    public int getHeight() { return frameBuffer.getHeight(); }

    public int getResWidth() { return resolutionWidth; }
    public int getResHeight() { return resolutionHeight; }


    private Rect transformRect(MyRect rect) { return new Rect(rect.left, rect.top, rect.right, rect.bottom); }
}
