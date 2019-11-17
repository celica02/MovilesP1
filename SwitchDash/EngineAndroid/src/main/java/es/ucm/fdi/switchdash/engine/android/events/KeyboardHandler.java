package es.ucm.fdi.switchdash.engine.android.events;

import android.view.KeyEvent;
import android.view.View;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;

public class KeyboardHandler implements View.OnKeyListener
{
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
        }
        return null;
    }
}
