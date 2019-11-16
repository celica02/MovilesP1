package es.ucm.fdi.switchdash.engine.desktop;

import java.awt.Color;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import javax.swing.JFrame;


import es.ucm.fdi.switchdash.engine.AbstractGraphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.utils.MyRect;




public class DesktopGraphics extends AbstractGraphics
{
    private  final String assetsPath = "Assets/";
    private java.awt.Graphics canvas;
    private Window window;

    int resolutionWidth;
    int resolutionHeight;



    public DesktopGraphics(Graphics drawGraphics, Window window, int resWidth, int resHeight)
    {
        this.canvas = drawGraphics;
        this.window = window;

        this.resolutionWidth = resWidth;
        this.resolutionHeight = resHeight;
    }

    @Override
    public Image newImage(String pathname){
        try {
            java.awt.Image img = javax.imageio.ImageIO.read(new File(assetsPath + pathname));
            return new DesktopImage(img);
        }
        catch(Exception e){
            System.err.println(e);
            return null;
        }
    }


    public void drawLine(){
        canvas.setColor(Color.black);
        canvas.drawLine(1, 1, 400, 400);
    }

    @Override
    public void clear(int color)
    {
        canvas.setColor(new Color((color)));
        canvas.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public int getWidth() {
        return window.getWidth();
    }

    @Override
    public int getHeight() { return window.getHeight(); }

    @Override
    public int getResWidth() { return resolutionWidth; }
    @Override
    public int getResHeight() { return resolutionHeight; }



    @Override
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha)
    {
        canvas.drawImage(((DesktopImage)image).getImage(), dst.left, dst.top, dst.right, dst.bottom,
                src.left, src.top, src.right, src.bottom, null);
    }
}

