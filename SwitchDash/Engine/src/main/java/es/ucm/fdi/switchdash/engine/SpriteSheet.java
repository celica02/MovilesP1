package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import java.util.List;

public class SpriteSheet extends Sprite
{
    protected float srcWidth;
    protected float srcHeight;

    int activeRow;
    int activeCol;


    public SpriteSheet(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols)
    {
        super(posX, posY, image, graphics);

        srcWidth = image.getWidth()/nCols;
        srcHeight = image.getHeight()/nRows;

        width = srcWidth;
        height = srcHeight;

       setActiveSprite(0, 0);
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

    public void setActiveSprite(int row, int col)
    {
        activeRow = row;
        activeCol = col;

        src.left = (int)srcWidth * col;
        src.top = (int)srcHeight * row;

        src.right = (int)(src.left + srcWidth);
        src.bottom = (int)(src.top + srcHeight);
    }

    public int getActiveRow() { return activeRow; }
    public int getActiveCol() { return activeCol; }
}
