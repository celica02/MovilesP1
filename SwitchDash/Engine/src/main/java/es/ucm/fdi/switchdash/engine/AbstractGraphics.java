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
        float lAspectRatio = (float) resolutionHeight/resolutionWidth;
        float pAspectRatio = (float) getHeight()/getWidth();

        float factor, scale;
        int left = 0;
        int top = 0;

        // Tenemos que reescalar verticalmente
        if(pAspectRatio > lAspectRatio)
        {
            factor = getWidth() * lAspectRatio;
            scale = factor / resolutionHeight;

             top = (int)(getHeight()/2 - factor/2);
        }
        // Tenemos que reescalar horizontalmente
        else
        {
            /*  Obtenemos el escalado sobre el ancho usando una regla de 3 para obtener la correspondencia:

                Al reescalar horizontalmente sabemos que la altura física tiene correspondencia directa con la altura lógica (en cuanto al aspect ratio)
                puesto que ésta no cambia en este caso. Por tanto, lo que debemos obtener es el valor que le corresponde al ancho físico para mantener el aspect ratio lógico
                despúes de haberlo reescalado:

                Altura física (getHeight()) -----> Altura lógica (1920)
                         Anchura física (X) -----> Anchura lógica (1080)

                Con esto, lo que estamos obteniendo es una "ventana" o "canvas" interno (dentro de la ventana principal),
                el cual está reescalado de tal manera que mantiene el mismo aspect ratio que el lógico.
                Es decir, justo lo que buscamos.
            */
            factor = getHeight() / lAspectRatio;

            // Una vez tenemos el valor del ancho reescalado, obtenemos cúanto se ha reescalado, lo cual usaremos para reposicionar las coordenadas de la imagen
            scale = factor / resolutionWidth;

            // Por último, tenemos que obtener la nueva posición de inicio en X (lo que sería la posición 0 de la ventana, pero reajustada al centro)
            // Para ello, primero obtenemos el punto medio de la ventana, y a éste le restamos la mitad de la "ventana interna" reescalada.
            // Así tendremos el extremo izquierdo de la ventana reescalada donde pintaremos realmente.
             left = (int)(getWidth()/2 - factor/2);
        }

        // Operación para cambiar de coordenadas logicas a coordenadas fisicas
        dest.top = (int)(top + (dest.top * scale));
        dest.bottom = (int)(top + (dest.bottom * scale));
        dest.left = (int)(left + (dest.left * scale));
        dest.right = (int)(left + (dest.right * scale));


        drawImagePrivate(image, source, dest, alpha);
    }


    //abstract public void drawImagePrivate(Image image, int srcX, int srcY, int srcWidth, int srcHeight, int dstX, int dstY, int dstWidth, int destHeight, int alpha);
    abstract public void drawImagePrivate(Image image, MyRect src, MyRect dst, int alpha);
}
