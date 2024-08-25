import java.util.*;
public class multmatrix {
   public static void main(String args[]) {
       Scanner sc = new Scanner(System.in);
       
       int rows = 2;
       int cols = 2;
       int n=2;


       int[][] numbers = new int[rows][cols];
       int[][] a = new int[rows][cols];
       int[][] result = new int[rows][cols];


       //input
       //rows
       System.out.println("Enter values in rows:");
       for(int i=0; i<rows; i++) {
           //columns
           System.out.println("Enter values in row: "+(i+1));
           for(int j=0; j<cols; j++) {
                System.out.println("Enter values for "+(i+1)+ "column");
               numbers[i][j] = sc.nextInt();
           }
       }

       System.out.println("For second matrix");
       System.out.println("Enter values in rows:");
       for(int i=0; i<rows; i++) {
           //columns
           System.out.println("Enter values in row: "+(i+1));
           for(int j=0; j<cols; j++) {
                System.out.println("Enter values for "+(i+1)+ "column");
               a [i][j] = sc.nextInt();
           }
       }









       System.out.println("Matrix");

       for(int i=0; i<rows; i++) {
           for(int j=0; j<cols; j++) {
                   System.out.print(numbers[i][j]+" ");
               }
               System.out.println();
           }

           System.out.println("Matrix");

       for(int i=0; i<rows; i++) {
           for(int j=0; j<cols; j++) {
                   System.out.print(a [i][j]+" ");
               }
               System.out.println();
           }

           for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += numbers [i][k] * a [k][j];
                }
            }
        }

        System.out.println("Matrix");

       for(int i=0; i<rows; i++) {
           for(int j=0; j<cols; j++) {
                   System.out.print(result [i][j]+" ");
               }
               System.out.println();
           }








        }
    }
    
