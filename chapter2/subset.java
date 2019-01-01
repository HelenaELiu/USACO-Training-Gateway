/*
ID: helena.6
LANG: JAVA
TASK: subset
*/

import java.io.*;
import java.util.Arrays;

public class subset {
	static long[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int n = Integer.parseInt(f.readLine());
		int totalSum = (n+1)*n/2;
		
		if (totalSum % 2 == 0) {
			long total = 0;
			memo = new long[totalSum/2+1][n+1];
			for(int i = 0; i < memo.length; i++) {
				 Arrays.fill(memo[i], -1);
			}
			
			total = calcSubsets(totalSum/2, n);
			out.println(total/2);
		} else {
			out.println(0);
		}
		
		out.close();
	}

	private static long calcSubsets(int sum, int numElements) {
		if(sum < 0 || numElements < 0)
			return 0;
		if(memo[sum][numElements] != -1)
			return memo[sum][numElements];
		if (sum == 0 && numElements == 0)
			return 1;
		memo[sum][numElements] = calcSubsets(sum, numElements - 1) + calcSubsets(sum - numElements, numElements - 1);
		
		return memo[sum][numElements];
	}
}
