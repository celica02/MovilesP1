package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 *  Provides the minimum graphic functionalities on the application window.
 */

public interface Graphics
{
    /**
     * Loads an image from its name stored in the application's resource container.
     *
     * @param pathname the pathname of the image to load
     * @return the loaded image
     */
    Image newImage(String pathname);


    /**
     * Draws the entire image.
     *
     * @param image the image to draw
     * @param x the destiny x
     * @param y the destiny y
     */
    public void drawImage(Image image, int x, int y, float alpha);

    /**
     *
     * @param image
     * @param dest
     */
    void drawImage(Image image, MyRect dest, float alpha);

    /**
     *
     * @param image
     * @param destX
     * @param destY
     * @param destWidth
     * @param destHeight
     */
    void drawImage(Image image, int destX, int destY, int destWidth, int destHeight, float alpha);

    /**
     * Draws a given part of the image.
     *  @param image the image to draw
     * @param source the source rect from the image which will be drawn
     * @param dest the dest rect where will be drawn
     * @param alpha
     */
    void drawImage(Image image, MyRect source, MyRect dest, float alpha);


    /**
     * Deletes the entire contents of the window, filling it with a color received as a parameter.
     *
     * @param color: the color with which to fill the screen
     */
    void clear(int color);


    void dispose();

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


    int getResWidth();
    int getResHeight();


    float logicX(float x);
    float logicY(float y);
}
