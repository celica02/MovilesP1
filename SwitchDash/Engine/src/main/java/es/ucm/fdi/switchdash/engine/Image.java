package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 * Wraps a bitmap image to then be used like an sprite.
 */
public interface Image
{
    /**
     *
     * @return width of the image
     */
    int getWidth();

    /**
     *
     * @return height of the image
     */
    int getHeight();

    MyRect getFullRect();

    MyRect getRect(int left, int top, int right, int bottom);
    /**
     * disposes the image
     */
    public void dispose();

}
