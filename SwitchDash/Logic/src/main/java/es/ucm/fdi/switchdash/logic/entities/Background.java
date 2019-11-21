package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;

public class Background extends EntitiesGroup {

    float arrowsHeight;

    public Background(Graphics graphics)
    {
        super(graphics);
        entities = new ArrayList<>();

    }

    public void init()
    {
        float posY = 0;

        //Creación de las flechas, se colocan de arriba a abajo, teniendo la de arriba ID = 0
        addEntity(new ArrowsBG(0f, posY, Assets.arrowsBackground, g));
        entities.get(0).setPosY(-entities.get(0).getHeight());

        arrowsHeight = entities.get(0).getHeight(); //Guarda el tamaño de las flechas

        do { //Mientas no se haya completado el alto de la pantalla se siguen poniendo flechas

            addEntity(new ArrowsBG(0f, posY, Assets.arrowsBackground, g));
            posY += arrowsHeight;

        } while(posY < (g.getHeight()+ arrowsHeight));
    }

    @Override
    public void update(float deltaTime)
    {
        for(Entity currentArrow: entities)//Recorre todas las flechas para actualizarlas.
        {
            currentArrow.update(deltaTime);

            if(currentArrow.getPosY() >= (g.getHeight() + arrowsHeight))  //Comprueba si alguna ya ha pasado el límite por abajo.
            {
                System.out.println((currentArrow.getPosY()));
                if (currentArrow.getID() == entities.size() - 1) //Coloca la imagen encima de la que está más arriba
                    currentArrow.setPosY(entities.get(0).getPosY() - arrowsHeight);

                else
                    currentArrow.setPosY(entities.get(currentArrow.getID() + 1).getPosY() - arrowsHeight);
            }
        }
    }

    @Override
    public void render(float deltaTime)
    {
        for(Entity a: entities)
        {
            a.render(deltaTime);
        }
    }

    @Override
    public void handleInput(List<Input.TouchEvent> touchEvents, List<Input.KeyboardEvent> keyEvents, float deltaTime)
    {
       /* for(ArrowsBG a: entities){
            a.handleInput(touchEvents, keyEvents, deltaTime);
        }*/
    }

    @Override
    public void handleTouchEvent(Input.TouchEvent e)
    {

    }

    @Override
    public void handleKeyEvent(Input.KeyboardEvent e)
    {

    }

    @Override
    public void setCenteredX()
    {
        for(Entity a: entities)
        {
            a.setCenteredX();
        }

    }

    public void setAlpha(float alpha)
    {
        for(Entity a: entities)
        {
            ((ArrowsBG)a).setAlpha(alpha);
        }
    }


}
