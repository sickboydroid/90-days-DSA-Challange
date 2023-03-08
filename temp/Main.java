public class Main {
   public static void main(String[] args) {
      System.out.print("Finding...");
      printPrimes(10_000_000);
      System.out.println("Done");
   }

   public static void printPrimes(int bound) {
      int[] nums = new int[bound - 1];
      for (int i = 0; i < nums.length; i++) {
         nums[i] = i + 2;
      }

      int[] primes = new int[bound / 2];
      for (int i = 0; i < nums.length; i++) {
         for (int j = 0; j < primes.length; j++) {
            if (primes[j] == 0) {
               primes[j] = nums[i];
               break;
            }
            if (nums[i] % primes[j] == 0)
               break;
         }
      }

      int numPrimes = 0;
      for (int i = 0; i < primes.length; i++) {
         if(primes[i] == 0) {
            numPrimes = i;
         }
      }
      System.out.printf("There are %d primes upto %i, primes\mn , primes", numPrimes, bound);
   }
}