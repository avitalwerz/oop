import java.util.Collection;

public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {
    protected  Collection<java.lang.String> collection;

    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
    }

    /**
     * A method that adds elements to a given collection.
     * it checks whether the value is already in the collection -
     * if not, it will add it.
     * @param newVal - that we want to add
     * @return a boolean value - true if the element was added, false if it's already appear in the collection.
     */
    public boolean add(java.lang.String newVal){
        if(collection.contains(newVal)){
            return false;
        }return collection.add(newVal);}

    /**
     * check whether the given element appears in the collection
     * @param searchVal Value to search for
     * @return boolean value- true if the element appears and false if not.
     */
    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }

    /**
     *delete a given element from the collection.
     * @param deleteVal - the value we want to delete
     * @return true if the element was found and false if not
     */
    public boolean delete(java.lang.String deleteVal){
        return collection.remove(deleteVal);
    };

    /**
     * @return the number of elements are in the collection.
     */
    public int size(){
        return collection.size();
    }


}
