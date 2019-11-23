package es.ucm.fdi.switchdash.engine;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class Sprite extends Entity
{
    protected Image img;

    protected MyRect src;

    protected float alpha;

    protected boolean visible = true;




    public Sprite(float posX, float posY, Image image, Graphics graphics)
    {
        super(posX, posY, graphics);
        img = image;
        src = image.getFullRect();
        width = src.right;
        height = src.bottom;


        alpha = 1;
    }
    public Sprite(float posX, float posY, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY, graphics);
        img = image;
        src = source;
        width = source.right;
        height = source.bottom;

        alpha = 1;
    }

    public Sprite(float posX, float posY, float width, float height, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY, width, height, graphics);

        img = image;
        src = source;

        alpha = 1;
    }

    @Override
    public void updateEntity(float deltaTime) {

    }

    @Override
    public void render(float deltaTime)
    {
        if(visible)
            renderEntity(deltaTime);
    }

    public void renderEntity(float deltaTime)
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


    public  void setAlpha(float a)
    {
        if(a > 1.0f)
            alpha = 1.0f;
        else if(a < 0.0f)
            alpha = 0.0f;
        else
            alpha = a;
    }
    public  float getAlpha() { return alpha; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean v) { visible = v; }




    public void setSourceRect(MyRect src) { this.src = src; }
    public void setDestRect(MyRect dest)
    {
        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }

    @Override
    public void reset(float x, float y)
    {
        super.reset(x, y);

        visible = true;
    }
}
