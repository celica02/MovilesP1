package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;


import java.util.List;

public abstract class Entity
{
    protected float posX, posY;
    protected float width, height;


    public Entity()
    {
        posX = 0;
        posY = 0;
    }

    public Entity(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
    }

    public Entity(float posX, float posY, float width, float height){
        this.posX = posX;
        this.posY = posY;

        this.width = width;
        this.height = height;
    }

    public abstract void update(float deltaTime);
    public abstract void render(float deltaTime);
    public void handleInput(List<TouchEvent> touchEvents, List<KeyboardEvent> keyEvents, float deltaTime)
    {
        for (TouchEvent e: touchEvents)
            handleTouchEvent(e);

        for (KeyboardEvent e: keyEvents)
            handleKeyEvent(e);
    }

    protected abstract void handleTouchEvent(TouchEvent e);
    protected abstract void handleKeyEvent(KeyboardEvent e);


    protected boolean inBounds(float x, float y) {
        return (x > posX && x < posX + width - 1) &&
                (y > posY && y < posY + height - 1);
    }

    protected boolean collides(Entity e) {
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

    public abstract void setCentered();
}
