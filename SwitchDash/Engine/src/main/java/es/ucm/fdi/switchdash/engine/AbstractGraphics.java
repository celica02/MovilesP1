package es.ucm.fdi.switchdash.engine;

import org.w3c.dom.css.Rect;

import es.ucm.fdi.switchdash.engine.utils.MyRect;
public abstract class AbstractGraphics implements Graphics
{
    @Override
    public void drawImage(Image image, int x, int y, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect srcRect = new MyRect(0, 0, image.getWidth(), image.getHeight());
        MyRect dstRect = new MyRect(x, y, image.getWidth(), image.getHeight());

        drawImagePrivate(image,srcRect , dstRect, alpha);
    }

    @Override
    public void drawImage(Image image, MyRect dest, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect source = new MyRect(0, 0, image.getWidth(), image.getHeight());

        drawImagePrivate(image, source, dest, alpha);
    }

    @Override
    public void drawImage(Image image, int destX, int destY, int destWidth, int destHeight, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect srcRect = new MyRect(0, 0, image.getWidth(), image.getHeight());
        MyRect dstRect = new MyRect(destX, destY, destWidth, destHeight);

        drawImagePrivate(image,srcRect , dstRect, alpha);
    }

    @Override
    public void drawImage(Image image, MyRect source, MyRect dest, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
       // dest.left = 1920 / dest.left;
        //dest.top = 1920/ dest.top;
       // dest.right = 1080 / dest.right;
       // dest.bottom = 1080 / dest.bottom;


        drawImagePrivate(image, source, dest, alpha);
    }



    //abstract public void drawImagePrivate(Image image, int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int destHeight, int alpha);
    abstract public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha);
}
