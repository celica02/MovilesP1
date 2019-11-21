package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Ball extends SpriteSheet {

    private boolean _active = true;

    public Ball(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);
    }
    public Ball(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols, int row, int col) {
        super(posX, posY, image, graphics, nRows, nCols, row, col);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        posY  = posY+430 * deltaTime;
        if(posY >1500)
            setActive(false);
    }

    @Override
    protected void handleTouchEvent(Input.TouchEvent e) { super.handleTouchEvent(e); }

    @Override
    protected void handleKeyEvent(Input.KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    @Override
    public void render(float deltaTime) {
        if(isActive())
            super.render(deltaTime);
    }

    public void setActive(boolean active){
        _active = active;
    }

    public boolean isActive(){ return _active; }
    public int getColor(){return src.top;}
}
