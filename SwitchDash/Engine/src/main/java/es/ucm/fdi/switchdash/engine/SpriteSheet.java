package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import java.util.List;

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

    public SpriteSheet(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols, int row, int col)
    {
        super(posX, posY, image, graphics);

        srcWidth = image.getWidth()/nCols;
        srcHeight = image.getHeight()/nRows;

        width = srcWidth;
        height = srcHeight;

        setActiveSprite(row, col);
    }


    @Override
    public void update(float deltaTime) {

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
