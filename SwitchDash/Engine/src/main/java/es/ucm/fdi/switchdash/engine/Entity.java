package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.InputEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

import java.util.List;

public abstract class Entity
{
    protected final Graphics g;

    protected Image img;
    protected MyRect src;
    protected int alpha;

    protected float posX, posY;
    protected float width, height;

    public Entity(Image image, MyRect source, Graphics graphics)
    {
        img = image;
        src = source;
        g = graphics;

        alpha = 255;

        posX = 0;
        posY = 0;

        width = source.right;
        height = source.bottom;
    }

    public Entity(Image image, MyRect source, Graphics graphics, int numRows, int numCols)
    {
        img = image;
        src = source;
        g = graphics;

        alpha = 255;

        posX = 0;
        posY = 0;

        width = source.right/numCols;
        height = source.bottom/numRows;
    }

    public Entity(Image image, MyRect source, MyRect dest, Graphics graphics)
    {
        img = image;
        src = source;
        g = graphics;

        alpha = 255;

        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }

    public Entity(Image image, MyRect source, float destX, float destY, Graphics graphics)
    {
        img = image;
        src = source;
        g = graphics;

        alpha = 255;

        posX = destX;
        posY = destY;

        width = source.right;
        height = source.bottom;
    }

    public abstract void update(float deltaTime);
    public abstract void handleInput(List<InputEvent> events, float deltaTime);
    public void render(float deltaTime)
    {
        MyRect dst = new MyRect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
        g.drawImage(img, src, dst, alpha);
    }

    public void setCentered() { posX = (g.getResWidth()/2) - (getWidth()/2); }

    public  void setAlpha(int a) { alpha = a; }

    public float getPosX() { return  posX; }
    public float getPosY() { return posY; }
    public void setPosX(float x) { posX = x; }
    public void setPosY(float y) { posY = y; }

    public float getWidth() { return width; }
    public float getHeight() { return  height; }
    public void setWidth(float w) { width = w; }
    public void setHeight(float h) { height = h; }

    public void setSize(float width, float height) { this.width = width; this.height = height; }


    public void setSourceRect(MyRect src) { this.src = src; }
    public void setDestRect(MyRect dest)
    {
        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }
}
