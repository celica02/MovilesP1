package es.ucm.fdi.switchdash.launcherpc;

import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.desktop.DesktopGame;
import es.ucm.fdi.switchdash.logic.states.LoadingState;

public class SwitchDashPC extends DesktopGame
{
    public SwitchDashPC(String title, int windowWidth, int windowHeight, int resWidth, int resHeight)
    {
        super(title, windowWidth, windowHeight, resWidth, resHeight);
    }

    @Override
    public GameState getStartState() { return new LoadingState(this); }



    public static void main(String[] args)
    {
        SwitchDashPC game = new SwitchDashPC("Switch Dash", 1920,1080, 1080, 1920);
        game.run();
    } // main
}
