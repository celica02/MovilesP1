package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;


import java.util.List;

public abstract class Entity
{
    protected final Graphics g;

    protected float posX, posY;
    protected float width, height;
    protected int ID;

    protected boolean active = true;



    public Entity(Graphics graphics)
    {
        g = graphics;

        posX = 0;
        posY = 0;
    }

    public Entity(float posX, float posY, Graphics graphics){
        g = graphics;

        this.posX = posX;
        this.posY = posY;
    }

    public Entity(float posX, float posY, float width, float height, Graphics graphics){
        g = graphics;

        this.posX = posX;
        this.posY = posY;

        this.width = width;
        this.height = height;
    }

    public void update(float deltaTime)
    {
        if (active)
            updateEntity(deltaTime);
    }
    public abstract void updateEntity(float deltaTime);

    public void render(float deltaTime){}
    public void handleInput(List<TouchEvent> touchEvents, List<KeyboardEvent> keyEvents, float deltaTime)
    {
        if(active)
        {
            for (TouchEvent e : touchEvents)
                handleTouchEvent(e);

            for (KeyboardEvent e : keyEvents)
                handleKeyEvent(e);
        }
    }

    protected void handleTouchEvent(TouchEvent e){}
    protected void handleKeyEvent(KeyboardEvent e){}


    public boolean inBounds(float x, float y) {
        return (x > posX && x < posX + width - 1) &&
                (y > posY && y < posY + height - 1);
    }

    public boolean collides(Entity e) {
        return (posX < e.posX + e.width &&
                posX + width > e.posX &&
                posY < e.posY + e.height &&
                posY + height > e.posY);
    }


    public float getPosX() { return  posX; }
    public float getPosY() { return posY; }
    public void setPosX(float x) { posX = x; }
    public void setPosY(float y) { posY = y; }

    public float getWidth() { return width; }
    public float getHeight() { return  height; }
    public void setWidth(float w) { width = w; }
    public void setHeight(float h) { height = h; }

    public void setSize(float width, float height) { this.width = width; this.height = height; }

    public void setCenteredX() { posX = (g.getWidth()/2) - (getWidth()/2); }
    public void setCenteredY() { posY = (g.getHeight()/2) - (getHeight()/2);}
    public void setCentered() { posX = (g.getWidth()/2) - (getWidth()/2); posY = (g.getHeight()/2) - (getHeight()/2); }

    public void setID(int id){ID = id;}
    public int getID(){return ID;}

    public boolean isActive() { return active; }
    public void setActive(boolean a) { active = a; }

    public void reset(float x, float y)
    {
        active = true;
        posX = x;
        posY = y;
    }
    public  void decreaseID(){ ID--; }
}
