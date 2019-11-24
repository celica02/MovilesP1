package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import java.util.List;


/**
 * Sprite formado por una imagen dada como spritesheet
 */
public class SpriteSheet extends Sprite
{
    protected float srcWidth;
    protected float srcHeight;

    protected int activeRow;
    protected int activeCol;

    // ---------- CONSTRUCTORAS ---------- //

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

    // ----------- FUNCIONES ---------- //

    /**
     * Cambia el sprite activo del spritesheet
     *
     * @param row fila del nuevo sprite
     * @param col columna del nuevo sprite
     */
    public void setActiveSprite(int row, int col)
    {
        activeRow = row;
        activeCol = col;

        src.left = (int)srcWidth * col;
        src.top = (int)srcHeight * row;

        src.right = (int)(src.left + srcWidth);
        src.bottom = (int)(src.top + srcHeight);
    }

    /**
     * @return la fila activa del sprite
     */
    public int getActiveRow() { return activeRow; }
    /**
     * @return la columna activa del sprite
     */
    public int getActiveCol() { return activeCol; }


    /**
     * Cambia la fila del spritesheet
     *
     * @param row fila del nuevo sprite
     */
    public void setActiveRow(int row){
        activeRow = row;
        src.top = (int)srcHeight * row;
        src.bottom = (int)(src.top + srcHeight);
    }

    /**
     * Cambia la columna del spritesheet
     *
     * @param col columna del nuevo sprite
     */
    public void setActiveCol(int col){
        activeCol = col;
        src.left = (int)srcWidth * col;
        src.right = (int)(src.left + srcWidth);
    }
}
