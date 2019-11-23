package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;

public class Background extends EntitiesGroup {

    Integer[] clearColors = new Integer[]{0x41a85f, 0x00a885, 0x3d8eb9, 0x2969b0, 0x553982, 0x28324e, 0xf37934, 0xd14b41, 0x75706b};
    int _color;
    float arrowsHeight;
    private float speed;

    public Background(float arrowSpeed, Graphics graphics)
    {
        super(graphics);
        entities = new ArrayList<>();
        init();
        speed = arrowSpeed;

    }

    public void init()
    {
        newColor();
        SpriteSheet bgColor = new SpriteSheet(0,0, Assets.backgrounds, g, 1, 9, 0, _color);
        bgColor.setDestRect(new MyRect(0,0, Assets.arrowsBackground.getWidth(), Assets.arrowsBackground.getHeight()));
        addEntity(bgColor);

        ArrowsBG arrowsBackground = new ArrowsBG(0, -Assets.arrowsBackground.getHeight()/5, Assets.arrowsBackground, g);
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

    }
    public void setColor(int color){
        _color = color;
    }
    public void newColor(){
        _color = clearColors[new Random().nextInt(9)];
    }
    public int getColor(){
        return _color;
    }

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


    public void setSpeed(float speed) { this.speed = speed; }

    @Override
    protected void handleKeyEvent(Input.KeyboardEvent e) {
        super.handleKeyEvent(e);
    }
}
