package es.ucm.fdi.switchdash.engine.pc;

import java.util.List;

import es.ucm.fdi.switchdash.engine.pc.events.KeyboardHandler;

public class Input implements es.ucm.fdi.switchdash.engine.Input
{

    // ----------ATTRIBUTES---------- //

        KeyboardHandler keyHandler;


    // ----------FUNCTIONS---------- //

    public Input()
    {
        keyHandler = new KeyboardHandler();
    }

    public boolean isKeyPressed(int keyCode) { return keyHandler.isKeyPressed(keyCode); }


    @Override
    public List<InputEvent> getInputEvents() { return keyHandler.getKeyEvents(); }
}
