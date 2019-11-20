package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.Assets;

public class Points extends EntitiesGroup {

    Map<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
    float _posX, _posY;
    int nRows = 7, nCols = 15;

    private int points = 0;
    float increasingTime = 100, time = 0;

    public Points (float posX, float posY, Graphics graphics){
        g = graphics;
        entities = new ArrayList<>();
        _posX = posX;
        _posY = posY;
        init();
    }

    public void init(){
        map.put(0, new Integer[]{3, 7});
        map.put(1, new Integer[]{3, 8});
        map.put(2, new Integer[]{3, 9});
        map.put(3, new Integer[]{3, 10});
        map.put(4, new Integer[]{3, 11});
        map.put(5, new Integer[]{3, 12});
        map.put(6, new Integer[]{3, 13});
        map.put(7, new Integer[]{3, 14});
        map.put(8, new Integer[]{4, 0});
        map.put(9, new Integer[]{4, 1});

        addEntity(new SpriteSheet(_posX, _posY, Assets.scoreFont, g, nRows, nCols, (map.get(0))[0], (map.get(0))[1]));
    }

    public void increasePoints(int value){
        points += value;
        number(Integer.toString(points));
    }

    @Override
    public void update(float deltaTime)
    {

        super.update(deltaTime);
    }

    public void number(String number)
    {
        float posX = _posX;

        if(number.length() > entities.size())
            addEntity(new SpriteSheet(posX, _posY, Assets.scoreFont, g, nRows, nCols));

        for(int i = number.length() -1; i >= 0 ; i--){
            ((SpriteSheet)entities.get(i)).setActiveSprite((map.get(Character.getNumericValue(number.charAt(i)))[0]), (map.get(Character.getNumericValue(number.charAt(i))))[1]);
            entities.get(i).setPosX(posX);
            posX -= entities.get(0).getWidth();
            System.out.println(number.charAt(i));
        }
    }

    @Override
    protected void handleTouchEvent(Input.TouchEvent e)
    {
        increasePoints(1);

        super.handleTouchEvent(e);
    }

    @Override
    protected void handleKeyEvent(Input.KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    @Override
    public void setCentered() {
        //super.setCentered();
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }


}
