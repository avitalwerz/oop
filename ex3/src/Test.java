/**
 * Created by avita on 5/2/2017.
 */
public class Test {

    public static void main(String[] args) {
        String[] data1_arr = Ex3Utils.file2array("data1.txt");
        String[] data2_arr = Ex3Utils.file2array("data2.txt");


        OpenHashSet open = new OpenHashSet(data2_arr);

    }


}
