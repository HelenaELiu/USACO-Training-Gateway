
/*
ID: helena.6
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.StringTokenizer;

public class inflate {
	static int m, n;
	static int[] points, minutes;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		points = new int[n];
		minutes = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st2 = new StringTokenizer(f.readLine());	
			points[i] = Integer.parseInt(st2.nextToken());
			minutes[i] = Integer.parseInt(st2.nextToken());
		}
		
		long [] maxPoints = new long[m + 1];
		findPoints(maxPoints);
        
        out.println(maxPoints[m]);

		out.close();
	}
	
	private static void findPoints(long[] maxPoints) {
		for(int i = 0; i < n; i++) {
            for(int j = minutes[i]; j <= m; j++) {
            	if (maxPoints[j] < maxPoints[j - minutes[i]] + points[i])
            		maxPoints[j] = maxPoints[j - minutes[i]] + points[i];
            }
        }
	}
}