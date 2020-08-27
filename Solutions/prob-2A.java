//This code is about using Recursion and basic funtions like power in an efficient way

import java.util.Scanner;

public class Main {
	public static long narcissistic_sum (long n, int num_digits) {
		if (n == 0)
			return 0;
		long digit = n % 10;
		n = n / 10;
		return pow(digit, num_digits) + narcissistic_sum(n, num_digits);
	}

	public static int count_num_digits (long n) {
		if (n == 0)
			return 0;
		n = n / 10;
		return 1 + count_num_digits(n);
	}

	public static long pow (long x, int p) {
		if (p == 0)
			return 1;
		return x * pow (x, p - 1);
	}

	public static void main (String[] args) {
		Scanner sc = new Scanner (System.in);
		int testcases = sc.nextInt();

		for (int i = 0; i < testcases; i++) {
			long n = sc.nextLong();
			int num_digits = count_num_digits(n);
			long digitSum = narcissistic_sum(n, num_digits);

			if (digitSum == n)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
