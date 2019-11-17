package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class SpriteSheet extends Sprite
{
    protected int srcWidth;
    protected int srcHeight;


    public SpriteSheet(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols)
    {
        super(posX, posY, image, graphics);

        srcWidth = image.getWidth()/nCols;
        srcHeight = image.getHeight()/nRows;

        width = srcWidth;
        height = srcHeight;
    }


    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void handleInput(List<Input.InputEvent> events, float deltaTime) {

    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }

    public void setActiveSprite(int row, int col)
    {
        src.left = srcWidth * col;
        src.top = srcHeight * row;

        src.right = src.left + srcWidth;
        src.bottom = src.top + srcHeight;
    }
}
