package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;


public class Points extends Text {

    int _points = 0;
    public Points (float posX, float posY, Graphics graphics){
        super(posX, posY, graphics);
        increasePoints(0);
    }

    public void increasePoints(int value)
    {
        _points += value;
        numberRepresentation(Integer.toString(_points));
    }

    public void numberRepresentation(String number)
    {
        representText(number);
        setPositionX(posX);
    }

    @Override
    public void setPositionX(float _posX){
        posX = _posX;
        System.out.println(_posX);

        for(int i = entities.size() - 1; i >= 0; i--){
            entities.get(i).setPosX(_posX);
            _posX -= entities.get(i).getWidth();
        }
    }

    public int getPoints() { return _points; }
}
