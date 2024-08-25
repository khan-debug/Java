import java.util.Arrays;

public class TestArraylist1<M> {
    M data[];
    int size;

    TestArraylist1() {
        this(1);
    }

    TestArraylist1(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.data = (M[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
    }

    public void ensureCapacity() {
        if (data.length <= size) {
            int oldcap = data.length;
            int newcap = oldcap * 2; // Updated to double the capacity

            M temp[] = Arrays.copyOf(data, newcap);
            data = temp;
        }
    }

    public void display() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if (data[i] != null) {
                System.out.print(data[i]);
                if (i < size - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public void add(M value) {
        ensureCapacity(); // Call ensureCapacity before adding an element
        data[size] = value;
        size++;
    }

    public static void main(String[] args) {
        TestArraylist1<Integer> c = new TestArraylist1<>(2);
        c.add(5);
        c.add(8);
        c.add(7);

        c.display();
    }
}
