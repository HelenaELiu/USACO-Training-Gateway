
/*
ID: helena.6
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.StringTokenizer;

public class combo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

		int n = Integer.parseInt(f.readLine());
		if (n > 5) {
			int[] fCombo = new int[3]; // farmer combo
			int[] mCombo = new int[3]; // master combo

			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int i = 0; i < 3; i++) {
				fCombo[i] = Integer.parseInt(st.nextToken());
			}

			StringTokenizer st2 = new StringTokenizer(f.readLine());
			for (int i = 0; i < 3; i++) {
				mCombo[i] = Integer.parseInt(st2.nextToken());
			}

			int overlap = findOverlaps(fCombo, mCombo, n);

			out.println(250 - overlap); // 2 * 5^3 is max. now we find overlap and subtract
		} else {
			out.println(n * n * n);
		}
		out.close();
	}

	private static int findOverlaps(int[] fCombo, int[] mCombo, int n) {
		int[] overlap = new int[3];
		int[] fPossible = new int[5]; // all possible values for one number in f combo
		int[] mPossible = new int[5]; // all possible values for one number in m combo
		for (int i = 0; i < 3; i++) {
			fPossible = fill(fPossible, fCombo, n, i);
			mPossible = fill(mPossible, mCombo, n, i);
			overlap[i] = findOverlap(fPossible, mPossible);
		}

		return overlap[0] * overlap[1] * overlap[2];
	}

	private static int[] fill(int[] arr, int[] combo, int n, int i) {
		for (int k = 0; k < 5; k++) {
			arr[k] = (combo[i] + n + k - 2) % n; // k goes from +0 to +4. want -2 to +2 so -2 is added
			if (arr[k] == 0)
				arr[k] = n;
		}

		return arr;
	}

	private static int findOverlap(int[] fPossible, int[] mPossible) {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				if (fPossible[i] == mPossible[k])
					count++;
			}
		}

		return count;
	}
}