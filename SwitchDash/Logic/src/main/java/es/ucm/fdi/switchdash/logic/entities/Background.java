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
    float arrowsHeight;

    public Background(Graphics graphics){
        g = graphics;
        backgrounds = new ArrayList<>();

    }
    public void init(){
        float posY = 0;
        int i = 1;
        //Creación de las flechas, se colocan de arriba a abajo, teniendo la de arriba ID = 0
        backgrounds.add(new ArrowsBG(0f, posY, Assets.arrowsBackground, g));
        arrowsHeight = backgrounds.get(0).getHeight();
        backgrounds.get(0).setPosY(-backgrounds.get(0).getHeight());
        do{
            backgrounds.add(new ArrowsBG(0f, posY, Assets.arrowsBackground, g));
            posY += backgrounds.get(0).getHeight();
            backgrounds.get(i).setID(i);
            i++;

        } while(posY < (g.getHeight()+ arrowsHeight));
    }

    @Override
    public void update(float deltaTime) {
        for(ArrowsBG currentArrow: backgrounds){ //Recorre todas las flechas para actualizarlas.
            currentArrow.update(deltaTime);

            if(currentArrow.getPosY() >= (g.getHeight() + arrowsHeight)) { //Comprueba si alguna ya ha pasado el límite por abajo.
                System.out.println((currentArrow.getPosY()));
                if (currentArrow.getID() == backgrounds.size() - 1) //Coloca la imagen encima de la que está más arriba
                    currentArrow.setPosY(backgrounds.get(0).getPosY() - arrowsHeight);
                else
                    currentArrow.setPosY(backgrounds.get(currentArrow.getID() + 1).getPosY() - arrowsHeight);
            }
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
