package es.ucm.fdi.switchdash.engine.desktop;

import es.ucm.fdi.switchdash.engine.AbstractGraphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.utils.MyRect;


public class DesktopGraphics extends AbstractGraphics
{
    @Override
    public Image newImage(String name) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha) {

    }
}
