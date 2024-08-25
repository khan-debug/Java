import java.util.Scanner;

public class sorting {
   
    static void sortingarr(int[] arr, int b) {
        int c = 0;
        for (int i = 0; i < 7; i++) {
            if (b == arr[i]) {
                c++;
            }
        }
        if (c >= 1) {
            System.out.println("Number " + b + " is found");
        } else {
            System.out.println("Number " + b + " not found");
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 7 numbers:");
        for (int i = 0; i < 7; i++) {
            System.out.print("Enter " + (i + 1) + " number: ");
            arr[i] = sc.nextInt();
        }

        System.out.println("Search for number:");
        int b = sc.nextInt();

        System.out.println();
        sortingarr(arr, b);
    }
}
