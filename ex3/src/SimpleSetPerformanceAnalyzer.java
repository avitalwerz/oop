/**
 * A class that run all the tests should be measured on a data-structure.
 */

import java.util.*;
public class SimpleSetPerformanceAnalyzer {

    private static final float MILI_SECOND = 1000000f;
    private static final int WARM_UP_TIME = 70000, LINKED_LIST_WARM_UP= 7000;
    private static final int CAPACITY = 5;
    private static final int openHashIndex = 0;
    private static final int closedHashIndex = 1;
    private static final int treeIndex = 2;
    private static final int linkedListIndex = 3;
    private static final int hashIndex = 4;
    private  SimpleSet[] structures;
    private String[] data1, data2;
    private String contains1 = "hi", contains2 = "-13170890158", contains3 ="23";

    /**
     * the constructor. initialize an array of SimpleSet objects and create object for each
     * type of SimpleSet
     */
    public SimpleSetPerformanceAnalyzer() {
        data1 = Ex3Utils.file2array("data1.txt");
        data2= Ex3Utils.file2array("data2.txt");
        structures = new SimpleSet[CAPACITY];

        structures[openHashIndex] = new OpenHashSet();
        structures[closedHashIndex] = new ClosedHashSet();
        structures[treeIndex] = new CollectionFacadeSet(new TreeSet<String>());
        structures[linkedListIndex] = new CollectionFacadeSet(new HashSet<String>());
        structures[hashIndex] = new CollectionFacadeSet(new LinkedList<String>());

    }

    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer analyzer = new SimpleSetPerformanceAnalyzer();
        analyzer.doTest(openHashIndex);
        analyzer.doTest(closedHashIndex);
        analyzer.doTest(treeIndex);
        analyzer.doTest(linkedListIndex);
        analyzer.doTest(hashIndex);
    }


    /**
     * A method that run all the tests one data-structure need to perform.
     * @param index that indicates the type of the SimpleSet
     */
    public void doTest(int index){
        addData(data1, index);
        addData(data2, index);
        containsTest(data1, contains1, index);
        containsTest(data1, contains2, index);
        containsTest(data2, contains3,index);
        containsTest(data2, contains1, index);
    }


    /**
     * A general method for adding data to a given structure (given by its index),
     * and also measure the time required to perform the operation.
     * @param data -  the data we want to insert
     * @param index - indicates the type of the structure
     */
    public void addData(String[] data, int index){
        long timeBefore = System.nanoTime();
        for(String element: data){
            structures[index].add(element);
        }
        long difference = System.nanoTime() - timeBefore;
        difference /= MILI_SECOND;
        System.out.println("set num "+ index +" add results: ");
        System.out.println(difference);
        createNewObject(index);
    }


    /**
     * A general method to locate an element in a given structure (given by its index),
     * and also measure the time required to perform the operation.
     * firstly we add the data we want to check and then check if the element is there.
     * @param data
     * @param str - the element we are looking for
     * @param index - indicated the type of the structure
     */
    public void containsTest(String[] data, String str, int index){
        long difference;
        addData(data, index);
        if(index != linkedListIndex){
        for (int i =0; i<WARM_UP_TIME; i++){
            structures[index].contains(str);
        }

        long timeBefore = System.nanoTime();
        for (int j =0; j<WARM_UP_TIME; j++){
        structures[index].contains(str);
        }
        difference = System.nanoTime() - timeBefore;
        difference /= WARM_UP_TIME;

        }else { //case of linkedlist
            long timeBefore = System.nanoTime();
            for(int i=0; i<LINKED_LIST_WARM_UP; i++){
                structures[index].contains(str);
            }
        difference = System.nanoTime() - timeBefore;
        difference /= LINKED_LIST_WARM_UP;
        }
        createNewObject(index);
        System.out.println("set num " + index +" contains "+ str.toString() + " results: ");
        System.out.println(difference);

    }

    /**
     * A method that re-initialize a given structure(given by an index),
     * by creating new "empty" structure after every test we make.
     * @param index - the index of the structure we worked on and want to re-initialize
     */
    public void createNewObject(int index){
        switch (index){
            case openHashIndex:
                structures[index] = new OpenHashSet();
                break;
            case closedHashIndex:
                structures[index] = new ClosedHashSet();
                break;
            case treeIndex:
                structures[index] = new CollectionFacadeSet(new HashSet<String>());
                break;
            case linkedListIndex:
                structures[index] = new CollectionFacadeSet(new LinkedList<String>());
                break;
            case hashIndex:
                structures[index] = new CollectionFacadeSet(new HashSet<String>());

        }
    }

}
