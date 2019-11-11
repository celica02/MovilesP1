package es.ucm.fdi.switchdash.engine.desktop;

import java.awt.Image;

import es.ucm.fdi.switchdash.engine.utils.MyRect;


public class DesktopImage implements es.ucm.fdi.switchdash.engine.Image
{
    Image image;

    public DesktopImage(Image image)
    {
        this.image = image;
    }

    @Override
    public int getWidth()
    {
        return image.getWidth(null);
    }

    @Override
    public  int getHeight()
    {
        return  image.getHeight(null);
    }

    @Override
    public void dispose() { }

    @Override
    public MyRect getFullRect() { return new MyRect(0, 0, getWidth(), getHeight()); }
}
