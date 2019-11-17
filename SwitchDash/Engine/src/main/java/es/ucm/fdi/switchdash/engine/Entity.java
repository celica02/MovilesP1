package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.InputEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

import java.util.List;

public abstract class Entity
{
    protected float posX, posY;


    public Entity()
    {
        posX = 0;
        posY = 0;
    }

    public Entity(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void update(float deltaTime);
    public abstract void render(float deltaTime);
    public abstract void handleInput(List<InputEvent> events, float deltaTime);


    public float getPosX() { return  posX; }
    public float getPosY() { return posY; }
    public void setPosX(float x) { posX = x; }
    public void setPosY(float y) { posY = y; }

    public abstract void setCentered();
}
