package es.ucm.fdi.switchdash.engine.android.events;

import android.view.View;
import android.view.MotionEvent;

import es.ucm.fdi.switchdash.engine.Input.InputEvent;
import es.ucm.fdi.switchdash.engine.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class SingleTouchHandler implements TouchHandler
{

    // ----------ATTRIBUTES---------- //

    // State of the touchscreen
    boolean isTouched;

    // Position of the finger in the touchscreen
    int touchX;
    int touchY;

    // Pool of touch events
    Pool<InputEvent> touchEventPool;

    // Lists to hold the incoming touch events and the ones to be processed next
    List<InputEvent> touchEvents = new ArrayList<InputEvent>();
    List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();

    // Used to handle screen resolutions
    float scaleX;
    float scaleY;


    // ----------FUNCTIONS---------- //

    /**
     * Set up the handler to be used with touch events.
     * @param view the view where we register the handler as an OnTouchListener
     * @param scaleX screen scale X
     * @param scaleY screen scale Y
     */
    public SingleTouchHandler(View view, float scaleX, float scaleY)
    {

        // 1) Set up the pool to be used with touch events
        Pool.PoolObjectFactory<InputEvent> factory = new Pool.PoolObjectFactory<InputEvent>() {
            @Override
            public InputEvent createObject() {
                return new TouchEvent();
            }
        };

        touchEventPool = new Pool<InputEvent>(factory, 100);

        // 2) Register the handler as an OnTouchListener
        view.setOnTouchListener(this);

        // 3) Store the scale values
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }


    /**
     * This is called each time the view receives a new touch event.
     * Then, creates a new touch event and suits it up depending on the event received.
     * @param v the view receiving the events
     * @param event the event received
     * @return true
     */
    public boolean onTouch(View v, MotionEvent event)
    {
        // 1) First, we make sure that it is not accessed in parallel
        synchronized (this)
        {
            TouchEvent touchEvent = (TouchEvent) touchEventPool.newObject();

            // 2) Then, we set our touch event depending on the event received
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;

                case MotionEvent.ACTION_UP:
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;

                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                    isTouched = false;
                    break;

                case MotionEvent.ACTION_CANCEL:
            }

            // 3) Lastly, we multiply the coordinates by the scale
            touchEvent.x = touchX = (int)(event.getX() * scaleX);
            touchEvent.y = touchY = (int)(event.getY() * scaleY);

            // 4) Then, we just add the event to the list of events waiting to get handled
            touchEventsBuffer.add(touchEvent);

            return true;
        }
    }


    /**
     * Returns whether the passed pointer is pressed or not.
     * @param pointerID the pointer to look up whether is pressed.
     */
    @Override
    public boolean isTouchDown(int pointerID)
    {
       synchronized (this)
       {
           // As this handler is just for single-touch screens, we ensure to only return the correct value with the pointer ID = 0
           if(pointerID == 0)
               return isTouched;
           else
               return false;
       }
    }

    public int getTouchX(int pointerID)
    {
        synchronized(this) {
            return touchX;
        }
    }
    public int getTouchY(int pointerID)
    {
        synchronized (this) {
            return touchY;
        }
    }

    /**
     * Sets the old events free and returns the new events waiting in the events buffer.
     * @return the new events previously added to the events buffer
     */
    public List<InputEvent> getTouchEvents()
    {
        synchronized(this)
        {
            // 1) Sets the old events free adding them to the pool
            for(InputEvent e: touchEvents)
                touchEventPool.free(e);

            // 2) Adds the new events previously waiting the events buffer
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);

            touchEventsBuffer.clear();

            // 3) Finally, returns the new events list
            return touchEvents;
        }
    }
}
