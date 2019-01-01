
/*
ID: helena.6
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.StringTokenizer;

public class money {
	static long[] memo;
	static int v, n;
	static int[] coins;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		v = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		coins = new int[v];
		int i = 0;
		while (f.ready() && i < v) {
			String[] temp = f.readLine().split(" ");
			for (int j = 0; j < temp.length; j++) {
				coins[i] = Integer.parseInt(temp[j]);
				i++;
			}
		}
		
		memo = new long[n + 1]; // memo[x] is the number of ways to make a total of x
		memo[0] = 1;
		
		for(int k = 0; k < v; k++) {
			for(long j = coins[k]; j <= n; j++)
				memo[(int) j] += memo[(int) (j - coins[k])];
		}

		out.println(memo[n]);
		
		out.close();
	}
}
