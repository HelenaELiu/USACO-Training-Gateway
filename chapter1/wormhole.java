
/*
ID: helena.6
LANG: JAVA
TASK: wormhole
*/

import java.io.*;
import java.util.StringTokenizer;

public class wormhole {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in")); //CHANGE TO WORMHOLE
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

		int n = Integer.parseInt(f.readLine());
		int[] x = new int[n+1];
		int[] y = new int[n+1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] right = new int[n+1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[j] > x[i] && y[i] == y[j]) {// j right of i...
					if (right[i] == 0 || x[j] - x[i] < x[right[i]] - x[i])
						right[i] = j;
				}
			}
		}

		int[] pairs = new int[n+1]; // if 1 is paired with 4, pairs[1] = 4
		int cycles = findCycles(x, y, pairs, n, right); // recursively finds number of cycles

		out.println(cycles);
		out.close();
	}

	private static int findCycles(int[] x, int[] y, int[] pairs, int n, int[] right) {
		int cycles = 0;
		int i = 0;
		for (i = 1; i <= n; i++) {
			if (pairs[i] == 0) // if unpaired, then find a pair
				break;
		}
		
		if (i > n) { // everyone is paired. base case
			if (cycle(x, y, pairs, n, right))
				return 1;
			else
				return 0;
		}

		for (int j = i + 1; j <= n; j++) {
			if (pairs[j] == 0) {
				pairs[i] = j;
				pairs[j] = i;
				cycles = cycles + findCycles(x, y, pairs, n, right);
				pairs[i] = pairs[j] = 0;
			}
		}

		return cycles;
	}

	
	private static boolean cycle(int[] x, int[] y, int[] pairs, int n, int[] right) {
		for (int start = 1; start <= n; start++) {
			int pos = start;
			for (int count = 0; count < n; count++) {
				pos = right[pairs[pos]];
			}
			if (pos != 0) return true;
		}
		
		return false;
	}
	
}
