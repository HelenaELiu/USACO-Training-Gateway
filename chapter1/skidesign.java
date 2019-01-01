
/*
ID: helena.6
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.Arrays;

public class skidesign {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

		int n = Integer.parseInt(f.readLine());
		int[] elevation = new int[n];
		for (int i = 0; i < n; i++) {
			elevation[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(elevation);
		
		int min = elevation[0];
		int max = elevation[n - 1];
		if (max-min > 17) {
			int minCost = Integer.MAX_VALUE;
			int cost = 0;
			
			for (int i = min; i <= max - 17; i++) { //i represents the smallest number
				for (int k = 0; k < n; k++) {
					if (elevation[k] < i) {
						cost = cost + (i-elevation[k])*(i-elevation[k]);
					}
					if (elevation[k] > i + 17) {
						cost = cost + (elevation[k] - i - 17)*(elevation[k] - i - 17); // distribute (elevation[k] - (i + 17))
					}
				}
				if (cost < minCost)
					minCost = cost;
				cost = 0;
			}
			
			out.println(minCost);
		} else {
			out.println(0);
		}

		out.close();
	}
}