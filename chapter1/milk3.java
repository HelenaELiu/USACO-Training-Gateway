
/*
ID: helena.6
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.StringTokenizer;

public class milk3 {
	static int max = 20;
	static int[][][] visited = new int[max + 1][max + 1][max + 1];
	static int[] aIsEmpty = new int[max + 1];
	static int[] capacity = new int[3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		for (int i = 0; i < 3; i++) {
			capacity[i] = Integer.parseInt(st.nextToken());
		}

		search(new State(0, 0, capacity[2]));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= max; i++) {
			if (aIsEmpty[i] == 1)
				sb.append(i + " ");
		}

		out.println(sb.toString().trim());
		out.close();
	}
	
	private static void search(State s) {
		int i, j;

		if (visited[s.getAmount(0)][s.getAmount(1)][s.getAmount(2)] != 1) {

			visited[s.getAmount(0)][s.getAmount(1)][s.getAmount(2)] = 1;

			if (s.getAmount(0) == 0)
				aIsEmpty[s.getAmount(2)] = 1;

			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					if (i != j) {
						search(pour(s, i, j));
					}
				}
			}
		} 
	}
	
	private static State pour(State s, int from, int to) {
		State newS = new State(s.getAmount(0), s.getAmount(1), s.getAmount(2));
		int amount = s.getAmount(from);

		if (s.getAmount(to) + amount > capacity[to])
			amount = capacity[to] - s.getAmount(to);

		newS.setAmount(from, s.getAmount(from) - amount);
		newS.setAmount(to, s.getAmount(to) + amount);
		return newS;
	}
}

class State {
	private int[] abc = new int[3];

	public State() {
		abc[0] = 0;
		abc[1] = 0;
		abc[2] = 0;
	}

	public State(int x, int y, int z) {
		abc[0] = x;
		abc[1] = y;
		abc[2] = z;
	}

	public int getAmount(int n) {
		return abc[n];
	}

	public void setAmount(int n, int x) {
		abc[n] = x;
	}
}