import java.util.*;

public class if_else{
    public static void main(String[] args) {
        System.out.println("Enter age:");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        if(age>18){
            System.out.println("person is adult");
        }
        else{
            System.out.println("person is not adult");
        }
    }
}