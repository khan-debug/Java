
public class GenericArray<T> {
	T arr[];
	int size;
	int i=0;
	
	GenericArray(int s)
	{
		this.size=s;
		arr=(T[]) new Object[size]; 
	}	
	public void add(T value)
	{
		if(i<size)
		{
			arr[i]=value;
			i++;
		}
	}
	public void display()
	{
		for (int x = 0; x < arr.length; x++) {
			System.out.print(arr[x] + "\t");
		}
	}
	public static void main(String[] args) {
		GenericArray<Integer> g1=new GenericArray<>(4);
		g1.add(5);
		g1.add(8);
		g1.add(32);
		g1.add(95);
		g1.display();
		
		GenericArray<String> g2=new GenericArray<String>(2);
		g2.add("Sana");
		g2.add("Ali");
		g2.display();

	}

}