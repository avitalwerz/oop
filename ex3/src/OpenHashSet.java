
 /**
 * OpenHashSet class:
 * A class that represents data structure of open-hash-set.
 * data structure that build as an array that each cell of it is a linkedlist,
 * extends from SimpleHashSet class.
 * the class implements addind data to the stucture, rehashing, locating an element etc..
 */

import java.util.LinkedList;
import java.util.Iterator;

public class OpenHashSet extends SimpleHashSet {
    private MyLinkedList[] openHash;

    /**
     * A default constructor. Constructs a new,
     * empty table with default initial capacity (16), upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        openHash = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            LinkedList<String> linkedList = new LinkedList<String>();
            openHash[i] = new MyLinkedList(linkedList);
        }
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor - the upper load factor of the hash table
     * @param lowerLoadFactor - the lower load factor of the hash table
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        openHash = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            LinkedList<String> linkedList = new LinkedList<String>();
            openHash[i] = new MyLinkedList(linkedList);
        }
    }


    /**
     * builds the hash set by adding the elements one by one
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        openHash = new MyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            LinkedList<String> linkedList = new LinkedList<String>();
            openHash[i] = new MyLinkedList(linkedList);
        }for (String element : data) {
                add(element);
            }
        }


    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set.
     * @return False iff newValue already exists in the set.
     */
    public boolean add(String newValue) {
        int index = getIndex(newValue);
        if (contains(newValue)) {
            return false;
        } else {
            openHash[index].add(newValue);
            size++;
            checkUpperLoad(size, capacity); // if element was added it check for capacity.
            return true;
        }
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int index = getIndex(searchVal);
        boolean isFound = false;
        if (openHash[index] == null) {
            return isFound;
        }else if (openHash[index].contains(searchVal)) {
            isFound =true;
        }return isFound;
    }


    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        int index = getIndex(toDelete);
        if (!contains(toDelete)) {
            return false;
        } else {
            openHash[index].delete(toDelete);
            size--;
            checkLowerLoad(size, capacity); // if element was deleted if checks for capacity
            return true;
        }
    }

    /**
     * A method that get the index of a given value.
     *
     * @param value
     * @return int of the index
     */
    public int getIndex(String value) {
        return value.hashCode() & capacity - 1;
    }

    /**
     * A method that do rehashing for a set when called.
     * if the bolVal is true - it will copy the exist set to a set with bigger capacity.
     * if bolVal is false - it will copy the exist set to a smaller capacity set
     * @param bolVal
     */
    public void rehashing(boolean bolVal) {
        MyLinkedList[] oldOpenHash = openHash;
        int oldCapacity = capacity;
        if (bolVal) {
            capacity *= INCREASE_FACTOR;
            openHash = new MyLinkedList[capacity];
        } else { // in case the bolVal is false
            capacity *= DECREASE_FACTOR;
            openHash = new MyLinkedList[(int)capacity];
        }
        for (int i = 0; i < capacity; i++) {
            LinkedList<String> linkedList = new LinkedList<String>();
            openHash[i] = new MyLinkedList(linkedList);
        }

        for (int i=0; i < oldCapacity; i++) {
            Iterator iter = oldOpenHash[i].iterator();
            while (iter.hasNext()) { //while next is not null
                Object value = iter.next();
                int index = getIndex(value.toString());
                openHash[index].add(value.toString()); //linkedList add method.
                }
            }
        }

    }

