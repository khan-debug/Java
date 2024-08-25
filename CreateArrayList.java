
public class CreateArrayList<T> {

	T data[];
	int size;

	CreateArrayList()
	{
		this(1);
	}

	CreateArrayList(int inicap) {
		if(inicap>0)
		{
			this.data=(T[])new Object[inicap];
		}
		else if(inicap==0)
		{
			this.data=(T[])new Object[0];
		}
		else
		{
			throw new IllegalArgumentException("Capacity cannot be negative");
		}
	}

	public void add(T value)
	{
		ensureCapacity();
		data[size]=value;
		size++;
	}

	public void ensureCapacity()
	{
		if(data.length<=size)
		{
			int oldcap=data.length;
			int newcap=oldcap+1;

			T temp[]=(T[]) new Object[newcap];
			for (int i = 0; i < data.length; i++) {
				temp[i]=data[i];
			}
			data=temp;
		}
	}

	public void add(int index,T element)
	{
		if(index<0 || index>size)
		{
			System.out.println("index is out of range");
		}
		ensureCapacity();
		for (int i = size-1; i >=index; i--) {
			data[i+1]=data[i];
		}
		data[index]=element;
		size++;

	}

	public T remove(int index)
	{
		if(index<0 || index>size)
		{
			throw new ArrayIndexOutOfBoundsException("Index out of range");
		}
		T temp=data[index];
		for (int i = index; i < data.length-1; i++) {
			data[i]=data[i+1];
		}
		size--;
		data[size]=null;
		return temp;
	}

	public int indexOf(T v)
	{
		if(v==null)
		{
			return -1;
		}
		else
		{
			for (int i = 0; i < size; i++) {
				if(data[i]==v)
				{
					return i;
				}
			}
		}
		return -1;
	}

	public boolean contains(T value)
	{
		if(indexOf(value)!=-1)
		{
			return true;
		}
		else
			return false;
	}

	public T get(int index)
	{
		if(index<0 || index>size)
		{
			throw new ArrayIndexOutOfBoundsException("Index out of range");
		}

		return data[index];
	}

	public void display()
	{
		System.out.print("[");
		for (int i = 0; i < data.length; i++) {
			if(data[i]!=null)
			{
				System.out.print(data[i] + ", ");
			}
		}
		System.out.print("]");
		System.out.println();
	}

	public static void main(String[] args) {
			CreateArrayList c=new CreateArrayList(3);
			c.add(5);
			c.add(8);
			c.add(7);
		c.add(1, 6);
		c.display();
		//c.remove(2);
		//c.display();
		System.out.println(c.indexOf(7));
		System.out.println(c.contains(8));

	}

}
