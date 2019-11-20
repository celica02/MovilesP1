package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;

public class BallsLogic extends EntitiesGroup {

    Ball b;
    float posYIni = -20;
    public BallsLogic(Graphics graphics){
        g = graphics;
        entities = new ArrayList<>();
        init();
    }

    public void init(){
        addEntity(new Ball(0, posYIni, Assets.balls, g, 2, 10, random(1), 0));
    }


    @Override
    public void update(float deltaTime) {

        if(entities.get(entities.size() - 1).getPosY() >= 300){
           b = new Ball(0, posYIni, Assets.balls, g, 2, 10,random(((Ball)entities.get(entities.size() - 1)).getColor()) ,0);
           addEntity(b);
           b.setCentered();
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

    private int random(int previous){
        int newC = 0;
        if (previous == 0) newC = 1;
        else previous /= previous;

        float a = (float)Math.random();
        return a < 0.7 ? previous : newC;
    }

}
