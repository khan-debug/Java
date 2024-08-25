public class zeroonepattern {

    public static void main(String[] args) {
        
        int n=5;
    
            /* second is 0 and 1 pattern
         1
         01
         101
         0101
         10101
         */


        System.out.println("\nnow 0 and 1 pattern ");
        System.out.println("--------------------");

        /* now we have inverted star method 
          *****
          ****
          ***
          **
          *
         */

         
for(int i=1; i<=n; i++)
        {
               for(int j=1; j<=i; j++)
               {
                int sum = i + j;
                       if(sum % 2 == 0)
                       {
                        System.out.print("1");
                       }else {System.out.print("0");
                       }
                }
               System.out.println();   
        }
        

     }
     
    }

