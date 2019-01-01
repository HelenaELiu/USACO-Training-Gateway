
/*
ID: helena.6
LANG: JAVA
TASK: game1
*/

import java.io.*;

public class game1 {
	static int n;
	static int[] board;
	static int[][] sums, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		
		n = Integer.parseInt(f.readLine());
		board = new int[n];
		int index = 0;
		
		while(f.ready()) {
			String[] parts = f.readLine().split(" ");
			for (int i = 0; i < parts.length; i++) {
				board[index] = Integer.parseInt(parts[i]);
				index++;
			}
		}
		
		sums = new int[n][n];
		dp = new int[n][n];
		
		for (int i = 0; i < n; i++){
			sums[i][i] = board[i];
			dp[i][i] = board[i];
		}
		
		for (int i = 0; i < n; i++){
			for (int j = i + 1; j < n; j++){
				sums[i][j] = sums[i][j-1] + board[j];
				sums[j][i] = sums[i][j];
			}
		}
		
		dp(0, n-1);
		StringBuilder sb = new StringBuilder();
		sb.append(dp[0][n-1]).append(" ").append(sums[0][n-1] - dp[0][n-1]);
		
		out.println(sb.toString());
		out.close();
	}

	public static int dp(int i, int j){
		if (i == j) 
			return board[i];
		if (dp[i][j] == 0)
			dp[i][j] = sums[i][j] - Math.min(dp(i+1, j), dp(i, j-1));
		return dp[i][j];
	}
}