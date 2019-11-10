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
    interface InputEvent
    {
    }


    /**
     *
     * @return the list of events received since the last invocation
     */
    List<InputEvent> getInputEvents();
}
