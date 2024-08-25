import java.util.*;
public class Binarysearch {

    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                low = mid + 1; // Discard left half
            } else {
                high = mid - 1; // Discard right half
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = new int[7];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 7 sorted numbers:");
        for (int i = 0; i < 7; i++) {
            System.out.print("Enter " + (i + 1) + " number: ");
            arr[i] = sc.nextInt();
        }

        System.out.println("Search for number:");
        int target = sc.nextInt();

        int result = binarySearch(arr, target);

        if (result != -1) {
            System.out.println("Number " + target + " found at index " + result);
        } else {
            System.out.println("Number " + target + " not found");
        }
    }
}