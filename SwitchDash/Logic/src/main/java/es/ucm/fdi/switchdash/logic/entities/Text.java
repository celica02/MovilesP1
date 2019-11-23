package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.switchdash.engine.EntitiesGroup;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;

public class Text extends EntitiesGroup {
        protected Map<Character, Integer[]> map = new HashMap<Character, Integer[]>();

        private int nRows = 7, nCols = 15;

        //Valores para el rectángulo que se cogerá del Spritesheet
        private int rectLeft, rectTop, rectW = 93, rectH = 112;


        private String _text;


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

        public void init(){
            //Guarda el espacio
            map.put(' ', new Integer[]{6, 2});

            //Guarda las 26 letras
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
            }

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

            //Cálculo del amaño que tiene cada sprite del Spritesheet.
            int sprH = Assets.scoreFont.getHeight()/nRows;
            int sprW = Assets.scoreFont.getWidth()/nCols;

            //Cálculo de la posición centra del sprite.
            int sprCenterX = sprW/2, sprCenterY = sprH/2;

            //Cálculo de la posición X e Y del sprite, donde empieza para que mida 93x112.
            rectLeft = sprCenterX - rectW/2;
            rectTop = sprCenterY - rectH/2;

            if(_text != null){
                representText(_text);
                setPositionX(posX);
            }
        }

        public void representText(String text){
            for(int i = 0; i < text.length(); i++) {
                int row = map.get(text.charAt(i))[0],
                        col = map.get(text.charAt(i))[1];
                SpriteSheet character;
                if(entities.size() == i){
                    character = new SpriteSheet(posX, posY, Assets.scoreFont, g, nRows, nCols, row, col);
                    addEntity(character);
                }
                else {
                    character = (SpriteSheet) entities.get(i);
                    character.setPosX(posX);
                    character.setActiveSprite(row, col);
                }
                int srcLeft = rectLeft + (Assets.scoreFont.getWidth()/nCols)*col,
                        srcTop = rectTop + (Assets.scoreFont.getHeight()/nRows)*row,
                        srcRight = srcLeft + rectW,
                        srcBottom = srcTop + rectH;

                MyRect sourceRect = new MyRect(srcLeft, srcTop, srcRight, srcBottom);
                character.setSourceRect(sourceRect);
            }
        }

        public void setPositionX(float _posX){
            posX = _posX;
            for(int i = 0; i< entities.size(); i++){
                entities.get(i).setPosX(_posX);
                _posX += entities.get(i).getWidth();
            }
        }

        @Override
        public void setCenteredX(){
            if(entities.size() != 0){
                float tamTotal = entities.get(0).getWidth() * entities.size();

                float centerX = g.getWidth()/2;
                float newPosX = centerX - (tamTotal/2);

                for(int i = 0; i < entities.size(); i++){
                    entities.get(i).setPosX(newPosX);
                    newPosX += entities.get(i).getWidth();
                }
            }
        }


}
