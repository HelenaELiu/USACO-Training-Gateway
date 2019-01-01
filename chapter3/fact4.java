
/*
ID: helena.6
LANG: JAVA
TASK: fact4
*/

import java.io.*;

public class fact4 {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		n = Integer.parseInt(f.readLine());

		long digit = factorial();

		out.println(digit);
		out.close();
	}

	private static long factorial() {
		long total = 1;
		for (int i = 2; i <= n; i++) {
			total = total * i;
			while (total % 10 == 0) {
				total /= 10;
			}
			total = total % 10000000000L;
		}
		
		return total % 10;
	}
}
