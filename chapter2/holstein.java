
/*
ID: helena.6
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

public class holstein {
	static int v;
	static int[] required;
	static int g;
	static int[][] amtVitamin;
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

		v = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		required = new int[v];
		for (int i = 0; i < v; i++) {
			required[i] = Integer.parseInt(st.nextToken());
		}

		g = Integer.parseInt(f.readLine());
		amtVitamin = new int[g][v];
		for (int i = 0; i < g; i++) {
			StringTokenizer st2 = new StringTokenizer(f.readLine());
			for (int j = 0; j < v; j++) {
				amtVitamin[i][j] = Integer.parseInt(st2.nextToken());
			}
		}

		int[] totalVitamin = new int[v];

		findScoops("", totalVitamin, true, 0);
		findScoops("", totalVitamin, false, 0);
		out.println(countString(str) + str);
		out.close();
	}

	private static void findScoops(String used, int[] totalVitamin, boolean toUse, int numFeed) {
		boolean t = true;
		if (!check(totalVitamin)) {
			t = false;
		}
		if (t || numFeed == g) {
            if (t && countString(used) < countString(str) && countString(used) != 0) 
            	str = used;
            return;
        }
		String strCopy = "" + used;
		int[] arrCopy = Arrays.copyOf(totalVitamin, totalVitamin.length);
		if (toUse) {
			strCopy = strCopy + " " + Integer.toString(numFeed + 1);
			for (int i = 0; i < arrCopy.length; i++) {
				arrCopy[i] = arrCopy[i] + amtVitamin[numFeed][i];
			}
		}
		
		findScoops(strCopy, arrCopy, true, numFeed + 1);
		findScoops(strCopy, arrCopy, false, numFeed + 1);
	}
	
	private static int countString(String str) {
		int x = 0;
		if (str==null || str.length() == 0)
			return Integer.MAX_VALUE;
		for (char c : str.toCharArray()) {
            if (c == ' ') {
                x++;
            }
        }
		return x;
	}

	private static boolean check(int[] totalVitamin) {
		for (int i = 0; i < v; i++) {
			if (totalVitamin[i] < required[i]) {
				return false;
			}
		}
		
		return true;
	}
}
