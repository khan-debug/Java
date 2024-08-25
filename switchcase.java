import java.util.*;

public class switchcase {

    public static void main(String[] args) {
        int button;
        System.out.println("Enter 1st number:");
        Scanner sc = new Scanner(System.in);
         int a = sc.nextInt();
        System.out.println("Enter Second number:");
         Scanner dc = new Scanner(System.in);
        int  b = dc.nextInt();
        do{
         System.out.println("press 1 for addition");
         System.out.println("press 2 for substraction");
         System.out.println("press 3 for multiplication");
         System.out.println("press 4 for division");
        System.out.println("press 9 to exit");

        Scanner fc = new Scanner(System.in);
         button = fc.nextInt();
    
   
switch(button) {
case 1 : System.out.println("a+b:"+(a+b));
break;
case 2 : System.out.println("a-b:"+(a-b));
break;
case 3 : System.out.println("a*b"+(a*b));
break; 
case 4 : System.out.println("a/b:"+(b/a));
System.out.println("and reminder:"+(b%a));
break;
case 9 : System.out.println("okay");
break;
default: System.out.println("invalid button");

}

}while(button!=9);

}
    }

