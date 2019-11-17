package es.ucm.fdi.switchdash.engine.desktop;

import java.util.List;
import java.util.ArrayList;

import es.ucm.fdi.switchdash.engine.desktop.events.KeyboardHandler;
import es.ucm.fdi.switchdash.engine.desktop.events.MouseHandler;

public class DesktopInput implements es.ucm.fdi.switchdash.engine.Input
{

    // ----------ATTRIBUTES---------- //

       private KeyboardHandler keyHandler;
       private MouseHandler mouseHandler;


    // ----------FUNCTIONS---------- //

    public DesktopInput(float resolutionWidth, float resolutionHeight)
    {
        mouseHandler = new MouseHandler(resolutionWidth, resolutionHeight);
        keyHandler = new KeyboardHandler();
    }


    @Override
    public List<TouchEvent> getTouchEvents() { return mouseHandler.getMouseEvents(); }

    @Override
    public List<KeyboardEvent> getKeyEvents() { return keyHandler.getKeyEvents(); }
}
