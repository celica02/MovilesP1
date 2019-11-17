package es.ucm.fdi.switchdash.launcherAndroid;

import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.android.AndroidGame;
import es.ucm.fdi.switchdash.logic.states.LoadingState;

public class SwitchDash extends AndroidGame
{
    public SwitchDash()
    {
        super(1080, 1920);
    }

    public GameState getStartState()
    {
        return new LoadingState(this);
    }
}
