import java.util.*;
abstract class Animal {
   abstract void walk();
   void breathe() {
 System.out.println("This animal breathes air");
   } 
   
}


class Horse extends Animal {
   void Horse() {
       System.out.println("Wow, you have created a Horse!");
   }
   void walk() {
       System.out.println("Horse walks on 4 legs");
   }
   void run() {
       System.out.println("This animal can run very fast\nIt is loyal for the owner\nIt needs more food");
   }
}


class Chicken extends Animal {
   Chicken() {
       System.out.println("Wow, you have created a Chicken!");
   }
   void walk() {
       System.out.println("Chicken walks on 2 legs");
   }
   void eggs() {
       System.out.println("This animal can lay eggs\nIt is small in size\nIt can be eaten for survival\nvery less amount of food in needed");
   }
}


public class abstractclass {
   public static void main(String args[]) {
    //   Horse horse = new Horse();
    //   horse.walk();
    //   horse.breathe();
    System.out.println("You are about to create an Animal.");
    System.out.println("Small amount of food is needed for chicken\nlarge amount is for horse");
    System.out.println("presss 1 if you want to create a horse\npress 2 if you want to create a chicken");
    Scanner sc = new Scanner(System.in);
    int a = sc.nextInt();
    if (a==2){
      Chicken chicken = new Chicken();
            
      chicken.walk();
      chicken.breathe();
      chicken.eggs();
    }else{
        Horse horse = new Horse();
        horse.walk();
        horse.breathe();
        horse.run();
    }
   }
}

