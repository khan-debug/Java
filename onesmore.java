import java.util.*;

public class onesmore {
    public static void main(String[] args) {
        int a,b;
        System.out.println("Enter number (a):");
        Scanner sc = new Scanner(System.in);
         a = sc.nextInt();
       
        System.out.println("Enter number :");
        Scanner dc = new Scanner(System.in);
         b = dc.nextInt();
        System.out.println("\nperforming logical equation:");
        System.out.println("============================\n");
         if(a==b){
            System.out.println("a is equals to b");
         }
         else if(a<b){
            System.out.println("a is less than b a<b");
         }
         else{
            System.out.println("a is greater than b a>b");
         }

         System.out.println("\nNow Even & odd");
         System.out.println("==============\n");
        if(a%2==0){
            System.out.println("a is even");
        }
        else {
            System.out.println("a is odd");
        }
        if(b%2==0){
            System.out.println("b is even");
        }
        else {
            System.out.println("b is odd");
        }
    }
}
