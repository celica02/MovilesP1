package es.ucm.fdi.switchdash.engine.desktop.events;

import javax.swing.JFrame;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.desktop.DesktopGraphics;
import es.ucm.fdi.switchdash.engine.utils.Pool;

import java.util.ArrayList;
import java.util.List;

public class MouseHandler extends JFrame implements MouseListener
{

    // Pool of key events
    private Pool<TouchEvent> mouseEventPool;

    // Lists to hold the incoming key events and the ones to be processed next
    private List<TouchEvent> mouseEventsBuffer = new ArrayList<>();
    private List<TouchEvent> mouseEvents = new ArrayList<>();

    // Used to handle screen resolutions
    private Graphics g;


    public MouseHandler(Window window, DesktopGraphics graphics) {
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };

        mouseEventPool = new Pool<>(factory, 100);

        addMouseListener(this);
        window.addMouseListener(this);

        this.g = graphics;
    }

    public List<TouchEvent> getMouseEvents()
    {
        synchronized (this)
        {
            // 1) Sets the old events free adding them to the pool
            for(TouchEvent e: mouseEvents)
                mouseEventPool.free(e);

            // 2) Adds the new events previously waiting in the events buffer
            mouseEvents.clear();
            mouseEvents.addAll(mouseEventsBuffer);

            mouseEventsBuffer.clear();

            // 3) Finally, returns the new events list
            return  mouseEvents;
        }
    }

    public boolean onMouse(MouseEvent event, int type)
    {
        // 1) First, we make sure that it is not accessed in parallel
        synchronized (this)
        {
            TouchEvent touchEvent = mouseEventPool.newObject();

            // 2) Then, we set our touch event depending on the event received
            switch (type) {
                case TouchEvent.DOWN:
                    touchEvent.type = TouchEvent.DOWN;
                    break;

                case TouchEvent.UP:
                    touchEvent.type = TouchEvent.UP;
                    break;

                case TouchEvent.DRAG:
                    touchEvent.type = TouchEvent.DRAG;
                    break;

                default:
                    break;
            }

            // 3) Lastly, we multiply the coordinates by the scale
            touchEvent.x = (int)g.scaleX(event.getX()); //(int) (event.getX() * g.getWidthScaleFactor());
            touchEvent.y = (int)g.scaleY(event.getY()); //(int) (event.getY() * g.getHeightScaleFactor());

            // 4) Then, we just add the event to the list of events waiting to get handled
            mouseEventsBuffer.add(touchEvent);

            return true;
        }
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        onMouse(mouseEvent, TouchEvent.DOWN);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        onMouse(mouseEvent, TouchEvent.DRAG);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        onMouse(mouseEvent, TouchEvent.UP);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
