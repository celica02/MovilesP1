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

    protected int posX, posY;
    protected int width, height;

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

    public Entity(Image image, MyRect source, int destX, int destY, Graphics graphics)
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
    public abstract void render(float deltaTime);
    public abstract void handleInput(List<InputEvent> events, float deltaTime);

    public abstract void drawCentered();

    public  void setAlpha(int a) { alpha = a; }

    public int getPosX() { return  posX; }
    public int getPosY() { return posY; }
    public void setPosX(int x) { posX = x; }
    public void setPosY(int y) { posY = y; }

    public int getWidth() { return width; }
    public int getHeight() { return  height; }
    public void setWidth(int w) { width = w; }
    public void setHeight(int h) { height = h; }

    public void setDestRect(MyRect dest)
    {
        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }
}
