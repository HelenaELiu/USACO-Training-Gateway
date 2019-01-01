
/*
ID: helena.6
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

		int n = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] digits = new int[n];

		for (int i = 0; i < n; i++) {
			digits[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0;

		for (int k = 0; k < n; k++) {
			for (int j = 0; j < n; j++) {
				for (int h = 0; h < n; h++) {
					int threeDigit = threeDigits(k, j, h, digits);
					for (int x = 0; x < n; x++) {
						for (int y = 0; y < n; y++) {
							int pp1 = threeDigit * digits[y];
							int pp2 = threeDigit * digits[x];
							if ((pp1 >= 100 && pp1 <= 999 && pp2 >= 100 && pp2 <= 999)) {
								int total = pp1 + pp2*10;
								 if (checkDigits(pp1, digits) && checkDigits(pp2, digits) && checkDigits(total, digits)) {
									 count++;
								 }
							}
						}
					}
				}
			}
		}

		out.println(count);
		out.close();
	}

	private static int threeDigits(int k, int j, int h, int[] digits) {
		return digits[k] * 100 + digits[j] * 10 + digits[h];
	}

	private static boolean checkDigits(int x, int[] digits) {
		int count = 0;
		String str = String.valueOf(x);
		int length = str.length();
		for (int i = 0; i < length; i++) {
			if (Arrays.toString(digits).contains(str.substring(i, i + 1))) {
				count++;
			}
		}
		
	    return (length == count);
	}
}