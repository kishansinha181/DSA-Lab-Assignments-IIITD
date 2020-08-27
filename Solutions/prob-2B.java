//A modified Recursive Fibonacci Number problem

import java.util.Scanner;

public class Main {
	public static int countWays (int numSteps) {
		if (numSteps < 0)
			return 0;
		if (numSteps == 0)
			return 1;
		return countWays(numSteps - 1) + countWays(numSteps - 2) + countWays(numSteps - 3);
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner (System.in);
		
		int numSteps = sc.nextInt();
		int numWays = countWays(numSteps);

		System.out.println(numWays);
	}
}
