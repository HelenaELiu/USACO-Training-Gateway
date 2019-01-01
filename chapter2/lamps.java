
/*
ID: helena.6
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
	static int numLamps;
	static boolean[][][][][] configs;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

		numLamps = Integer.parseInt(f.readLine());
		int counter = Integer.parseInt(f.readLine());
		List<Integer> mustbeOn = new ArrayList<>();
		List<Integer> mustbeOff = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(f.readLine());
		int g = 0;
		while ((g = Integer.parseInt(st.nextToken())) != -1) {
			mustbeOn.add(g - 1);
		}
		StringTokenizer st2 = new StringTokenizer(f.readLine());
		while ((g = Integer.parseInt(st2.nextToken())) != -1) {
			mustbeOff.add(g - 1);
		}

		boolean[] state = new boolean[numLamps];
		Arrays.fill(state, true);

		configs = new boolean[2][2][2][2][numLamps];
		configs[0][0][0][0] = state;

		configs[0][0][0][1] = button1(state);
		configs[0][0][1][0] = button2(state);
		configs[0][1][0][0] = button3(state);
		configs[1][0][0][0] = button4(state);

		configs[0][0][1][1] = button1(button2(state));
		configs[0][1][0][1] = button1(button3(state));
		configs[1][0][0][1] = button1(button4(state));
		configs[0][1][1][0] = button2(button3(state));
		configs[1][0][1][0] = button2(button4(state));
		configs[1][1][0][0] = button3(button4(state));

		configs[1][1][1][0] = button2(button3(button4(state)));
		configs[1][1][0][1] = button1(button3(button4(state)));
		configs[1][0][1][1] = button1(button1(button4(state)));
		configs[0][1][1][1] = button1(button2(button3(state)));

		configs[1][1][1][1] = button1(button2(button3(button4(state))));

		List<FinalState> list = new ArrayList<FinalState>();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					for (int m = 0; m < 2; m++) {
						if (counter - i - j - k - m >= 0 && (counter - i - j - k - m) % 2 == 0) {
							boolean valid = true;
							for (int off : mustbeOff) {
								if (configs[i][j][k][m][off])
									valid = false;
							}
							for (int on : mustbeOn) {
								if (!configs[i][j][k][m][on])
									valid = false;
							}
							if (valid && !list.contains(new FinalState(i, j, k, m))) {
								list.add(new FinalState(i, j, k, m));
							}
						}
					}
				}
			}
		}
		
		Collections.sort(list);
		
		if (list.size() == 0)
			out.println("IMPOSSIBLE");
		else {
			for (FinalState s : list) {
				out.println(s.toString());
			}
		}

		out.close();
	}

	private static class FinalState implements Comparable<FinalState> {
		int i, j, k, m;

		public FinalState(int i, int j, int k, int m) {
			this.i = i;
			this.j = j;
			this.k = k;
			this.m = m;
		}

		public int compareTo(FinalState s) {
			for (int n = 0; n < numLamps; n++) {
				if (configs[i][j][k][m][n] && !configs[s.i][s.j][s.k][s.m][n])
					return 1;
				if (!configs[i][j][k][m][n] && configs[s.i][s.j][s.k][s.m][n])
					return -1;
			}

			return 0;
		}

		public String toString() {
			String output = "";
			for (int n = 0; n < numLamps; n++) {
				if (configs[i][j][k][m][n])
					output = output + "1";
				else
					output = output + "0";
			}

			return output;
		}

		@Override
		public boolean equals(Object s) {
			if (s == null)
				return false;
			if (!(s instanceof FinalState))
				return false;

			for (int n = 0; n < numLamps; n++) {
				if (configs[i][j][k][m][n] != configs[((FinalState) s).i][((FinalState) s).j][((FinalState) s).k][((FinalState) s).m][n])
					return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			return i * 1000 + j * 100 + k * 10 + m;
		}
	}

	public static boolean[] button1(boolean[] state) { // all
		boolean[] copy = state.clone();
		for (int i = 0; i < numLamps; i++) {
			copy[i] = !state[i];
		}

		return copy;
	}

	public static boolean[] button2(boolean[] state) { // odds (zero based)
		boolean[] copy = state.clone();
		for (int i = 0; i < numLamps; i += 2) {
			copy[i] = !state[i];
		}

		return copy;
	}

	public static boolean[] button3(boolean[] state) { // evens
		boolean[] copy = state.clone();
		for (int i = 1; i < numLamps; i += 2) {
			copy[i] = !state[i];
		}

		return copy;
	}

	public static boolean[] button4(boolean[] state) { // 3k+1
		boolean[] copy = state.clone();
		for (int i = 0; i < numLamps; i += 3) {
			copy[i] = !state[i];
		}

		return copy;
	}
}
