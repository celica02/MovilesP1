package es.ucm.fdi.switchdash.engine.android;


import android.content.Context;
import android.view.View;

import java.util.List;

import es.ucm.fdi.switchdash.engine.android.events.KeyboardHandler;
import es.ucm.fdi.switchdash.engine.android.events.SingleTouchHandler;



public class AndroidInput implements es.ucm.fdi.switchdash.engine.Input
{

    // ----------ATTRIBUTES---------- //

    private SingleTouchHandler touchHandler;
    private KeyboardHandler keyboardHandler;



    // ----------FUNCTIONS---------- //

    public AndroidInput(Context context, View view, float widthFactor, float heightFactor)
    {
        touchHandler = new SingleTouchHandler(view, widthFactor, heightFactor);
        keyboardHandler = new KeyboardHandler();
    }



    @Override
    public List<TouchEvent> getTouchEvents()
    {
        return touchHandler.getTouchEvents();
    }

    @Override
    public List<KeyboardEvent> getKeyEvents() { return keyboardHandler.getKeyEvents(); }
}
