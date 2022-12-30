import java.util.*;

public class Search {
	static final int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter target num: ");
		int target = sc.nextInt();
		printResult("Linear Search", linearSearch(target));
	}

	static void printResult(String algoName, boolean foundTarget) {
		if(foundTarget)
			System.out.printf("Found: %s\n", algoName);
		else
			System.out.printf("Not found: %s\n", algoName);
	}		

	static boolean linearSearch(int target) {
		for(int i = 0; i < data.length; i++) {
			if(target == data[i])
				return true;
		}
		return false;
	}
}
