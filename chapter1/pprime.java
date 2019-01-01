
/*
ID: helena.6
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pprime {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		ArrayList<Integer> palindromes = new ArrayList<>(makePalindromes(a, b));

		for (int p : palindromes) {
			if (isPrime(p))
				out.println(p);
		}

		out.close();
	}

	private static ArrayList<Integer> makePalindromes(int a, int b) {
		ArrayList<Integer> palindromes = new ArrayList<>();
		int alength = Integer.toString(a).length();
		int blength = Integer.toString(b).length();

		if (a == 5)
			palindromes.add(5);
		if (a <= 7 && b >= 7)
			palindromes.add(7);

		if (blength >= 2 && alength <= 2) {
			for (int i = 1; i <= 9; i++) {
				int palindrome = i * 11;
				if (palindrome >= a && palindrome <= b)
					palindromes.add(palindrome);
			}
		}

		if (blength >= 3 && alength <= 3) {
			for (int d1 = 1; d1 <= 9; d1 += 2) { // evens arent prime (except 2 of course)
				for (int d2 = 0; d2 <= 9; d2++) {
					int palindrome = 100 * d1 + 10 * d2 + d1;
					if (palindrome >= a && palindrome <= b)
						palindromes.add(palindrome);
				}
			}
		}

		if (blength >= 4 && alength <= 4) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					int palindrome = 1000 * d1 + 100 * d2 + 10 * d2 + d1;
					if (palindrome >= a && palindrome <= b)
						palindromes.add(palindrome);
				}
			}
		}

		if (blength >= 5 && alength <= 5) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					for (int d3 = 0; d3 <= 9; d3++) {
						int palindrome = 10000 * d1 + 1000 * d2 + 100 * d3 + 10 * d2 + d1;
						if (palindrome >= a && palindrome <= b)
							palindromes.add(palindrome);
					}
				}
			}
		}

		if (blength >= 6 && alength <= 6) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					for (int d3 = 0; d3 <= 9; d3++) {
						int palindrome = 100000 * d1 + 10000 * d2 + 1000 * d3 + 100 * d3 + 10 * d2 + d1;
						if (palindrome >= a && palindrome <= b)
							palindromes.add(palindrome);
					}
				}
			}
		}

		if (blength >= 7 && alength <= 7) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					for (int d3 = 0; d3 <= 9; d3++) {
						for (int d4 = 0; d4 <= 9; d4++) {
							int palindrome = 1000000 * d1 + 100000 * d2 + 10000 * d3 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
							if (palindrome >= a && palindrome <= b)
								palindromes.add(palindrome);
						}
					}
				}
			}
		}

		if (blength >= 8 && alength <= 8) {
			for (int d1 = 1; d1 <= 9; d1 += 2) {
				for (int d2 = 0; d2 <= 9; d2++) {
					for (int d3 = 0; d3 <= 9; d3++) {
						for (int d4 = 0; d4 <= 9; d4++) {
							int palindrome = 10000000 * d1 + 1000000 * d2 + 100000 * d3 + 10000 * d4 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
							if (palindrome >= a && palindrome <= b)
								palindromes.add(palindrome);
						}
					}
				}
			}
		}

		return palindromes;
	}

	private static boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;

		double sqrt = Math.sqrt(n);
		for (int i = 3; i <= sqrt; i = i + 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}
}