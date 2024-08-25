import java.util.*;

public class Function1 {
public static void printMyName(String name){
    System.out.println("My name is :"+name);
}

    public static void main(String[] args) {
        System.out.print("Enter name :");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();

        printMyName(name);
    }
}
