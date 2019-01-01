
/*
ID: helena.6
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class humble {
	static int k, n;
	static int[] s;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		s = new int[k];

		StringTokenizer st2 = new StringTokenizer(f.readLine());
		for (int i = 0; i < k; i++)
			s[i] = Integer.parseInt(st2.nextToken());

		Arrays.sort(s);

		long humnum = findHumnum();

		out.println(humnum);
		out.close();
	}

	private static long findHumnum() {
		long[] humnums = new long[n + 1];
		humnums[0] = 1; //make 1 the first humnum for convenience when multiplying
		int[] next = new int[k];

		for (int i = 1; i <= n; i++) {
			long best = Integer.MAX_VALUE;
			for (int j = 0; j < k; j++) {
				while (s[j] * humnums[next[j]] <= humnums[i - 1])
					next[j]++;
				if (s[j] * humnums[next[j]] < best) {
					best = s[j] * humnums[next[j]];
				}
			}
			humnums[i] = best;
		}
		
		return humnums[n];
	}
}
