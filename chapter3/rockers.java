
/*
ID: helena.6
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.StringTokenizer;

public class rockers {
	static int n, t, m, maxNum;
	static int[] songLength;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		songLength = new int[n];

		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++)
			songLength[i] = Integer.parseInt(st.nextToken());

		fillDisks(0, 0, t, 0);
		out.println(maxNum);
		out.close();
	}

	private static void fillDisks(int song, int disk, int timeLeft, int numSelected) {
		if (song == n)
			maxNum = Math.max(maxNum, numSelected);
		else {
			if (timeLeft >= songLength[song]) //choose this song
				fillDisks(song + 1, disk, timeLeft - songLength[song], numSelected + 1); //room left on disk
			else {
				if (disk < m - 1 && t >= songLength[song])
					fillDisks(song + 1, disk + 1, t - songLength[song], numSelected + 1); //no room left on disk
			}
			
			fillDisks(song + 1, disk, timeLeft, numSelected); //do not choose this song (skip it)
		}
	}
}
