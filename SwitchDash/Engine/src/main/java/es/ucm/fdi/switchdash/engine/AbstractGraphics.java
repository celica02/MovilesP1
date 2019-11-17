package es.ucm.fdi.switchdash.engine;

import org.w3c.dom.css.Rect;

import es.ucm.fdi.switchdash.engine.utils.MyRect;
public abstract class AbstractGraphics implements Graphics
{

    protected int resolutionWidth;
    protected int resolutionHeight;

    protected float widthFactor;
    protected float heightFactor;

    public AbstractGraphics(int resWidth, int resHeight)
    {
        this.resolutionWidth = resWidth;
        this.resolutionHeight = resHeight;
    }

    @Override
    public void drawImage(Image image, int x, int y, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect srcRect = new MyRect(0, 0, image.getWidth(), image.getHeight());
        MyRect dstRect = new MyRect(x, y, image.getWidth(), image.getHeight());

        drawImagePrivate(image, srcRect, dstRect, alpha);
    }

    @Override
    public void drawImage(Image image, MyRect dest, int alpha)
    {
        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect source = new MyRect(0, 0, image.getWidth(), image.getHeight());

        drawImagePrivate(image, source, dest, alpha);
    }

    @Override
    public void drawImage(Image image, int destX, int destY, int destWidth, int destHeight, int alpha)
    {

        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        MyRect srcRect = new MyRect(0, 0, image.getWidth(), image.getHeight());
        MyRect dstRect = new MyRect(destX, destY, destWidth, destHeight);

        drawImagePrivate(image,srcRect , dstRect, alpha);
    }

    @Override
    public void drawImage(Image image, MyRect source, MyRect dest, int alpha)
    {
        // 1) Primero obtenemos los ascpect ratio en función de la altura / anchura
        float lAspectRatio = (float) resolutionHeight/resolutionWidth; // Aspect ratio en el que queremos pintar
        float pAspectRatio = (float) getHeight()/getWidth(); // Aspect ratio real de nuestra ventana

        float pResolution, scale;
        int left = 0;
        int top = 0;

        /*
        2) En función de ello, vemos cúal de los valores es mayor para saber dónde se produce la sobreescala, si en la altura o en la anchura:

            a) Obtendremos entonces el factor de escala en función de la dimensión que no se ha sobreescalado (es decir, la que mantendría la relación de aspect ratio).

            b) Por último, para posicionarnos en el centro obtendremos el valor físico que le correspondería a la altura o anchura para mantener el aspect ratio lógico.
              Es decir, generaríamos un "canvas interno" con dicho aspect ratio, y reescalaríamos la dimensión multiplicándola por el factor de escala obtenido.

            c) Una vez obtenido, simplemente calculamos el centro de la ventana real, y le restamos la mitad de la "ventana interna" con el aspect ratio correcto
              para obtener la nueva posición (0, 0) de la "ventana" donde realmente vamos a pintar. En función de la dimensiñón que se haya reescalado obtendremos
              la coordenada X o la coordenada Y.
         */
        if(pAspectRatio > lAspectRatio)
        {
            scale = (float) getWidth()/resolutionWidth;
            pResolution = scale * resolutionHeight;

            top = (int)(getHeight()/2 - pResolution/2);
        }
        else
        {
            scale = (float) getHeight()/resolutionHeight;
            pResolution = scale * resolutionWidth;

            left = (int)(getWidth()/2 - pResolution/2);
        }

        dest.top = (int)(top + (dest.top * scale));
        dest.bottom = (int)(top + (dest.bottom * scale));
        dest.left = (int)(left + (dest.left * scale));
        dest.right = (int)(left + (dest.right * scale));

        drawImagePrivate(image, source, dest, alpha);
    }


    //abstract public void drawImagePrivate(Image image, int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int destHeight, int alpha);
    abstract public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha);
}
