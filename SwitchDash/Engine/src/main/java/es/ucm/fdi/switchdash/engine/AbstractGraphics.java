package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.utils.MyRect;

/**
 * Capa intermedia para realizar la transformación de coordenadas de la ventana
 */
public abstract class AbstractGraphics implements Graphics
{
    // Resolución a mostrar en la ventana
    protected int resolutionWidth;
    protected int resolutionHeight;

    protected float lAspectRatio;


    /**
     * Obtiene la resolución lógica que se quiere mostrar en la ventana
     *
     * @param resWidth ancho lógico
     * @param resHeight alto lógico
     */
    public AbstractGraphics(int resWidth, int resHeight)
    {
        this.resolutionWidth = resWidth;
        this.resolutionHeight = resHeight;

        this.lAspectRatio = (float) resHeight / resWidth;
    }


    /**
     * Transforma coordenadas físicas en coordenadas lógicas en el eje X
     *
     * @param x el punto físico en el eje X a transformar
     * @return el punto lógico en el eje X transformado
     */
    public float logicX(float x)
    {
        float pAspectRatio = (float) getWindowHeight()/ getWindowWidth(); // Aspect ratio real de nuestra ventana

        float scale;
        int left = 0;

        // Si nos sobra (o nos falta) de alto, entonces obtenemos la escala según el eje X, en el cual hay correspondencia de aspect ratio
        if(pAspectRatio > lAspectRatio)
            scale = (float)resolutionWidth / getWindowWidth();

        // Si nos sobra de ancho, entonces sería la situación contraria.
        // También hacemos los cálculos para situar el punto centrado en la ventana
        else
        {
            scale = (float) resolutionHeight / getWindowHeight();
            float pWidthResolution = resolutionWidth / scale;

            left = (int)(getWindowWidth()/2 - pWidthResolution/2);
        }

        return (x - left) * scale;
    }

    /**
     * Transforma coordenadas físicas en coordenadas lógicas en el eje Y
     *
     * @param y el punto físico en el eje Y a transformar
     * @return el punto lógico en el eje Y transformado
     */
    public float logicY(float y)
    {
        float pAspectRatio = (float) getWindowHeight()/ getWindowWidth(); // Aspect ratio real de nuestra ventana

        float scale;
        int top = 0;

        // Si nos sobra de alto, entonces obtenemos la escala según el eje X, en el cual hay correspondencia de aspect ratio.
        // También hacemos los cálculos para situar el punto centrado en la ventana
        if(pAspectRatio > lAspectRatio)
        {
            scale = (float) resolutionWidth / getWindowWidth();
            float pHeightResolution = resolutionHeight / scale;

            top = (int)(getWindowHeight()/2 - pHeightResolution/2);
        }
        // Si nos sobra (o nos falta) de ancho, entonces obtenemos la escala según el eje Y, en el cual hay correspondencia de aspect ratio
        else
            scale = (float) resolutionHeight / getWindowHeight();

        return (y - top) * scale;
    }

    /**
     * Reescala las coordenadas para pasar de coordenandas lógicas a físicas,
     * y luego pinta la imagen según el motor concreto
     *
     * @param image imagen a pintar
     * @param source rect de origen de la imagen que pintaremos como tal
     * @param dest rect de destino donde pintaremos el rect de origen escogido
     * @param alpha transparencia con la que pintar la imagen
     */
    @Override
    public void drawImage(Image image, MyRect source, MyRect dest, float alpha)
    {
        // 1) Primero obtenemos el ascpect ratio en función de la altura / anchura
        float pAspectRatio = (float) getWindowHeight()/ getWindowWidth(); // Aspect ratio real de nuestra ventana

        float scale;
        int left = 0;
        int top = 0;

        /*
        2) En función de ello, vemos cúal de los valores es mayor para saber dónde se produce la sobreescala, si en la altura o en la anchura:

            a) Obtendremos entonces el factor de escala en función de la dimensión que no se ha sobreescalado (es decir, la que mantendría la relación de aspect ratio).

            b) A continuación obtendremos el valor físico que le correspondería a la altura o anchura para mantener el aspect ratio lógico.
              Es decir, generaríamos un "canvas interno" con dicho aspect ratio, y reescalaríamos la dimensión multiplicándola por el factor de escala obtenido.

            c) Una vez obtenido, simplemente calculamos el centro de la ventana real, y le restamos la mitad de la "ventana interna" con el aspect ratio correcto
              para obtener la nueva posición (0, 0) de la "ventana" donde realmente vamos a pintar. En función de la dimensiñón que se haya reescalado obtendremos
              la coordenada X o la coordenada Y.
         */
        if(pAspectRatio > lAspectRatio)
        {
            scale = (float) getWindowWidth()/resolutionWidth;
            float pHeightResolution = scale * resolutionHeight;

            top = (int)(getWindowHeight()/2 - pHeightResolution/2);
        }
        else
        {
            scale = (float) getWindowHeight()/resolutionHeight;
            float pWidthResolution = scale * resolutionWidth;

            left = (int)(getWindowWidth()/2 - pWidthResolution/2);
        }

        // 3) Situamos las coordenadas de destino ya con los cálculos realizados
        dest.left = (int)(left + (dest.left * scale));
        dest.right = (int)(left + (dest.right * scale));

        dest.top = (int)(top + (dest.top * scale));
        dest.bottom = (int)(top + (dest.bottom * scale));

        // 4) Por último, pintamos la imagen como tal
        drawImagePrivate(image, source, dest, alpha);
    }


    /**
     * Llama al pintado de imagen del motor concreto que lo va a redefinir
     *
     * @param image imagen a pintar
     * @param src rect de origen de la imagen que pintaremos como tal
     * @param dst rect de destino donde pintaremos el rect de origen escogido
     * @param alpha transparencia con la que pintar la imagen
     */
    abstract public void drawImagePrivate(Image image, MyRect src, MyRect dst, float alpha);
}
