public class insertionsort{
    public static void unsorted(int a[]){
        for (int i=0 ; i < a.length; i ++)
        {
            System.out.print(a[i]+" ");
        }


    }
     //for sorting
     public static void sorting(int a[])
     {
        for(int i=1; i<a.length; i++) {
            int current = a[i];
            int j = i - 1;
                while(j >= 0 && a[j] > current) {
                    //Keep swapping
                    a[j+1] = a[j];
                    j--;
                }
            a[j+1] = current;
        }
    }
 
    

    public static void print(int a[])
    {
        System.out.println("sorted array:\n");
        for(int i = 0 ; i<a.length;i++)
        {
            System.out.print(a[i]+" ");
        }

    }
    public static void main(String[] args) {
        int[] a = {9,5,6,7,8,2};
        System.out.println("unsorted array is:");
        //printing array
        unsorted(a);
        sorting(a);
        print(a);
        
        }
}