
/*
ID: helena.6
LANG: JAVA
TASK: preface
*/

import java.io.*;
import java.util.*;

public class preface {
	static Map<Integer, String> map;
	static Map<Integer, String> subtract;
	static int[] values = { 1, 5, 10, 50, 100, 500, 1000 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int n = Integer.parseInt(f.readLine());
		
		map = new HashMap<Integer, String>();
		map.put(1, "I");
		map.put(5, "V");
		map.put(10, "X");
		map.put(50, "L");
		map.put(100, "C");
		map.put(500, "D");
		map.put(1000, "M");
		
		subtract = new HashMap<Integer, String>();
		subtract.put(4, "IV");
		subtract.put(9, "IX");
		subtract.put(40, "XL");
		subtract.put(90, "XC");
		subtract.put(400, "CD");
		subtract.put(900, "CM");

		int[] countLetters = new int[n]; // 0 is I, 1 is V, etc.

		for (int i = 1; i <= n; i++) {
			String str = convert(i);
			countString(countLetters, str);
		}

		for (int i = 0; i < n; i++) {
			if (countLetters[i] != 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(map.get(values[i])).append(" ").append(countLetters[i]);
				out.println(sb.toString());
			}
		}

		out.close();
	}

	private static void countString(int[] countLetters, String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == 'I')
				countLetters[0]++;
			else if (c == 'V')
				countLetters[1]++;
			else if (c == 'X')
				countLetters[2]++;
			else if (c == 'L')
				countLetters[3]++;
			else if (c == 'C')
				countLetters[4]++;
			else if (c == 'D')
				countLetters[5]++;
			else if (c == 'M')
				countLetters[6]++;
		}
	}

	private static String convert(int i) { // does not handle fours or nines
		StringBuilder sb = new StringBuilder();
		boolean firstDigit4 = false;
		boolean firstDigit9 = false;

		while (i > 0) {
			firstDigit4 = has4(i);
			firstDigit9 = has9(i);
			if (firstDigit4) {
				int toSubtract = 4 * (int) Math.pow(10, Integer.toString(i).length() - 1);
				sb.append(subtract.get(toSubtract));
				i -= toSubtract;
			} else if (firstDigit9) {
				int toSubtract = 9 * (int) Math.pow(10, Integer.toString(i).length() - 1);
				sb.append(subtract.get(toSubtract));
				i -= toSubtract;
			} else {
				for (int j = 6; j >= 0; j--) {
					if (i >= values[j]) {
						sb.append(map.get(values[j]));
						i -= values[j];
						break;
					}
				}
			}
		}

		return sb.toString();
	}

	private static boolean has4(int i) {
		String str = Integer.toString(i);
		return (str.charAt(0) == '4');
	}

	private static boolean has9(int i) {
		String str = Integer.toString(i);
		return (str.charAt(0) == '9');
	}
}