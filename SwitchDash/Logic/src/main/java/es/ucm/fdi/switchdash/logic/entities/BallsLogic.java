package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;

public class BallsLogic extends EntitiesGroup {

    Ball b;
    float _posYIni;
    int _ballType = 0;


    public BallsLogic(Graphics graphics, float posYIni){
        super(graphics);
        entities = new ArrayList<>();
        _posYIni = posYIni;
        init();
    }
    public BallsLogic(float posYIni, Graphics graphics, int ballType) {
        super(graphics);
        entities = new ArrayList<>();
        _posYIni = posYIni;
        _ballType = ballType;
        init();
    }

    public void init(){
        addEntity(new Ball(0, _posYIni, Assets.balls, g, 2, 10, random(1), _ballType));
    }


    @Override
    public void update(float deltaTime) {

        if(entities.get(entities.size() - 1).getPosY() >= 395){
           newBall();
           System.out.println(entities.size());
        }

        super.update(deltaTime);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }

    @Override
    protected void handleTouchEvent(Input.TouchEvent e) {
        super.handleTouchEvent(e);
    }

    @Override
    protected void handleKeyEvent(Input.KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    @Override
    public void setCenteredX() {
        super.setCenteredX();
    }

    private Ball newBall(){
        for(Entity e: entities){
            if(!e.isActive()){
                moveToTop(e);

                ((Ball)e).setActiveSprite(random(((Ball)entities.get(e.getID() - 1)).getActiveRow()), _ballType);
                e.setPosY(_posYIni);
                e.setActive(true);

                return (Ball)e;
            }
        }

        b = new Ball(0, _posYIni, Assets.balls, g, 2, 10,random(((Ball)entities.get(entities.size() - 1)).getActiveRow()) ,_ballType);
        addEntity(b);
        b.setCentered();
        return b;

    }

    /**
     * Method that returns randomly witch color will be the new ball.
     * 70% of possibilities to be the same colour as the previous ball
     * @param previous: color of the previous ball
     * @return the new colour
     */
    private int random(int previous){
        int newC = 0;
        if (previous == 0) newC = 1;

        float a = (float)Math.random();
        return a < 0.7 ? previous : newC;
    }

}
