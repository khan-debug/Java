import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTree socialMediaPlatform = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add a user");
            System.out.println("2. Delete a user");
            System.out.println("3. Get follower count");
            System.out.println("4. Get top N users with the highest follower count");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter user ID to add:");
                    int userIdToAdd = scanner.nextInt();
                    socialMediaPlatform.addUser(userIdToAdd);
                    System.out.println("User added successfully!");
                    break;

                case 2:
                    System.out.println("Enter user ID to delete:");
                    int userIdToDelete = scanner.nextInt();
                    socialMediaPlatform.deleteUserById(userIdToDelete);
                    System.out.println("User deleted successfully!");
                    break;

                case 3:
                    System.out.println("Enter user ID to get follower count:");
                    int userIdToGetFollowerCount = scanner.nextInt();
                    int followerCount = socialMediaPlatform.getFollowerCountById(userIdToGetFollowerCount);
                    if (followerCount != -1) {
                        System.out.println("Follower Count for User ID " + userIdToGetFollowerCount + ": " + followerCount);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter the value of N:");
                    int n = scanner.nextInt();
                    List<AVLNode> topUsers = socialMediaPlatform.retrieveTopUsers(n);
                    System.out.println("Top " + n + " users with the highest follower count: " + topUsers);
                    break;

                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
