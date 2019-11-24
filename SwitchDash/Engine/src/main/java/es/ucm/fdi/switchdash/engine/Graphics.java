package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 * Provee las funcionalidades gráficas mínimas en la aplicación de la ventana.
 */

public interface Graphics
{
    /**
     * Carga una imagen dado el nombre del archivo.
     *
     * @param pathname el pathname de la imagen a cargar
     * @return la imagen cargada
     */
    Image newImage(String pathname);

    /**
     * Pinta la imagen dada (o parte de ella).
     *
     * @param image la imagen a pintar
     * @param source el rect de la imagen que queremos pintar como tal
     * @param dest el rect destino donde pintaremos el rect de origen escogido
     * @param alpha la transparencia con la que pintaremos la imagen
     */
    void drawImage(Image image, MyRect source, MyRect dest, float alpha);

    /**
     * Borra por completo el contenido de la ventana, y lo rellena con un color dado.
     *
     * @param color el color con el que rellenar la ventana
     */
    void clear(int color);


    void dispose();

    /**
     * @return el ancho físico de la ventana
     */
    int getWindowWidth();
    /**
     *
     * @return el alto físico de la ventana
     */
    int getWindowHeight();


    /**
     * @return el ancho lógico de la ventana
     */
    int getWidth();
    /**
     * @return el alto lógico de la ventana
     */
    int getHeight();


    /**
     * Transforma coordenadas físicas en coordenadas lógicas en el eje X
     *
     * @param x el punto físico en el eje X a transformar
     * @return el punto lógico en el eje X transformado
     */
    float logicX(float x);
    /**
     * Transforma coordenadas físicas en coordenadas lógicas en el eje Y
     *
     * @param y el punto físico en el eje Y a transformar
     * @return el punto lógico en el eje Y transformado
     */
    float logicY(float y);
}
