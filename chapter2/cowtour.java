
/*
ID: helena.6
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cowtour {
	static int n, count;
	static int[][] locs, adj;
	static boolean [] visited;
	static int[] comp;
	static double[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));

		n = Integer.parseInt(f.readLine());
		locs = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			locs[i][0] = Integer.parseInt(st.nextToken());
			locs[i][1] = Integer.parseInt(st.nextToken());
		}

		adj = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = f.readLine();
			for (int j = 0; j < n; j++) {
				adj[i][j] = (int) s.charAt(j) - 48;
			}
		}
		
		comp = new int[n];
		visited = new boolean[n];
		count = 1;
		floodfill();
		
		dist = new double[n][n];
		
		for (int i = 0; i < n; i++) 
			Arrays.fill(dist[i], Integer.MAX_VALUE);

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j)
					dist[i][j] = 0;
				else if (adj[i][j] == 1) {
					double d = Math.hypot(locs[i][0] - locs[j][0], locs[i][1] - locs[j][1]);
					dist[i][j] = d;
					dist[j][i] = d;
				}
			}
		}
		
		floydWarshall();
		
		double [] diameters = new double[count + 1];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(comp[i] == comp[j]) {
					diameters[comp[i]] = Math.max(diameters[comp[i]], dist[i][j]);
				}
			}
		}
		
		double minDiam = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(comp[i] != comp[j]) {
					double diam1 = 0;
					double diam2 = 0;
					for(int k = 0; k < n; k++) {
						if(comp[k] == comp[i]) diam1 = Math.max(diam1, dist[i][k]);
					}
					
					for(int k = 0; k < n; k++) {
						if(comp[k] == comp[j]) diam2 = Math.max(diam2, dist[j][k]);
					}
					
					double d = Math.hypot(locs[i][0] - locs[j][0], locs[i][1] - locs[j][1]);
					double diam = Math.max(diameters[comp[i]], Math.max(diameters[comp[j]], diam1 + d + diam2));
					minDiam = Math.min(minDiam, diam);
				}
			}
		}
		
		String ans = String.format("%.6f", minDiam);
		while(ans.length() - ans.indexOf(".") < 6) 
			ans += "0";
		out.println(ans);
		
		out.close();
	}

	private static void floydWarshall() {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
	
	public static void floodfill() {
		for (int v = 0; v < n; v++) {
			if (!visited[v]) {
				dfs(v);
				count++;
			}
		}
	}
	
	private static void dfs(int v) {
		visited[v] = true;
		comp[v] = count;
		for (int i = 0; i < n; i++) {
			if(i != v && ! visited[i] && adj[v][i] == 1) dfs(i);
		}
	}
}