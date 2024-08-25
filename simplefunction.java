import java.util.*;
public class simplefunction {
    public static void printMyName(String name)
    {
        System.out.println("name is:"+name);
        return;
    }
public static void main(String[] args) {
    System.out.print("name:");
    Scanner sc = new Scanner(System.in);
    String name = sc.next();
    printMyName(name);




}


}
