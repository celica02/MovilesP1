package es.ucm.fdi.switchdash.engine.android.events;

import android.view.View.OnTouchListener;
import es.ucm.fdi.switchdash.engine.Input.InputEvent;

import java.util.List;




/**
 * Registers a handler with a view and provides input handling functions.
 */
public interface TouchHandler extends OnTouchListener
{
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public List<InputEvent> getTouchEvents();
}
