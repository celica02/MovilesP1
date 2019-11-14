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
    public DesktopGraphics(Graphics drawGraphics){
        _graphics = drawGraphics;
    }
    @Override
    public Image newImage(String name){
       /* try {
            java.awt.Image img = javax.imageio.ImageIO.read(new File(name));
            return new DesktopImage(img);
        }
        catch(Exception e){
            System.err.println(e);
            return null;
        }*/
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
    public void drawLine(){
        _graphics.setColor(Color.black);
        _graphics.drawLine(1, 1, 400, 400);
        System.out.println("dibujando");
    }

    @Override
    public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha) {

    }

    private java.awt.Graphics _graphics;
}
