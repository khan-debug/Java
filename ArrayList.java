import java.util.ArrayList;

class ArrayLists {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // add elements
        list.add(0);
        list.add(2);
        list.add(3);
        System.out.println(list);

        // get elements
        int elements = list.get(0);
        System.out.println(elements);

        // to add element in between
        list.add(1, 1);
        System.out.println(list);

        // set elements
        list.set(0, 5);
        System.out.println(list);

        // delete element
        list.remove(2); // Corrected index to remove
        System.out.println(list);

        // size
        int size = list.size();
        System.out.println(size);

        // by loop
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
