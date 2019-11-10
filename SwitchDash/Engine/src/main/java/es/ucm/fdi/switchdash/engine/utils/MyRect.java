package es.ucm.fdi.switchdash.engine.utils;

public class MyRect
{
    public int left, right, top, bottom;

    public MyRect(int left, int top, int right, int bottom)
    {
        // Top-left corner
        this.left = left;
        this.top = top;

        // Bottom-right corner
        this.right = right;
        this.bottom = bottom;
    }
}
