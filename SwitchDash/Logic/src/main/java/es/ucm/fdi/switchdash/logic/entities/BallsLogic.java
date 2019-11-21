package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;

public class BallsLogic extends EntitiesGroup {

    Ball b;
    float _posYIni;
    int _ballType = 0;

    public BallsLogic(float posYIni, Graphics graphics){
        g = graphics;
        entities = new ArrayList<>();
        _posYIni = posYIni;
        init();
    }
    public BallsLogic(float posYIni, Graphics graphics, int ballType) {
        g = graphics;
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
    public void setCentered() {
        super.setCentered();
    }

    private Ball newBall(){
        for(Entity e: entities){
            if(!((Ball)e).isActive()){
                b = (Ball)e;

                moveToTop(b);

                b.setActiveSprite(random(((Ball)entities.get(b.getID() - 1)).getColor()), _ballType);
                b.setPosY(_posYIni);
                b.setActive(true);

                return b;
            }
        }

        b = new Ball(0, _posYIni, Assets.balls, g, 2, 10,random(((Ball)entities.get(entities.size() - 1)).getColor()) ,_ballType);
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
        else previous /= previous;

        float a = (float)Math.random();
        return a < 0.7 ? previous : newC;
    }

}
