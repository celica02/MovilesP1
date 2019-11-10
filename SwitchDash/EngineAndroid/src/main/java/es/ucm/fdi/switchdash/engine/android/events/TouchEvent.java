package es.ucm.fdi.switchdash.engine.android.events;

import es.ucm.fdi.switchdash.engine.Input;

public class TouchEvent implements Input.InputEvent
{
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int pointerID;
        public int type;

        public int x, y;
}
