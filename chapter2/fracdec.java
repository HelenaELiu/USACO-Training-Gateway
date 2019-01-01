
/*
ID: helena.6
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {
	static int n, d;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		if (n % d == 0) {
			out.println((double) n / d);
		} else {
			ArrayList<Integer> digits = new ArrayList<Integer>(); 
			ArrayList<Integer> numerators = new ArrayList<Integer>();
			int div = n / d; // part before decimal

			findDecimal(digits, numerators);
			
			String s = div + "." + getSb(digits, numerators).toString();
			int index = 0;
			while (76 + index < s.length()) {
				out.println(s.substring(index, index + 76));
				index += 76;
			}

			if (index - s.length() != 0)
				out.println(s.substring(index));
		}

		out.close();
	}

	private static void findDecimal(ArrayList<Integer> digits, ArrayList<Integer> numerators) {
		boolean[] visited = new boolean[100010];
		n = n % d; // numerator for part after decimal

		while (n > 0 && !visited[n]) {
			visited[n] = true;
			numerators.add(n);
			n *= 10;
			digits.add(n / d);
			n = n % d;
		}
	}

	private static StringBuilder getSb(ArrayList<Integer> digits, ArrayList<Integer> numerators) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < digits.size(); i++) {
			if (n > 0 && numerators.get(i) == n)
				sb.append("(");
			sb.append(digits.get(i));
		}

		if (n > 0)
			sb.append(")");
		
		return sb;
	}
}
