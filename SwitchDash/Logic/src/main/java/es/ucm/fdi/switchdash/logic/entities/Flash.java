package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;


/**
 * Entidad que simula un "flashazo" en toda ventana
 */
public class Flash extends BlinkingEntity
{

    private float iniAlpha;

    public Flash(Graphics graphics)
    {
        super(graphics);
        img = Assets.white;
        src = img.getFullRect();
        width = graphics.getWidth();
        height = graphics.getHeight();

        iniAlpha = alpha = 0.0f;
        alphaChange = 20.0f;
    }

    @Override
    public void updateEntity(float deltaTime)
    {
        super.updateEntity(deltaTime);

        // Ciclo realizado
        if(alpha == iniAlpha && deltaTime != 0)
        {
            setActive(false);
            setVisible(false);
        }
    }
}
