
/*
ID: helena.6
LANG: JAVA
TASK: ratios
*/

import java.io.*;
import java.util.StringTokenizer;

public class ratios {
	static int[] goal;
	static int[][] mix;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		goal = new int[3];
		mix = new int[3][3];
		for (int i = 0; i < 3; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < 3; j++) {
				mix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] ratios = new int[3];
		int ratio = getRatio(ratios);
		
		if (ratio == -1)
			out.println("NONE");
		else
			out.println(ratios[0] + " " + ratios[1] + " " + ratios[2] + " " + ratio);

		out.close();
	}

	private static int getRatio(int[] ratios) {
		int sum = Integer.MAX_VALUE;
		int ratio = -1;
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				for (int k = 0; k < 101; k++) {
					int[] total = getTotal(i, j, k);
					int r = correctRatio(total); //see if totals simplify to goal
					if (r != -1) {
						if (total[0] + total[1] + total[2] < sum) {
							sum = total[0] + total[1] + total[2];
							ratios[0] = i;
							ratios[1] = j;
							ratios[2] = k;
							ratio = r;
						}
					}
				}
			}
		}
		
		return ratio;
	}

	private static int[] getTotal(int i, int j, int k) {
		int[] total = new int[3];
		total[0] = i * mix[0][0] + j * mix[1][0] + k*mix[2][0];
		total[1] = i * mix[0][1] + j * mix[1][1] + k*mix[2][1];
		total[2] = i * mix[0][2] + j * mix[1][2] + k*mix[2][2];
		
		return total;
	}

	private static int correctRatio(int[] total) {
		if((goal[0] > 0 || goal[1] > 0 || goal[2] > 0) && (total[0] == 0 && total[1] == 0 && total[2] == 0))
			return -1;
		
		for (int i = 0; i < 3; i++) {
			if (total[i] < goal[i])
				return -1;
		}
		
		double[] ratios = new double[3];
		for (int i = 0; i < 3; i++) {
			ratios[i] = (double) goal[i] / (double) (goal[0] + goal[1] + goal[2]);
		}
		
		double[] ratios2 = new double[3];
		for (int i = 0; i < 3; i++) {
			ratios2[i] = (double) total[i] / (double) (total[0] + total[1] + total[2]);
		}
		
		for (int i = 0; i < 3; i++) {
			if (ratios[i] != ratios2[i])
				return -1;
		}
		
		for (int i = 0; i < 3; i++) {
			if (goal[i] != 0)
				return total[i]/goal[i];
		}
		
		return -1;
	}
}
