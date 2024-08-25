public class pattern {
    public static void main(String[] args) {
      int n=5;


      //this program contains pattern methods 
      //first we have paramid * pattern 
      /**
        **
        ****
        *****
        ****** */


        for(int i=1; i<=n; i++)
        {
               for(int j=1; j<=i; j++)
               {
                       System.out.print("*");
               }
                 System.out.println("");
        }

}
}


