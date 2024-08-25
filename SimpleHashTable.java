public class SimpleHashTable {
    private static final int TABLE_SIZE = 10; // Size of the hash table
    private Object[] table;

    public SimpleHashTable() {
        table = new Object[TABLE_SIZE];
    }

    // Basic hash function: modulo operation
    private int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // Insert a key-value pair into the hash table
    public void put(int key, Object value) {
        int index = hashFunction(key);
        if (table[index] == null) {
            table[index] = value;
        } else {
            // Handle collision: simple linear probing
            int newIndex = (index + 1) % TABLE_SIZE;
            while (table[newIndex] != null) {
                newIndex = (newIndex + 1) % TABLE_SIZE;
            }
            table[newIndex] = value;
        }
    }

    // Retrieve a value based on the key
    public Object get(int key) {
        int index = hashFunction(key);
        while (table[index] != null) {
            // Check if the key matches
            // In a real-world scenario, you might compare keys more robustly
            return table[index];
        }
        return null; // Key not found
    }

    public static void main(String[] args) {
        SimpleHashTable hashTable = new SimpleHashTable();

        // Insert key-value pairs
        hashTable.put(5, "Value1");
        hashTable.put(15, "Value2");
        hashTable.put(25, "Value3");

        // Retrieve values
        System.out.println("Value for key 5: " + hashTable.get(5));
        System.out.println("Value for key 15: " + hashTable.get(15));
        System.out.println("Value for key 25: " + hashTable.get(25));
        System.out.println("Value for key 35: " + hashTable.get(35)); // Not found
    }
}
