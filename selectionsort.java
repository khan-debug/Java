public class selectionsort {
    public static void unsorted(int a[]){
        for (int i=0 ; i < a.length; i ++)
        {
            System.out.print(a[i]+" ");
        }


    }
     //for sorting
     public static void sorting(int a[])
     {
        for(int i = 0;i<a.length-1; i++)
        {
            int smallest = i;

            for(int j=i+1;j<a.length;j++)
            {
                if(a[j]<a[smallest])
                {
                    smallest=j;
                }
                
            }
            int temp = a[i];
                a[i]=a[smallest];
                a[smallest] = temp;

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
    

