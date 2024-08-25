import java.util.Scanner;

public class ArraySorter {

    // Function to sort an array
    static void sortArray(int[] arr) {
        for (int i=0; i<7; i++)
        {
             for (int j=0; j<7-i-1; j++)
        {
            if(arr[j]>arr[j+1]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j]=temp;
            }
        }
        }
    }

    // Function to display an array
    static void displayArray(int[] arr) {
        System.out.print("Sorted Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input of 7 numbers in an array
        int[] numbers = new int[7];
        System.out.println("Enter 7 numbers:");

        for (int i = 0; i < 7; i++) {
            System.out.print("Enter number at position " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }

        // Sorting the array using the function
        sortArray(numbers);

        // Displaying the sorted array
        displayArray(numbers);
    }
}