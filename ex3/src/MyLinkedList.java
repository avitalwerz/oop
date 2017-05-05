/**
 * MyLinkedList class is a wrapper for the java's linkedlist objects.
 * it extends from CollectionFacadeSet.
 * and have a single method - the iterator mathod.
 */

import java.util.Collection;

public class MyLinkedList extends CollectionFacadeSet {
    public MyLinkedList(Collection<String> collection) {
        super(collection);
    }

    /**
     * A method to make an iterator for the linkedlist
     * @return an iterator
     */
    public java.util.Iterator iterator(){
        return collection.iterator();
    }


}
