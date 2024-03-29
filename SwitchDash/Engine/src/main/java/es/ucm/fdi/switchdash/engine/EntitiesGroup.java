package es.ucm.fdi.switchdash.engine;

import java.util.List;

public class EntitiesGroup extends Entity {
    protected List<Entity> entities;

    // ---------- CONSTRUCTORA ---------- //

    public EntitiesGroup(Graphics graphics) {
        super(graphics);
    }

    // ---------- FUNCIONES ---------- //

    @Override
    public void updateEntity(float deltaTime) {

    }

    @Override
    public void update(float deltaTime) {
        for(Entity e: entities){
            e.update(deltaTime);
        }
    }

    @Override
    public void render(float deltaTime) {
        for(Entity e: entities){
            if(e.isActive())
                e.render(deltaTime);
        }
    }

    @Override
    protected void handleTouchEvent(Input.TouchEvent e) {
        for(Entity ent: entities){
            ent.handleTouchEvent(e);
        }
    }

    @Override
    protected void handleKeyEvent(Input.KeyboardEvent e) {
        for(Entity ent: entities){
            ent.handleKeyEvent(e);
        }
    }

    @Override
    public void setCenteredX() {
        for(Entity e: entities){
            e.setCenteredX();
        }
    }

    protected void addEntity(Entity e){
        e.setID(entities.size());
        entities.add(e);

        if(e.getHeight() > getHeight())
            setHeight(e.getHeight());
        if(e.getWidth() > getWidth())
            setWidth(e.getWidth());
    }

    protected void removeEntity(Entity e){
        entities.remove(e.getID());
        for(int i = e.getID(); i < entities.size(); i++){
            entities.get(i).decreaseID();
        }
    }

    protected void moveToTop(Entity e){
        removeEntity(e);
        addEntity(e);
    }
}
