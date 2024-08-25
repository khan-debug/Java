import java.util.*;
public class deletion {
    
    public static void main(String[] args) {
         int c;
        Scanner sc = new Scanner(System.in);
        System.out.print("enter size of array:");
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n-1];
        System.out.println("Enter Array:");
       for (int i=0; i<n; i++)
       {
        a[i] = sc.nextInt();
       }
        System.out.println("Enter the number you want to delete from array");
        int v = sc.nextInt();
        for(int i = 0 ; i < n; i++)
        {
            if(a[i]<v)
            {
                b[i] = a[i];
             }
             else if (a[i]==v)
             continue;
             else 
             {
                b[i-1] = a[i];
             }
        }
        System.out.println("Array after deletion:");
        for(int i=0;i<n-1; i++)
        {
            System.out.println(b[i]);
        }
    }
}
