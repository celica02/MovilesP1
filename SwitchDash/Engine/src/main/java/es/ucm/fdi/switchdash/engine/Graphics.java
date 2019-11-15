package es.ucm.fdi.switchdash.engine;

import java.io.IOException;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 *  Provides the minimum graphic functionalities on the application window.
 */

public interface Graphics
{
    /**
     * Loads an image from its name stored in the application's resource container.
     *
     * @param name the pathname of the image to load
     * @return the loaded image
     */
    Image newImage(String name);


    /**
     * Draws the entire image.
     *
     * @param image the image to draw
     * @param x the destiny x
     * @param y the destiny y
     */
    public void drawImage(Image image, int x, int y, int alpha);

    /**
     *
     * @param image
     * @param dest
     */
    void drawImage(Image image, MyRect dest, int alpha);

    /**
     *
     * @param image
     * @param destX
     * @param destY
     * @param destWidth
     * @param destHeight
     */
    void drawImage(Image image, int destX, int destY, int destWidth, int destHeight, int alpha);

    /**
     * Draws a given part of the image.
     *
     * @param image the image to draw
     * @param source the source rect from the image which will be drawn
     * @param dest the dest rect where will be drawn
     */
    void drawImage(Image image, MyRect source, MyRect dest, int alpha);


    /**
     * Deletes the entire contents of the window, filling it with a color received as a parameter.
     *
     * @param color: the color with which to fill the screen
     */
    void clear(int color);


    /**
     *
     * @return the width of the window
     */
    int getWidth();

    /**
     *
     * @return  the height of the window
     */
    int getHeight();
}
