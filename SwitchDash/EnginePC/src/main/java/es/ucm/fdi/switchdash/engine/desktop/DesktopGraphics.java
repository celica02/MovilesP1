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

    public DesktopGraphics(Graphics drawGraphics, Window window)
    {
        this.canvas = drawGraphics;
        this.window = window;
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
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha)
    {
        canvas.drawImage(((DesktopImage)image).getImage(), dst.left, dst.top, dst.right, dst.bottom,
                src.left, src.top, src.right, src.bottom, null);
    }
}

