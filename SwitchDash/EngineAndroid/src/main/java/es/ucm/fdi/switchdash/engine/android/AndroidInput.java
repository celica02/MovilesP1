package es.ucm.fdi.switchdash.engine.android;


import android.content.Context;
import android.view.View;

import java.util.List;

import es.ucm.fdi.switchdash.engine.android.events.KeyboardHandler;
import es.ucm.fdi.switchdash.engine.android.events.SingleTouchHandler;



public class AndroidInput implements es.ucm.fdi.switchdash.engine.Input
{

    // ----------ATTRIBUTES---------- //

    SingleTouchHandler touchHandler;
    KeyboardHandler keyboardHandler;



    // ----------FUNCTIONS---------- //

    public AndroidInput(Context context, View view, float resolutionWidth, float resolutionHeight)
    {
        touchHandler = new SingleTouchHandler(view, resolutionWidth, resolutionHeight);
    }



    @Override
    public List<TouchEvent> getTouchEvents()
    {
        return touchHandler.getTouchEvents();
    }

    @Override
    public List<KeyboardEvent> getKeyEvents() { return keyboardHandler.getKeyEvents(); }
}
