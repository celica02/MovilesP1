package es.ucm.fdi.switchdash.engine.android;


import android.content.Context;
import android.view.View;

import java.util.List;

import es.ucm.fdi.switchdash.engine.android.events.SingleTouchHandler;



public class AndroidInput implements es.ucm.fdi.switchdash.engine.Input
{

    // ----------ATTRIBUTES---------- //

    SingleTouchHandler touchHandler;


    // ----------FUNCTIONS---------- //

    public AndroidInput(Context context, View view, float scaleX, float scaleY)
    {
        touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
    }



    public boolean isTouchDown(int pointerID) { return touchHandler.isTouchDown(pointerID); }

    public int getTouchX(int pointerID) { return touchHandler.getTouchX(pointerID); }
    public int getTouchY(int pointerID) { return touchHandler.getTouchY(pointerID); }

    @Override
    public List<InputEvent> getInputEvents()
    {
        return touchHandler.getTouchEvents();
    }
}
