public class hashtable <k,v> {
    Entry bucket [];
    int size;
    hashtable(int s){
        size=s;
        bucket= new Entry [size];
        for (int i=0 ;i < bucket.length ; i++){
            bucket [i]= new Entry<k,v>();
        }
    }
    public void put (k key , v value){
        int index = key.hashCode()%size;
        Entry x = new Entry (key ,value);
        
        bucket[index]=x;
    }
    public void display() {
        for (int i = 0; i < bucket.length; i++) {
            Entry x = bucket[i];
            k uk = (k) x.key;
            v uv = (v) x.val;
            System.out.println("User Key: " + uk + "\t "
                    + "User Value: " + uv);
        }
    }

    public void searchbykey(k key) {
        int index = key.hashCode() % size;
        Entry entry = bucket[index];


        if (entry.key.equals(key)) {
            v val = (v) entry.val;
            System.out.println("Key found! Value: " + val);
        } else {
            System.out.println("Key not found!");
        }

    }
    public static void main(String[] args) {
        hashtable <Integer,String> a = new hashtable <Integer,String> (3);
        a.put(1, "usama");
        a.put(2, "jana");
        a.put(3, "hee");
        a.display();
        a.searchbykey(2);
        System.out.println("hello");
    }


}

