package es.ucm.fdi.switchdash.engine.desktop.events;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import es.ucm.fdi.switchdash.engine.Input.InputEvent;
import es.ucm.fdi.switchdash.engine.utils.Pool;

import java.util.ArrayList;
import java.util.List;


public class KeyboardHandler extends JFrame implements KeyListener
{
    // ----------ATTRIBUTES---------- //

    boolean[] pressedKeys = new boolean[128];

    // Pool of key events
    Pool<InputEvent> keyEventPool;

    // Lists to hold the incoming key events and the ones to be processed next
    List<InputEvent> keyEventsBuffer = new ArrayList<InputEvent>();
    List<InputEvent> keyEvents = new ArrayList<InputEvent>();


    // ----------FUNCTIONS---------- //

    /**
     * Set up the handler to be used with keyboard events.
     */
    public KeyboardHandler()
    {
        // 1) Set up the pool to be used with keyboard events
        Pool.PoolObjectFactory<InputEvent> factory = new Pool.PoolObjectFactory<InputEvent>() {
            @Override
            public InputEvent createObject() {
                return new KeyboardEvent();
            }
        };

        keyEventPool = new Pool<InputEvent>(factory, 100);

        // 2) Register the handler as a key listener
        addKeyListener(this);
    }


    /**
     * Returns whether the passed key is pressed or not.
     * @param keyCode the key to look up whether is pressed.
     */
    public boolean isKeyPressed(int keyCode)
    {
        if (keyCode < 0 || keyCode > 127)
            return false;

        return pressedKeys[keyCode];
    }

    /**
     * Sets the old events free and returns the new events waiting in the events buffer.
     * @return the new events previously added to the events buffer
     */
    public List<InputEvent> getKeyEvents()
    {
        synchronized (this)
        {
            // 1) Sets the old events free adding them to the pool
            for(InputEvent e: keyEvents)
                keyEventPool.free(e);

            // 2) Adds the new events previously waiting in the events buffer
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);

            keyEventsBuffer.clear();

            // 3) Finally, returns the new events list
            return  keyEvents;
        }
    }

    /**
     * This is called each time a new keyboard event happens.
     * Then, creates a new keyboard event and suits it up depending on the event received.
     * @param event
     * @param type
     * @param pressed
     */
    public void onKey(KeyEvent event, int type, boolean pressed)
    {
        // 1) First, we make sure that it is not accessed in parallel
        synchronized (this)
        {
            KeyboardEvent keyEvent = (KeyboardEvent) keyEventPool.newObject();

            // 2) Then, we set our keyboard event depending on the event received
            int keyCode = keyEvent.keyCode = event.getKeyCode();
            keyEvent.keyChar = event.getKeyChar();
            keyEvent.type = type;

            // 3) We also show which key is the pressed (or released) one
            if (keyCode > 0 && keyCode < 127)
                pressedKeys[keyCode] = pressed;

            // 4) Then, we just add the event to the list of events waiting to get handled
            keyEventsBuffer.add(keyEvent);
        }
    }

    @Override
    public void keyTyped(KeyEvent event)
    {
        onKey(event,KeyboardEvent.KEY_DOWN,true);
    }


    @Override
    public void keyReleased(KeyEvent event)
    {
        onKey(event,KeyboardEvent.KEY_UP,false);
    }

    @Override
    public void keyPressed(KeyEvent event) { }
}
