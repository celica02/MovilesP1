package es.ucm.fdi.switchdash.launcherpc;

import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.desktop.DesktopGame;
import es.ucm.fdi.switchdash.logic.states.LoadingState;

public class SwitchDashPC extends DesktopGame
{

    @Override
    public GameState getStartState() { return new LoadingState(this); }

    public  static void main(String[] args)
    {
        SwitchDashPC game = new SwitchDashPC();
        game.run();
    } // main
}
