//compile time polymorphism
class Studentc{
String name;
int age;

public void printInfo(String name)
{
	System.out.print(name+":");
}
public void printInfo(int age)
{
	System.out.print(age+":");
}
public void printInfo(String name, int age){
	System.out.println(name+":");
	System.out.print(age+"");
	
}
}

// our main function 
public class functionoverloading {
    public static void main(String[] args) {

        Studentc s1 = new Studentc();
        s1.name="khan";
        s1.age=18;
        
        
        		s1.printInfo(s1.name, s1.age);
    	
    }
}
