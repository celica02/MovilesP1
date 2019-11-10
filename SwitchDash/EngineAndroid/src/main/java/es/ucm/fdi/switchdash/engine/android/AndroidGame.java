package es.ucm.fdi.switchdash.engine.android;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import es.ucm.fdi.switchdash.engine.FileIO;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;

public abstract class AndroidGame extends Activity implements Game
{
    AndroidFastRenderView renderView;
    Graphics graphics;
    Input input;
    FileIO fileIO;
    GameState gameState;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;

        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Config.RGB_565);

        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();

        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        input = new AndroidInput(this, renderView, scaleX, scaleY);

        gameState = getStartState();

        setContentView(renderView);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        gameState.resume();
        renderView.resume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        renderView.pause();
        gameState.pause();
        if (isFinishing())
        {
            gameState.dispose();
        }
    }



    public void setState(GameState state)
    {
        if (gameState == null)
            throw new IllegalArgumentException("GameState must not be null");

        this.gameState.pause();
        this.gameState.dispose();

        state.resume();
        state.update(0);

        this.gameState = state;
    }

    public GameState getCurrentState()
    {
        return gameState;
    }

    public Input getInput() {
        return input;
    }
    public FileIO getFileIO() {
        return fileIO;
    }
    public Graphics getGraphics() {
        return graphics;
    }
}
