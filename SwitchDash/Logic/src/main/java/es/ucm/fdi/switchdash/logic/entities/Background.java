package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;

public class Background extends Entity {
    Graphics g;
    protected List<ArrowsBG> backgrounds;

    public Background(Graphics graphics){
        g = graphics;
        backgrounds = new ArrayList<>();

    }
    public void init(){
        for (int i = 0; i < 3; i++) {
            backgrounds.add(new ArrowsBG(0f, 0f, Assets.arrowsBackground, g));
        }

            /*ArrowsBG arrowsBG0 = new ArrowsBG(0f, Assets.arrowsBackground.getHeight(), Assets.arrowsBackground, g);
            ArrowsBG arrowsBG1 = new ArrowsBG(0f, 0F, Assets.arrowsBackground, g);
            ArrowsBG arrowsBG2 = new ArrowsBG(0f, -Assets.arrowsBackground.getHeight(), Assets.arrowsBackground, g);
            backgrounds.add(new ArrowsBG(0f, Assets.arrowsBackground.getHeight(), Assets.arrowsBackground, g));
            backgrounds.add(new ArrowsBG(0f, 0F, Assets.arrowsBackground, g));
            backgrounds.add(new ArrowsBG(0f, -Assets.arrowsBackground.getHeight(), Assets.arrowsBackground, g));
        //}*/
        backgrounds.get(0).setPosY(Assets.backgrounds.getHeight() * g.getResHeight());
        backgrounds.get(2).setPosY(-(Assets.backgrounds.getHeight() * g.getResHeight()));
    }

    @Override
    public void update(float deltaTime) {
        for(ArrowsBG a: backgrounds){
            a.update(deltaTime);
            if(a.getPosY() >= g.getHeight())
                a.setPosY(-Assets.backgrounds.getHeight());
        }
    }

    @Override
    public void render(float deltaTime) {
        for(ArrowsBG a: backgrounds){
            a.render(deltaTime);
        }
    }

    @Override
    public void handleInput(List<Input.TouchEvent> touchEvents, List<Input.KeyboardEvent> keyEvents, float deltaTime) {
        for(ArrowsBG a: backgrounds){
            a.handleInput(touchEvents, keyEvents, deltaTime);
        }
    }

    @Override
    public void setCentered() {
        for(ArrowsBG a: backgrounds){
            a.setCentered();
        }

    }

    public void setAlpha(float alpha){
        for(ArrowsBG a: backgrounds){
            a.setAlpha(alpha);
        }
    }


}
