package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Ball extends SpriteSheet
{

    float scaleFactor = 1.0f;

    private float speed;
    float speedUpTime = 0.1f;
    float speedUpCounter = 0;

    public Ball(float posX, float posY, float ballSpeed, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);

        speed = ballSpeed;
    }
    public Ball(float posX, float posY, float ballSpeed, Image image, Graphics graphics, int nRows, int nCols, int row, int col) {
        super(posX, posY, image, graphics, nRows, nCols, row, col);

        speed = ballSpeed;
    }

    @Override
    public void updateEntity(float deltaTime)
    {
        posY  = posY + speed * deltaTime;

//        System.out.println("Speed: " + speed);
    }

    public void setSpeed(float s) { speed = s; }

    public int getCurrentColor(){return activeRow; }
}
