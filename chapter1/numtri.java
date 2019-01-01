
/*
ID: helena.6
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class numtri {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

		int r = Integer.parseInt(f.readLine());
		int[][] triangle = new int[r][r];
		for (int i = 0; i < r; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int k = 0; k <= i; k++) {
				triangle[i][k] =  Integer.parseInt(st.nextToken());
			}
		}
		
		int largestSum = traverse(triangle, r);
		
		out.println(largestSum);
		out.close();
	}

	private static int traverse(int[][] triangle, int r) {
		for(int i = r - 2; i >= 0; i--) { //r-1 is leaves, so r-2 is one level above leaves
			for (int k = 0; k <= i; k++) {
				if (triangle[i+1][k] > triangle[i+1][k+1])
					triangle[i][k] = triangle[i][k] + triangle[i+1][k];
				else
					triangle[i][k] = triangle[i][k] + triangle[i+1][k+1];
			}
		}
		
		return triangle[0][0];
	}
}