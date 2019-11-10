package es.ucm.fdi.switchdash.engine.pc.events;

import es.ucm.fdi.switchdash.engine.Input;

public class KeyboardEvent implements Input.InputEvent
{
    public static final int KEY_DOWN = 0;
    public static final int KEY_UP = 1;

    public int type;
    public int keyCode;
    public char keyChar;
}
