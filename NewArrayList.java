import java.util.ArrayList;

public class NewArrayList {

    public static void print(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(9);
        a.add(7);
        a.add(10);

        print(a);
    }
}
