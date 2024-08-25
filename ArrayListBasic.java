import java.util.*;

public class ArrayListBasic{
    public static void printarray(ArrayList<Integer> l1){
        for (int i=0 ; i<l1.size() ; i++){
            System.out.print(" "+ l1.get(i) );
        }
    }
    public static void main(String[] args){

        ArrayList <Integer> l1 = new ArrayList<>();
        l1.add(7);
        l1.add(8);
        l1.add(5);
        l1.add(6);
        printarray(l1);

    }
}
