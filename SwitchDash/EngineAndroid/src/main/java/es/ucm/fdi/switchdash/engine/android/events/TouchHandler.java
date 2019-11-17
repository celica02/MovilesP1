package es.ucm.fdi.switchdash.engine.android.events;

import android.view.View.OnTouchListener;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;

import java.util.List;


/**
 * Registers a handler with a view and provides input handling functions.
 */
public interface TouchHandler extends OnTouchListener
{
     boolean isTouchDown(int pointer);

     int getTouchX(int pointer);
     int getTouchY(int pointer);

     List<TouchEvent> getTouchEvents();
}
