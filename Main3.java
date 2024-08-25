import java.util.*;
public class TestArrayList<M> {
    M data[];
    int size;

    TestArrayList() {
        this(1);
    }

    public void display() {
        // System.out.print("[");
        for (int i = 0; i < size; i++) {
            // if (data[i] != null) {
                System.out.print(data[i]);
                 if (i < size - 1) {
                    System.out.print(", ");
                }
    }


    public static void main(String[] args) {
        TestArrayList<Integer> c = new TestArrayList<>();
        c.add(5);
        c.add(8);
        c.add(7);
	   c.add(9);
        c.add(7);
        c.display();
    }

   

    
}
