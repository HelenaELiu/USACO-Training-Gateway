
/*
ID: helena.6
LANG: JAVA
TASK: runround
*/

import java.io.*;
import java.util.*;

public class runround {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

		int m = Integer.parseInt(f.readLine()) + 1;

		while (true) {
			if (check(m))
				break;
			m++;
		}

		out.println(m);
		out.close();
	}

	private static boolean check(int m) {
		int length = Integer.toString(m).length();
		if (length == 1)
			return true;
		if (!uniqueDigits(m)) // all digits must be unique
			return false;
		if (digitEqualsLength(m, length)) // digit must land on different digit
			return false;
		if (numberHasZero(m)) // no zeros in the digit
			return false;
		if (!runaround(m, length))
			return false;

		return true;
	}

	private static boolean runaround(int m, int length) { // do actual roundabout check here. mod length
		char[] charM = Integer.toString(m).toCharArray();
		ArrayList<Integer> digits = new ArrayList<>();
		int n = 0; // index in char
		int digit = 0;
		
		for (int i = 0; i < length - 1; i++) { // minus one because we already ensured it loops back to beginning
			digit = (int) charM[n % length] - 48; // ascii
			if (digits.contains(digit)) {
				return false;
			}
			digits.add(digit);
			n += digit;
		}
		
		digit = (int) charM[n % length] - 48;
		int runaroundDigit = (int) charM[(n + digit) % length] - 48;
		if (runaroundDigit == digits.get(0))
			return true;
		
		return false;
	}

	private static boolean numberHasZero(int m) {
		return String.valueOf(m).contains("0");
	}

	private static boolean digitEqualsLength(int m, int length) {
		while (m > 0) {
			int n = m % 10;
			if (n == length)
				return true;
			m = m / 10;
		}

		return false;
	}

	private static boolean uniqueDigits(int m) {
		ArrayList<Integer> digits = new ArrayList<>();
		while (m > 0) {
			int n = m % 10;
			if (digits.contains(n)) {
				return false;
			}
			digits.add(n);
			m = m / 10;
		}

		return true;
	}
}
