package es.ucm.fdi.switchdash.launcherAndroid;


import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.android.AndroidGame;
import es.ucm.fdi.switchdash.logic.states.LoadingState;

public class SwitchDash extends AndroidGame
{
    public GameState getStartState()
    {
        return new LoadingState(this);
    }
}




/*
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class SwitchDash extends AppCompatActivity implements View.OnClickListener
{

    // ----------------ATTRIBUTES---------------- //

    Button button;
    int touchCount;

    // ----------------FUNCTIONS---------------- //


    // This function "replaces" the constructor normally expected to create an instance of a class
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = new Button(this);
        button.setText("Touch me!");
        button.setOnClickListener(this);
        setContentView(button);
    }


    public  void onClick(View v)
    {
        touchCount++;
        button.setText("Touched me " + touchCount + "time(s)");
    }
}
*/