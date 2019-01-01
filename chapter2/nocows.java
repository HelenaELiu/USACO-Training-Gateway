
/*
ID: helena.6
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.StringTokenizer;

public class nocows {
	static int numNodes, height;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numNodes = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		if (numNodes % 2 == 1) {
			long[][] pedigrees = new long[numNodes + 1][height + 1];
			long total = generatePedigrees(pedigrees);
			out.println(total);
		} else { //root will always makes it impossible to have even n
			out.println("0");
		}
		
		out.close();
	}

	private static long generatePedigrees(long[][] pedigrees) {
		for (int k = 1; k <= height; k++) {
			pedigrees[1][k] = 1;
			for (int n = 2; n <= numNodes; n++) {
				for (int j = 1; j <= n - 2; j++) {
					pedigrees[n][k] += pedigrees[j][k - 1] * pedigrees[n - j - 1][k - 1]; // f(n, k) = sum(f(j, k - 1) * f(n - j - 1, k - 1)																		
					pedigrees[n][k] %= 9901;
				}
			}
		}
		
		//subtract because pedigrees finds sum of all possibilities of levels up to height
		return ((pedigrees[numNodes][height] - pedigrees[numNodes][height - 1] + 9901) % 9901); 
	}
}
