package es.ucm.fdi.switchdash.engine;
import java.util.List;

/**
 * Provides basic input functionalities. The game does not require
 * a complex interface, so only screen touch (or mouse click) is used in this case.
 */
public interface Input
{

    /**
     * Represents screen touch (or mouse event) information.
     * Indicates which type (touch, release, drag), position, and identifier of the "finger" (or button).
     */
    class TouchEvent
    {
        public static final int DOWN = 0;
        public static final int UP = 1;
        public static final int DRAG = 2;

        public int pointerID;
        public int type;

        public int x, y;
    }

     class KeyboardEvent
    {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public char keyChar;
    }


    /**
     *
     * @return the list of events received since the last invocation
     */
    List<TouchEvent> getTouchEvents();

    List<KeyboardEvent> getKeyEvents();
}
