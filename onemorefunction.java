import java.util.*;

public class onemorefunction {
    public static int calculateSum(int a , int b){
        int sum = a+b;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("enter values for sum:");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int sum = calculateSum( a , b);
        System.out.println("sumed value is :"+sum);
    }
}
