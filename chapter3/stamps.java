
/*
ID: helena.6
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
	static int k, n, maxVal;
	static int[] values;
	static int inf = 100000000;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		values = new int[n];
		
		int k = 0;
		while (f.ready()) {
			String[] parts = f.readLine().split(" ");
			for (int i = 0; i < parts.length; i++) {
				values[k] = Integer.parseInt(parts[i]);
				k++;
			}
		}
        
		Arrays.sort(values);
		maxVal = values[n-1];
		
        int m = dp();
        out.println(m);
		
		out.close();
	}
	
	private static int dp() {
		int [] dp = new int[maxVal * k + 2];
        Arrays.fill(dp, inf);
        dp[0] = 1;
        
        for(int i = 1; i <= maxVal * k + 1; i++) {
            for(int j = 0; j < n; j++) {
                if(values[j] <= i && dp[i - values[j]] > 0) {
                	if (dp[i] > dp[i - values[j]] + 1)
                        dp[i] = dp[i - values[j]] + 1;
                }
            }
            
            if(dp[i] > k + 1) {
                return i - 1;
            }
        }
        
        return -1; //unreachable
	}
}
