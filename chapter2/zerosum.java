
/*
ID: helena.6
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

		n = Integer.parseInt(f.readLine());
		char[] operations = new char[n];
		List<String> sequences = new ArrayList<String>(); // contains all the successful sequences
		findSequences(sequences, operations, -1, 0); //-1 because it doesn't execute anything yet

		Collections.sort(sequences);
		for (String s : sequences) {
			out.println(s);
		}
		out.close();
	}

	private static void findSequences(List<String> sequences, char[] operations, int oper, int index) {
		char[] arrCopy = Arrays.copyOf(operations, operations.length);
		if (oper == 0) // if oper == 0 it is +, 1 is -, 2 is ' '
			arrCopy[index] = '+';
		else if (oper == 1)
			arrCopy[index] = '-';
		else if (oper == 2)
			arrCopy[index] = ' ';
		if (index == n - 1) { // reached the end of sequence
			String sequence = operToString(arrCopy);
			if (!sequences.contains(sequence) && findSum(sequence) == 0)
				sequences.add(sequence);
		} else {
			findSequences(sequences, arrCopy, 0, index + 1);
			findSequences(sequences, arrCopy, 1, index + 1);
			findSequences(sequences, arrCopy, 2, index + 1);
		}
	}

	private static int findSum(String sequence) {
		int sum = 0;
		sequence = sequence.replaceAll(" ", "");
		int index = 0;
		int length = sequence.length();
		char oper = '.';
		while (index < length) {// finds first number. exception if all are spaces?
			if (sequence.charAt(index) == '+' || sequence.charAt(index) == '-') {
				oper = sequence.charAt(index);
				index++;
				break;
			} else {
				sum = sum * 10 + ((int) sequence.charAt(index) - 48);
				index++;
			}
		}

		int t = 0;
		while (index < length) { // everything in between first and last number
			if (sequence.charAt(index) == '+' || sequence.charAt(index) == '-') {
				if (oper == '+')
					sum = sum + t;
				else if (oper == '-')
					sum = sum - t;
				t = 0;
				oper = sequence.charAt(index);
			} else {
				t = t * 10 + ((int) sequence.charAt(index) - 48);
			}
			index++;
		}
		if (oper == '+') // last number
			sum = sum + t;
		else if (oper == '-')
			sum = sum - t;

		return sum;
	}

	private static String operToString(char[] operations) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; i++) {
			sb.append(i).append(operations[i]);
		}
		sb.append(n);

		return sb.toString();
	}
}
