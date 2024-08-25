import java.util.*;
public class binser {
    public static void sort(int a[])
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
public static void main(String[] args) {
    int low=0 , mid=0 , c=0;
    Scanner sc  = new Scanner(System.in);
    System.out.println("Enter size of items:");
    int s = sc.nextInt();
    int a[] = new int[s];
    System.out.println("Enter items ");
    for (int i = 0; i<s; i++)
    {
        a[i] = sc.nextInt();
    }
    sort(a);
    System.out.println("sorted array");
    for (int i=0;i<s;i++)
    {
        System.out.print(a[i]+" ");

    }
    System.out.println();
    

    System.out.println("enter value for search:");
    int v = sc.nextInt();
    int high = s-1;
    while(low <= high)
    {
        mid=(low+high)/2;

        if(a[mid]==v)
        {
            c++;
            break;
        }
        else if (a[mid]< v)
        {
            low = mid+1;
        }
        else if(a[mid] > v)
        {
            high = mid-1;
        }

    }
    if (c > 0)
    {
        System.out.println("item was found at:"+ (mid+1)+" location");
    }
    else 
    {
        System.out.println("item yaha nhi kahi or");
    }

}
}
