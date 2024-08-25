
class Students{
String name;
int age;
public void printinfo(){
	System.out.print(this.name+":");
	System.out.println(this.age);
}
Students(Students s2)
{
  this.name=s2.name;
  this.age=s2.age;
}
Students(){

}
}

// our main function 
public class copyconstructor {
    public static void main(String[] args) {

        Students s1 = new Students();
        s1.name="khan";
        s1.age=18;

        Students s2 = new Students(s1);

        		// s1.printinfo();
        		s2.printinfo();
    	
    }
}
