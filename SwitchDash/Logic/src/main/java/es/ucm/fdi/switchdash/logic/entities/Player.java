package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Player extends SpriteSheet
{
    public Player(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);
    }
}
