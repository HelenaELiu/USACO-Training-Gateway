
/*
ID: helena.6
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
	static int n;
	static int[][] grid;
	static int inf = 100001;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("agrinet.in"));
		PrintWriter out = new PrintWriter(new File("agrinet.out"));
		n = sc.nextInt();
		grid = new int[n][n];

		for (int i = 0; i < n; i++) { 
			for (int j = 0; j < n; j++)
				grid[i][j] = sc.nextInt();
		}
		
		int minLength = mst();
		out.println(minLength);
		
		out.close();
	}

	private static int mst() {
		int[] dist = new int[n];
		Arrays.fill(dist, inf);
		boolean[] intree = new boolean[n];
		intree[0] = true;
		int[] source = new int[n];
		Arrays.fill(source, -1);
		
		int currNode = 0;
		int numTrue = 1;
		int totaldist = 0;
		
		while (numTrue < n) {
			for (int i = 0; i < n; i++) { //iterate through all nodes currNode is connected to
				if(i != currNode && grid[currNode][i] < dist[i])
					dist[i] = grid[currNode][i];
			}
			int minNode = findMinNode(dist, intree);
			intree[minNode] = true;
			totaldist += dist[minNode];
			currNode = minNode;
			numTrue++;
		}
		
		return totaldist;
	}

	private static int findMinNode(int[] dist, boolean[] intree) {
		int pos = -1;
		int min = inf;

		for (int i = 0; i < n; i++) {
			if (!intree[i] && dist[i] < min) {
				min = dist[i];
				pos = i;
			}
		}

		return pos;
	}
}
