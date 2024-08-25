import java.util.*;

public class helloworld {
    public static void main(String[] args) {
        System.out.println("enter value for a and b:");
      Scanner sc = new Scanner (System.in);
      int a = sc.nextInt();
      int b = sc.nextInt();

    int sum=a+b;
    int diff=a-b;
System.out.println("a is:"+a);
System.out.println("b is:"+b);
    System.out.println("Sum of a and b is:"+sum);
    System.out.println("diff of a and b is:"+diff);
    System.out.println("multiple of a and b:"+a*b);
    int ans= (a*b) / (a-b) ;
    System.out.println("answer of (a*b)/(a-b):"+ans);
    }
}

