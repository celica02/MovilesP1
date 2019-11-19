package es.ucm.fdi.switchdash.engine;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class Sprite extends Entity
{
    protected final Graphics g;

    protected Image img;

    protected MyRect src;

    protected float alpha;



    public Sprite(float posX, float posY, Image image, Graphics graphics)
    {
        super(posX, posY);
        img = image;
        src = image.getFullRect();
        width = src.right;
        height = src.bottom;

        g = graphics;

        alpha = 1;
    }
    public Sprite(float posX, float posY, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY);
        img = image;
        src = source;
        width = source.right;
        height = source.bottom;

        g = graphics;

        alpha = 1;
    }

    public Sprite(float posX, float posY, float width, float height, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY, width, height);

        img = image;
        src = source;
        g = graphics;

        alpha = 1;
    }


    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(float deltaTime)
    {
        MyRect dst = new MyRect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
        g.drawImage(img, src, dst, alpha);
    }

    @Override
    protected void handleTouchEvent(TouchEvent e) {

    }

    @Override
    protected void handleKeyEvent(KeyboardEvent e) {

    }

    @Override
    public void setCentered() { posX = (g.getResWidth()/2) - (getWidth()/2); }

    public  void setAlpha(float a) { alpha = a; }



    public void setSourceRect(MyRect src) { this.src = src; }
    public void setDestRect(MyRect dest)
    {
        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }
}
