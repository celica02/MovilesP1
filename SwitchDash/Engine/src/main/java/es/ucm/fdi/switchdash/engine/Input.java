package es.ucm.fdi.switchdash.engine;
import java.util.List;

/**
 * Provee funcionalidades básicas para el input.
 */
public interface Input
{

    /**
     * Representa la información de los eventos de pulsación (o de click del ratón)
     * Indica el tipo (touch, release, drag), la posición, y el identificador del "dedo" (o el botón).
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

    /**
     * Representa la información de los eventos de teclado (pulsación de teclas).
     */
     class KeyboardEvent
    {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public char keyChar;
    }


    /**
     * @return la lista de eventos de pulsación recibidos desde la última invocación
     */
    List<TouchEvent> getTouchEvents();
    /**
     * @return la lista de eventos de teclado recibidos desde la última invocación
     */
    List<KeyboardEvent> getKeyEvents();
}
