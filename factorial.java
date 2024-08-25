import java.util.*;

public class factorial {
    public static void main(String[] args) {
        int j = 1 ;
        int i;
        int a;
        
        System.out.print("enter any number for factorial:");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
       if(a<0){
        System.out.println("invalid number");
       }
       else{
        for ( i = 1 ; i <= a ; i++)
        {
        
            j = j * i ;
        }
        System.out.println("factorial is:"+j);
    }     
}    
}
