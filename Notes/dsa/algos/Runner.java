import java.util.Arrays;
import java.util.Random;

/* Test your methods and algos here */
public class Runner extends Base {
   public static void main(String[] args) {
      int[] randArr = getRandomIntArray(100_000_000);
      Arrays.sort(randArr);
   }
}

class Base {
   public static int[] getRandomIntArray(int length) {
      int[] arr = new int[length];
      Random random = new Random();
      for (int i = 0; i < length; i++) {
         arr[i] = random.nextInt(length * 10);
      }
      return arr;
   }

   public static void print(int[] arr) {
      System.out.println(Arrays.toString(arr));
   }

   public static void print(String msg) {
      System.out.println(msg);
   }
}