public class Recursionsum{
    public static void printNumb(int i, int n, int sum){
        if(i==n){
            sum = sum + i;
            System.out.println(sum);
            return;
        }
        sum = sum + i ;

        i=i+1;
        printNumb(i, n, sum);
    }


    public static void main(String args[])
    {

        printNumb(1, 5, 0);
    }
}