package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;

/**
 * Clase que gestiona los elementos del fondo (color del clear, las flechas y su color de fondo)
 */
public class Background extends EntitiesGroup {

    Integer[] clearColors = new Integer[]{0x41a85f, 0x00a885, 0x3d8eb9, 0x2969b0, 0x553982, 0x28324e, 0xf37934, 0xd14b41, 0x75706b};
    int _color;
    private float _speed;
    ArrowsBG arrowsBackground;

    // ---------- CONSTRUCTORA ---------- //
    public Background(float arrowSpeed, Graphics graphics)
    {
        super(graphics);
        entities = new ArrayList<>();
        _speed = arrowSpeed;
        init();

    }

    public void init()
    {
        newColor();
        SpriteSheet bgColor = new SpriteSheet(0,0, Assets.backgrounds, g, 1, 9, 0, _color);
        bgColor.setDestRect(new MyRect(0,0, Assets.arrowsBackground.getWidth(), Assets.arrowsBackground.getHeight()));
        addEntity(bgColor);

        arrowsBackground = new ArrowsBG(0, -Assets.arrowsBackground.getHeight()/5, Assets.arrowsBackground, g, _speed);
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

    }

    // ---------- FUNCIONES ---------- //

    public void setColor(int color){
        _color = color;
    }
    public void newColor(){
        _color = clearColors[new Random().nextInt(9)];
    }
    public int getColor(){
        return _color;
    }



    public void setSpeed(float speed) {
        _speed = speed;
        arrowsBackground.setSpeed(_speed);
    }

    public float getSpeed (){return arrowsBackground.getSpeed(); }


    @Override
    public void update(float deltaTime) {
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
}
