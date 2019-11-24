package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 * Envuelve una imagen dada como bitmap para poder ser usado como un sprite.
 */
public interface Image
{
    /**
     * @return el ancho de la imagen
     */
    int getWidth();
    /**
     *
     * @return el alto de la imagen
     */
    int getHeight();


    /**
     * Obtiene un rect de la imagen dadas las coordenadas
     *
     * @param left esquina superior izquierda (eje X)
     * @param top esquina superior izquierda (eje Y)
     * @param right esquina inferior derecha (eje X)
     * @param bottom esquina inferior derecha (eje Y)
     * @return el rect de la imagen
     */
    MyRect getRect(int left, int top, int right, int bottom);
    /**
     * @return el rect de la imagen completa
     */
    MyRect getFullRect();

    /**
     * disposes the image
     */

    void dispose();
}
