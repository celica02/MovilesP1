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


    public Image getImage() { return image; }


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

    @Override
    public MyRect getRect(int left, int top, int right, int bottom) { return new MyRect(left, top, right, bottom); }
}
