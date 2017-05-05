/**
 * ClosedHashSEt class:
 * A class that represents data structure of closed-hash-set.
 * data structure that build as an array of String, extends from SimpleHashSet class.
 * the class implements addind data to the stucture, rehashing, locating an element etc..
 */
public class ClosedHashSet extends SimpleHashSet {

    private String[] strArray;
    private final static String EMPTY_STR = "";

    /**
     * A default constructor.Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        strArray = new String[capacity];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor - the upper load factor of the hash table
     * @param lowerLoadFactor - the lower load factor of the hash table
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        strArray = new String[capacity];
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * builds the hash set by adding the elements one by one.
     * The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(String[] data) {
        super();
        strArray = new String[capacity];
        for (String element : data) {
            add(element);
        }
    }


    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set, true is was added
     */
    public boolean add(String newValue) {
        boolean wasAdded = false;
        int i = 0;
        if (contains(newValue)) {
            return wasAdded;
        }
        while (!wasAdded) {
            int index = getIndex(i, newValue);
            if (strArray[index] == null || strArray[index] == EMPTY_STR) {
                strArray[index] = newValue;
                size++;
                wasAdded = true;
                checkUpperLoad(size, capacity); // check of capacity after inserting new element to the set
            } else {
                i++;
            }
        }
        return wasAdded;
    }


    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int i = 0;
        boolean isFound = false;
        while (!isFound) {
            int index = getIndex(i, searchVal);
            if (strArray[index] == null) {
                break; // if reached to null, searchVal not in the set.
            }
            if (searchVal.equals(strArray[index])) {
                isFound = true;
            } else {
                i++;
            }
        }
        return isFound;
    }


    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        int i = 0; //num of tries
        boolean wasDeleted = false;
        if (!contains(toDelete)) {
            return wasDeleted;
        }
        while (!wasDeleted) {
            int index = getIndex(i, toDelete);
            if (strArray[index].equals(toDelete)) {
                strArray[index] = EMPTY_STR;
                size--;
                checkLowerLoad(size, capacity);
                wasDeleted = true;
            }else{ i++;
             }
         } return wasDeleted;
    }


    /**
     * A method that get the index of a given value.
     *
     * @param value
     * @return int of the index
     */
    public int getIndex(int i, String value) {
        return value.hashCode() + (i + i ^ 2) / 2 & capacity - 1;
    }


    /**
     * A method that do rehashing for a set when called.
     * if the bolVal is true - it will copy the exist set to a set with bigger capacity.
     * if bolVal is false - it will copy the exist set to a smaller capacity set
     *
     * @param bolVal
     */
    public void rehashing(boolean bolVal) {
        String[] oldStrArray = strArray;
        if (bolVal) {
            capacity *= INCREASE_FACTOR;
            strArray = new String[capacity];
        } else {
            capacity *= DECREASE_FACTOR;
            strArray = new String[capacity];
        }
        for (String element : oldStrArray) {
            if (element != null && element != EMPTY_STR) {
                rehashInsert(element);
            }

        }
    }

    /**
     * A helper for rehashing. method that insert the elements to the new array
     * without checking contains.
     * @param element
     */
    public void rehashInsert(String element) {
        int i = 0;
        boolean wasAdded = false;
        while (!wasAdded) {
            int index = getIndex(i, element);
            if (strArray[index] == null || strArray[index] == EMPTY_STR) {
                strArray[index] = element;
                wasAdded = true;
            } else {
                i++;
            }
        }
    }

}