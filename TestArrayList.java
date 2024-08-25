public class TestArrayList<M> {
    M data[];
    int size;

    TestArrayList() {
        this(1);
    }

    // TestArrayList(int initialCapacity) {
    //     if (initialCapacity > 0) {
    //         this.data = (M[]) new Object[initialCapacity];
    //     } else if (initialCapacity == 0) {
    //         this.data = (M[]) new Object[0];
    //     } else {
    //         throw new IllegalArgumentException("Capacity cannot be negative");
    //     }
    // }

    public void display() {
        // System.out.print("[");
        for (int i = 0; i < size; i++) {
            // if (data[i] != null) {
                System.out.print(data[i]);
                 if (i < size - 1) {
                    System.out.print(", ");
                }
            // }
        }
        // System.out.print("]");
        // System.out.println();
    }
	// public void ensureCapacity()
	// {
	// 	if(data.length<=size)
	// 	{
	// 		int oldcap=data.length;
	// 		int newcap=oldcap+1;

	// 		M temp[]=(M[]) new Object[newcap];
	// 		for (int i = 0; i < data.length; i++) {
	// 			temp[i]=data[i];
	// 		}
	// 		data=temp;
	// 	}
	// }
	//  public void add(M value) {
    //     ensureCapacity();
    //     data[size] = value;
    //     size++;
    // }

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
