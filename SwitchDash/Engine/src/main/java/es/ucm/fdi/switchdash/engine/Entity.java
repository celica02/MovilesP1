package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;


import java.util.List;

/**
 * Entidad de juego la cual permite actualizarse y recibir eventos.
 */
public abstract class Entity
{
    // Motor gráfico
    protected final Graphics g;

    // Posición y dimensiones
    protected float posX, posY;
    protected float width, height;

    // ID de la entidad
    protected int ID;

    // bool para cononcer si la entidad está o no activa
    protected boolean active = true;


    // ---------- CONSTRUCTORAS ---------- //

    public Entity(Graphics graphics)
    {
        g = graphics;

        posX = 0;
        posY = 0;
    }

    public Entity(float posX, float posY, Graphics graphics){
        g = graphics;

        this.posX = posX;
        this.posY = posY;
    }

    public Entity(float posX, float posY, float width, float height, Graphics graphics){
        g = graphics;

        this.posX = posX;
        this.posY = posY;

        this.width = width;
        this.height = height;
    }

    //---------- FUNCIONES ----------//

    /**
     * Actualiza la entidad si ésta estuviera activa.
     *
     * @param deltaTime delta time del juego
     */
    public void update(float deltaTime)
    {
        if (active)
            updateEntity(deltaTime);
    }

    /**
     * Actualiza la entidad como tal. Se llamará solo si la entidad estuviera activa.
     *
     * @param deltaTime delta time del juego
     */
    public abstract void updateEntity(float deltaTime);

    /**
     * Renderiza la entidad. En principio, una entidad sin más no tiene nada que renderizar
     *
     * @param deltaTime delta time del juego
     */
    public void render(float deltaTime){}

    /**
     * Maneja los eventos de pulsación y teclado si la entidad esuviera activa.
     *
     * @param touchEvents lista de eventos de pulsación
     * @param keyEvents lista de eventos de teclado
     * @param deltaTime delta time del juego
     */
    public void handleInput(List<TouchEvent> touchEvents, List<KeyboardEvent> keyEvents, float deltaTime)
    {
        if(active)
        {
            for (TouchEvent e : touchEvents)
                handleTouchEvent(e);

            for (KeyboardEvent e : keyEvents)
                handleKeyEvent(e);
        }
    }

    /**
     * Maneja un evento de pulsación
     * @param e evento dado
     */
    protected void handleTouchEvent(TouchEvent e){}
    /**
     * Maneja un evento de teclado
     * @param e evento dado
     */
    protected void handleKeyEvent(KeyboardEvent e){}


    /**
     * Comprueba que una posición dada esté dentro de la entidad.
     *
     * @param x posición en X
     * @param y posición en Y
     * @return 'true' si la posición dada está dentro, 'false' en caso contrario
     */
    public boolean inBounds(float x, float y) {
        return (x > posX && x < posX + width - 1) &&
                (y > posY && y < posY + height - 1);
    }

    /**
     * Comprueba si la entidad colisiona con otra entidad dada.
     *
     * @param e la entidad con la que comprobar la colisión
     * @return 'true' si hay colisión, 'false' en caso contrario
     */
    public boolean collides(Entity e) {
        return (posX < e.posX + e.width &&
                posX + width > e.posX &&
                posY < e.posY + e.height &&
                posY + height > e.posY);
    }


    /**
     * @return posición en X
     */
    public float getPosX() { return  posX; }
    /**
     * @return posición en Y
     */
    public float getPosY() { return posY; }

    /**
     * Cambia la posición en X
     *
     * @param x nuevo valor de X
     */
    public void setPosX(float x) { posX = x; }
    /**
     * Cambia la posición en Y
     *
     * @param y nuevo valor de Y
     */
    public void setPosY(float y) { posY = y; }

    /**
     * @return ancho de la entidad
     */
    public float getWidth() { return width; }
    /**
     * @return alto de la entidad
     */
    public float getHeight() { return  height; }
    /**
     * Cambia el ancho de la entidad
     *
     * @param w nuevo valor del ancho
     */

    public void setWidth(float w) { width = w; }
    /**
     * Cambia el alto de la entidad
     *
     * @param h nuevo valor del alto
     */
    public void setHeight(float h) { height = h; }
    /**
     * Reescala el tamaño de la entidad
     *
     * @param width nuevo valor del ancho
     * @param height nuevo valor del alto
     */
    public void setSize(float width, float height) { this.width = width; this.height = height; }

    /**
     * Centra la entidad en el eje X
     */
    public void setCenteredX() { posX = (g.getWidth()/2) - (getWidth()/2); }
    /**
     * Centra la entidad en el eje Y
     */
    public void setCenteredY() { posY = (g.getHeight()/2) - (getHeight()/2);}
    /**
     * Centra la entidad en el eje X y en el eje Y
     */
    public void setCentered() { posX = (g.getWidth()/2) - (getWidth()/2); posY = (g.getHeight()/2) - (getHeight()/2); }

    /**
     * @return el ID de la entidad
     */
    public int getID(){return ID;}
    /**
     * Cambia el ID de la entidad
     *
     * @param id
     */
    public void setID(int id){ID = id;}

    /**
     * @return 'true' si la entidad está activa, 'false' en caso contrario
     */
    public boolean isActive() { return active; }

    /**
     * Cambia el estado de la entidad (activa o no activa)
     *
     * @param a el valor del nuevo estado de la entidad
     */
    public void setActive(boolean a) { active = a; }

    /**
     * Resetea la entidad
     *
     * @param x nueva posición en X
     * @param y nueva posición en Y
     */
    public void reset(float x, float y)
    {
        active = true;
        posX = x;
        posY = y;
    }

    public  void decreaseID(){ ID--; }
}
