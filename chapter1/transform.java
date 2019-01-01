
/*
ID: helena.6
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.Arrays;

public class transform {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

		int n = Integer.parseInt(f.readLine());
		char[][] before = new char[n][n];
		char[][] after = new char[n][n];

		for (int i = 0; i < n; i++) {
			before[i] = f.readLine().toCharArray();
		}
		for (int i = 0; i < n; i++) {
			after[i] = f.readLine().toCharArray();
		}

		int t = 7; // t for transform!!
		char[][] transformed = before;

		for (int i = 1; i < 7; i++) {
			if (i == 1)
				transformed = rotate90(transformed, n);
			else if (i == 2) {
				transformed = rotate90(transformed, n);
				transformed = rotate90(transformed, n);
			} else if (i == 3) {
				transformed = rotate90(transformed, n);
				transformed = rotate90(transformed, n);
				transformed = rotate90(transformed, n);
			} else if (i == 4)
				transformed = reflect(before, n);
			else if (i == 5) {
				transformed = reflect(before, n);
				transformed = rotate90(transformed, n);
				if (!Arrays.deepEquals(transformed, after))
					transformed = rotate90(transformed, n);
				if (!Arrays.deepEquals(transformed, after))
					transformed = rotate90(transformed, n);
			}

			if (Arrays.deepEquals(transformed, after)) {
				t = i;
				break;
			}
			else {
				transformed = before;
			}
		}

		out.println(t);
		out.close();
	}

	private static char[][] rotate90(char[][] c1, int n) {
		char[][] c2 = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				c2[k][n - 1 - i] = c1[i][k];
			}
		}
		
		return c2;
	}

	private static char[][] reflect(char[][] c1, int n) {
		char[][] c2 = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				c2[i][n - 1 - k] = c1[i][k];
			}
		}
		
		return c2;
	}
}
