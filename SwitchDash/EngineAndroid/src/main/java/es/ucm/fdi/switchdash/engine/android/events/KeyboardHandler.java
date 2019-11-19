package es.ucm.fdi.switchdash.engine.android.events;

import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;

public class KeyboardHandler implements View.OnKeyListener
{


    // Lists to hold the incoming touch events and the ones to be processed next
    private List<KeyboardEvent> keyEvents = new ArrayList<>();
    private List<KeyboardEvent> keyEventsBuffer = new ArrayList<>();
    public KeyboardHandler() {
        super();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event)
    {
        return false;
    }

    public List<KeyboardEvent> getKeyEvents()
    {
        synchronized(this)
        {
            return keyEvents;
        }
    }
}
