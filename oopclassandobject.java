// our class with its properties
class pen{
String color;
String type;
public void write()
{
System.out.println("\nwrites anything according to will");
}
public void printcolor()
{
    System.out.println(this.color);
}
public void printtype()
{
    System.out.println(this.type);
}
}

// our main function 
public class oopclassandobject {
    public static void main(String[] args) {
        pen pen1 = new pen();
        pen1.color = "green";
        pen1.type = "ball";
        pen1.write();

        pen pen2 = new pen();
        pen2.color = "black";
        pen2.type = "gel";
        pen1.printcolor();
        pen2.printcolor();
        



    }
}
