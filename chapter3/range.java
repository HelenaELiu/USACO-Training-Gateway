
/*
ID: helena.6
LANG: JAVA
TASK: range
*/

import java.io.*;

public class range {
	static int n;
	static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));

		n = Integer.parseInt(f.readLine());
		grid = new int[n][n];

		int countOnes = 0;
		for (int i = 0; i < n; i++) {
			String s = f.readLine();
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(s.substring(j, j + 1));
				if (grid[i][j] == 1) {
					countOnes++;
				}
			}
		}

		if (countOnes == n * n) {
			for (int i = 2; i <= n; i++) {
				int count = n*n;
				int borders = 2*n*(i-1);
				count = count - borders;
				int overlap = (i-1)*(i-1);
				count = count + overlap;
				
				out.println(i + " " + count);
			}
		} else {
			int[] counts = new int[n + 1];

			for (int i = 2; i <= n; i++) { // side length of square to check for
				for (int j = 0; j < n; j++) { // column
					for (int k = 0; k < n; k++) { // row
						if (grid[j][k] == i - 1) {
							if (check(i, j, k)) {
								grid[j][k] = i;
								counts[i]++;
							}
						}
					}
				}
				if (counts[i] == 0)
					break;
			}

			for (int i = 2; i <= n; i++) {
				if (counts[i] > 0)
					out.println(i + " " + counts[i]);
			}
		}
		out.close();
	}

	private static boolean check(int size, int column, int row) {
		if (row + size > n || column + size > n)
			return false;

		for (int i = column; i < column + size; i++) {
			for (int j = row; j < row + size; j++) {
				if (grid[i][j] == 0)
					return false;
			}
		}

		return true;
	}
}
