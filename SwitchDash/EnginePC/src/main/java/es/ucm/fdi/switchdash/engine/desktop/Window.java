package es.ucm.fdi.switchdash.engine.desktop;

import javax.swing.JFrame;


public class Window extends JFrame
{
    
    public Window(String title) {
        super(title);
    }

    public void  init(int width, int weight)
    {
        setSize(width, weight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIgnoreRepaint(true);
        setVisible(true);
    }
}

