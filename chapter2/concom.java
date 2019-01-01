
/*
ID: helena.6
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.*;

public class concom {
	static int n;
	static int[][] own;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

		n = Integer.parseInt(f.readLine());
		own = new int[101][101]; // one based
		for (int k = 0; k < n; k++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			own[i][j] = p;
		}

		visited = new boolean[101][101]; // one based
		
		findOwnership();
		
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if (i != j && own[i][j] > 50) {
					StringBuilder sb = new StringBuilder();
					sb.append(i).append(" ").append(j);
					out.println(sb.toString());
				}
			}
		}

		out.close();
	}

	private static void findOwnership() {
		boolean valid = true;

		while (valid) {
			valid = false;
			for (int i = 1; i < 101; i++) {
				for (int j = 1; j < 101; j++) {
					if (!visited[i][j] && i != j && own[i][j] > 50) {
						visited[i][j] = true;
						valid = true;
						for (int k = 1; k < 101; k++) {
							if (i != k && j != k)
								own[i][k] += own[j][k];
							if (own[i][k] > 100)
								own[i][k] = 100;
						}
					}
				}
			}
		}
	}
}
