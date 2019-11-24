package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;

/**
 * Pinta en pantalla, a partir de la posición indicada hacia la derecha, el texto pasado por parámetros
 */
public class Text extends EntitiesGroup {

    protected Map<Character, Integer[]> map = new HashMap<Character, Integer[]>();

    private int nRows = 7, nCols = 15;

    //Valores para el rectángulo que se cogerá del Spritesheet para cada símbolo
    private int rectLeft, rectTop, rectW = 93, rectH = 112;

    private String _text;

    // ---------- CONSTRUCTORAS ---------- //
    public Text (float posX, float posY, Graphics graphics){
        super(graphics);
        entities = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        init();
    }

    public Text (float posX, float posY, String text, Graphics graphics){
        super(graphics);
        entities = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        _text = text;
        init();

        representText(text);

    }

    // ---------- FUNCIONES ---------- //
    /**
     * Guarda las letras, números y el espacio en el mapa. También
     */
    public void init()
    {
        //Guarda el espacio
        map.put(' ', new Integer[]{6, 2});

        //Guarda las 26 letras del abecedario inglés. Mayúsculas y minúsculas
        int c = 65;
        int fil = 0, col = 0;
        for(int i = 0; i < 26; i++)
        {
            if(col == nCols ){
                fil++;
                col = 0;
            }
            map.put((char)c, new Integer[]{fil, col});
            map.put((char)(c+32), new Integer[]{fil, col});

            c++;
            col++;
        }//for

        //Guarda los 10 números
        c = 48;
        fil = 3;
        col = 7;
        for(int i=0; i < 10; i++)
        {
            if(col == nCols){
                fil++;
                col = 0;
            }
            map.put((char)c, new Integer[]{fil, col});

            c++;
            col++;
        }

        //Cálculo del tamaño que tiene cada sprite del spritesheet
        int sprH = Assets.scoreFont.getHeight()/nRows;
        int sprW = Assets.scoreFont.getWidth()/nCols;

        //Cálculo de la posición central del sprite del spritesheet.
        int sprCenterX = sprW/2, sprCenterY = sprH/2;

        //Cálculo de la posición X e Y a partir de la cual hay que coger el sprite para que esté centrado si mide 93x112.
        rectLeft = sprCenterX - rectW/2;
        rectTop = sprCenterY - rectH/2;

        if(_text != null){
            representText(_text);
            setPositionX(posX);
        }
    }//init

    /**
     * Representa el texto pasado por parámetro en pantalla
     * @param text texto a escribir
     */
    public void representText(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            //1. Guarda la columna y la fila en la que se encuentra el símbolo
            int row = map.get(text.charAt(i))[0],
                    col = map.get(text.charAt(i))[1];

            SpriteSheet character;
            //2. Comprueba si ya hay una entidad existente
            if(entities.size() == i)
            {
                //Si no es así crea una nueva y la añade a la lista
                character = new SpriteSheet(posX, posY, Assets.scoreFont, g, nRows, nCols, row, col);
                addEntity(character);
            }
            else
            {
                //Si sí que hay establece los parámetros para la letra que corresponde
                character = (SpriteSheet) entities.get(i);
                character.setPosX(posX);
                character.setActiveSprite(row, col);
            }

            //3. Calcula los valores del Rect para coger el trozo del spritesheet
            int srcLeft = rectLeft + (Assets.scoreFont.getWidth()/nCols)*col,
                    srcTop = rectTop + (Assets.scoreFont.getHeight()/nRows)*row,
                    srcRight = srcLeft + rectW,
                    srcBottom = srcTop + rectH;

            MyRect sourceRect = new MyRect(srcLeft, srcTop, srcRight, srcBottom);
            character.setSourceRect(sourceRect);
        }//for
    }

    /**
     * Coloca todos los caracteres del texto de izquierda a derecha a partir de la posición pasada por parámetro
     * @param _posX posición en X a partir de la que se colocará el texto
     */
    public void setPositionX(float _posX){
        posX = _posX;
        for(int i = 0; i< entities.size(); i++){
            entities.get(i).setPosX(_posX);
            _posX += entities.get(i).getWidth();
        }
    }

    /**
     *Coloca el texto centrado en el eje X en la pantalla
     */
    @Override
    public void setCenteredX(){
        if(entities.size() != 0){
            //1. Calcula el tamaño total que ocupa el texto
            float tamTotal = entities.get(0).getWidth() * entities.size();

            //2. Calcula el centro de la pantalla de juego
            float centerX = g.getWidth()/2;

            //3. Calcula la posición a partir de donde deberá posicionarse el texto
            float newPosX = centerX - (tamTotal/2);

            //4. Coloca los símbolos a partir de esa posición hacia la derecha
            for(int i = 0; i < entities.size(); i++){
                entities.get(i).setPosX(newPosX);
                newPosX += entities.get(i).getWidth();
            }
        }
    }


}
