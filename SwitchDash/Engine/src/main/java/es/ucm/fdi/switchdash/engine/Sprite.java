package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;


/**
 * Entidad que contiene además una imagen que renderizar
 */
public class Sprite extends Entity
{
    protected Image img;

    protected MyRect src;

    protected float alpha;

    protected boolean visible = true;

    // ---------- CONSTRUCTORAS ---------- //

    public Sprite(Graphics graphics)
    {
        super(graphics);
    }

    public Sprite(float posX, float posY, Image image, Graphics graphics)
    {
        super(posX, posY, graphics);
        img = image;
        src = image.getFullRect();
        width = src.right;
        height = src.bottom;


        alpha = 1;
    }
    public Sprite(float posX, float posY, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY, graphics);
        img = image;
        src = source;
        width = source.right;
        height = source.bottom;

        alpha = 1;
    }

    public Sprite(float posX, float posY, float width, float height, Image image, MyRect source, Graphics graphics)
    {
        super(posX, posY, width, height, graphics);

        img = image;
        src = source;

        alpha = 1;
    }

    //---------- FUNCIONES ----------//

    @Override
    public void updateEntity(float deltaTime) { }

    /**
     * Renderiza el sprite si éste fuera visible
     *
     * @param deltaTime delta time del juego
     */
    @Override
    public void render(float deltaTime)
    {
        if(visible)
            renderEntity(deltaTime);
    }

    /**
     * Renderiza la entidad como tal (cuando ésta sea visible)
     *
     * @param deltaTime delta time del juego
     */
    public void renderEntity(float deltaTime)
    {
        MyRect dst = new MyRect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
        g.drawImage(img, src, dst, alpha);
    }

    @Override
    protected void handleTouchEvent(TouchEvent e) { }
    @Override
    protected void handleKeyEvent(KeyboardEvent e) { }


    /**
     * @return la transparencia actual del sprite
     */
    public  float getAlpha() { return alpha; }
    /**
     * Cambia la transparencia del sprite
     *
     * @param a valor de la transparencia (rango de 0 a 1)
     */
    public  void setAlpha(float a)
    {
        if(a > 1.0f)
            alpha = 1.0f;
        else if(a < 0.0f)
            alpha = 0.0f;
        else
            alpha = a;
    }

    /**
     * @return 'true' si es visible, 'false' en caso contrario
     */
    public boolean isVisible() { return visible; }
    /**
     * Cambia el estado del sprite (visible o no visible)
     * @param v nuevo estado de visibilidad del sprite
     */
    public void setVisible(boolean v) { visible = v; }


    /**
     * Cambia el rect de origen del sprite
     *
     * @param src nuevo rect de origen
     */
    public void setSourceRect(MyRect src) {
        this.src = src;
        width = src.right - src.left;
        height = src.bottom - src.top;
    }
    /**
     * Cambia el rect de destino del sprite
     *
     * @param dest nuevo rect de destino
     */
    public void setDestRect(MyRect dest)
    {
        posX = dest.left;
        posY = dest.top;

        width = dest.right;
        height = dest.bottom;
    }

    /**
     * Resetea el sprite en una nueva posición, y lo pone como visible
     * @param x nueva posición en X
     * @param y nueva posición en Y
     */
    @Override
    public void reset(float x, float y)
    {
        super.reset(x, y);

        visible = true;
    }
}
