import java.util.Scanner;
public class Insertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    System.out.println("enter number of arrays:");
    int n = sc.nextInt();
    int[] a = new int [n];
    System.out.println("Enter values of array:");
    for (int i = 0 ; i < n;i++) 
                                  {
                                    a[i]=sc.nextInt();
                                  }
     int[] b = new int [n+1];
         System.out.println("enter position of inserted value:");
         int l = sc.nextInt();
         int p = l-1;
         if(p>a.length+1){
            System.out.println("wrong answer:");
         }
         else 
         {
         System.out.println("enter the value:");
         int v = sc.nextInt();

         for ( int i=0 ; i < n+1; i++)
                     {
                       if(i<p)
                       { 
                       b[i]=a[i];
                         }
                          else if(i==p)
                            {
                                   b[i]=v;
                            }
                                  else
                                  {
                                     b[i]=a[i-1];
                                  }
                                  
                                }
                                System.out.println("Value are:");
                                for (int i = 0 ; i < n+1;i++)
                                  {
                                    System.out.println(b[i]);
                                  }
                              } 


        }
                        }

                             
            
 

