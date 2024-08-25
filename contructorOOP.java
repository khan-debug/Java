
class Student{
String name;
int age;
public void printinfo(){
	System.out.print(this.name+":");
	System.out.println(this.age);
}
Student(String name, int age)
{
      this.name=name ;
      this.age =age ;
}
}

// our main function 
public class contructorOOP {
    public static void main(String[] args) {

        Student s1 = new Student("khan" ,20);
        Student s2 = new Student("sara" ,18);

        		s1.printinfo();
        		s2.printinfo();
    	
    }
}
