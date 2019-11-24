package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;

/**
 * Clase encargada de la visualización de los puntos durante la partida
 */
public class Points extends Text {

    private int _points = 0;

    // ---------- CONSTRUCTORA ---------- //
    public Points (float posX, float posY, Graphics graphics){
        super(posX, posY, graphics);
        increasePoints(0);
    }

    // ---------- FUNCIONES ---------- //
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

    /**
     * Posiciona los números de los puntos. Coloca el último dígito en la posición
     * pasada por parámetro y los demás los va colocando a la izquierda del anterior
     *
     * @param _posX posición en X en la que se van a colocar las unidades
     */
    @Override
    public void setPositionX(float _posX){
        posX = _posX;

        for(int i = entities.size() - 1; i >= 0; i--){
            entities.get(i).setPosX(_posX);
            _posX -= entities.get(i).getWidth();
        }
    }

    public int getPoints() { return _points; }
}
