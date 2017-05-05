/**
 * SimpleHashSet- a Class that implements the interface of SimpleSet.
 * This class is the "Father" of the openHashSet class and the ClosedHashSet class.
 * it contains all the fields and methods that are needed in order to be a HashsSet.
 */
public abstract class SimpleHashSet implements SimpleSet {

    protected final static int INITIAL_CAPACITY = 16, INCREASE_FACTOR = 2;
    protected final static double DECREASE_FACTOR = 0.5;
    protected final static float UPPER_LOAD = 0.75f;
    protected final static float LOWER_LOAD = 0.25f;
    protected int capacity = INITIAL_CAPACITY;
    protected int size = 0;
    protected float upperLoadFactor, lowerLoadFactor;

    public SimpleHashSet() {
        upperLoadFactor = UPPER_LOAD;
        lowerLoadFactor = LOWER_LOAD;
    }

    /**
     * A method that do rehashing for a set when called.
     * if the bolVal is true - it will copy the exist set to a set with bigger capacity.
     * if bolVal is false - it will copy the exist set to a smaller capacity set
     * @param bolVal
     */
    public abstract void rehashing(boolean bolVal);

    /**
     * method to get the size of a data-structure
     * @return The number of elements currently in the set
     */
    public int size(){
        return size;
    }

    /**
     * method to get the capacity of a data-structure
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return capacity;
    }

    /**
     * method that checks if there is need to do rehahsing by the upperLoadFactor,
     * and call the rehashing method if necessary.
     * @param size - how many elements in the set
     * @param capacity - the current capacity of the set
     */
    public void checkUpperLoad(int size, int capacity){
        float loadFactor = (float)size/capacity;
        if(upperLoadFactor<loadFactor){
            rehashing(true);
        }
    }

    /**
     * method that checks if there is need to do rehahsing by the lowerLoadFactor,
     * and call the rehashing method if necessary.
     * @param size - how many elements in the set
     * @param capacity - the current capacity of the set
     */
    public void checkLowerLoad(int size, int capacity){
        float loadFactor = (float)size / capacity;
        if(lowerLoadFactor>loadFactor){
            rehashing(false);
        }
    }


}
