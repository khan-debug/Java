import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class AVLNode {
    int userId;
    int followerCount;
    AVLNode left, right;
    int height;

    public AVLNode(int userId) {
        this.userId = userId;
        this.followerCount = 0;
        this.left = this.right = null;
        this.height = 1;
    }
}

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    private int getHeight(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    private AVLNode insertUser(AVLNode root, int userId) {
        if (root == null) {
            return new AVLNode(userId);
        }

        if (userId < root.userId) {
            root.left = insertUser(root.left, userId);
        } else if (userId > root.userId) {
            root.right = insertUser(root.right, userId);
        } else {
            System.out.println("User with ID " + userId + " already exists. Duplicate user IDs not allowed.");
            return root;  // Duplicate userId not allowed
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && userId < root.left.userId) {
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && userId > root.right.userId) {
            return rotateLeft(root);
        }

        // Left Right Case
        if (balance > 1 && userId > root.left.userId) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Left Case
        if (balance < -1 && userId < root.right.userId) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public void addUser(int userId) {
        root = insertUser(root, userId);
        System.out.println("User with ID " + userId + " added successfully.");
    }

    private AVLNode findUser(AVLNode root, int userId) {
        if (root == null || root.userId == userId) {
            return root;
        }

        if (userId < root.userId) {
            return findUser(root.left, userId);
        } else if (userId > root.userId) {
            return findUser(root.right, userId);
        }

        return null;
    }

    public AVLNode deleteUser(AVLNode root, int userId) {
        if (root == null) {
            return root;
        }

        if (userId < root.userId) {
            root.left = deleteUser(root.left, userId);
        } else if (userId > root.userId) {
            root.right = deleteUser(root.right, userId);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.userId = findMin(root.right).userId;
            root.right = deleteUser(root.right, root.userId);
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateRight(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public void deleteUserById(int userId) {
        AVLNode deletedNode = deleteUser(root, userId);
        if (deletedNode != null) {
            System.out.println("User with ID " + userId + " deleted successfully.");
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int getFollowerCountById(int userId) {
        AVLNode userNode = findUser(root, userId);
        if (userNode != null) {
            return userNode.followerCount;
        } else {
            System.out.println("User with ID " + userId + " not found.");
            return -1;
        }
    }

    private void getTopUsers(AVLNode root, int n, List<AVLNode> result) {
        if (root != null) {
            getTopUsers(root.right, n, result);

            if (result.size() < n) {
                result.add(root);
                getTopUsers(root.left, n, result);
            }
        }
    }

    public List<AVLNode> retrieveTopUsers(int n) {
        List<AVLNode> result = new ArrayList<>();
        getTopUsers(root, n, result);
        return result;
    }
}
