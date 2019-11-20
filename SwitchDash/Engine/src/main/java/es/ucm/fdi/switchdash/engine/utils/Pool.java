package es.ucm.fdi.switchdash.engine.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Pool of objects to reuse previous existing instances instead of creating new ones.
 * @param <T> type of the objects in the pool
 */
public class Pool<T>
{
    // ----------ATTRIBUTES---------- //

    private final int maxSize;

    private final PoolObjectFactory<T> factory;
    private final List<T> freeObjects;


    // ----------FUNCTIONS---------- //

    /**
     * Creates a new object of the type given.
     * @param <T> type of the object to create
     */
    public interface PoolObjectFactory<T>
    {
        public T createObject();
    }

    /**
     * Creates a new pool given an object factory to generate new instances.
     * @param factory: the instances generator
     * @param maxSize: the max size of the pool
     */
    public Pool(PoolObjectFactory<T> factory, int maxSize)
    {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }


    /**
     * Reuses an object from the pool, or creates a new one if no other is available.
     * @return the object from the pool
     */
    public T newObject()
    {
        T object = null;
        if (freeObjects.isEmpty())
            object = factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size() - 1);
        return object;
    }


    /**
     * Adds an object to the pool so it is available for reusing.
     * @param object: the object to add to the pool
     */
    public void free(T object)
    {
        if(freeObjects.size() < maxSize)
            freeObjects.add(object);
    }


}
