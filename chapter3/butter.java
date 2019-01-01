
/*
ID: helena.6
LANG: JAVA
TASK: butter
*/

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class butter {
	static int n, p, c, minDist;
	static int[][] weight;
	static int[] numCow; // number of cows in each pasture at the beginning
	static LinkedList<Integer> adj[]; // Adjacency Lists
	static int inf = 500000; // largest possible distance is 1450*225 = 326250, far smaller than 500000

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		weight = new int[p][p];
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < i; j++) {
				weight[i][j] = inf;
				weight[j][i] = inf;
			}
		}

		numCow = new int[p];
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(f.readLine());
			numCow[x - 1]++;
		}

		adj = new LinkedList[p];
		for (int i = 0; i < p; i++)
			adj[i] = new LinkedList();

		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(f.readLine());
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());
			adj[j].add(k);
			adj[k].add(j);
			weight[j][k] = l;
			weight[k][j] = l;
		}
		
		minDist = inf;
		for (int i = 0; i < p; i++) {
			int sum = dijkstra(i);
			if (sum < minDist)
				minDist = sum;
		}

		out.println(minDist);
		out.close();
	}

	private static int dijkstra(int s) {
		int[] dist = new int[p];
		Arrays.fill(dist, inf);
		boolean visited[] = new boolean[p];
		dist[s] = 0;
		int count = 0; //counts how many have been visited
		int sum = 0;
		int cowsLeft = n;
		
		while (count < p) {
			Iterator<Integer> i = adj[s].listIterator(); // finds all adjacent to node s
			while (i.hasNext()) {
				int n = i.next();
				int d = weight[s][n] + dist[s];
				if (d < dist[n]) {
					dist[n] = d;
				}
			}
			
			visited[s] = true;
			int nextSmallest = -1;
			int nextSmallestDist = inf;
			cowsLeft -= numCow[s];
			
			sum += (dist[s] * numCow[s]);
			if (sum + dist[s] * cowsLeft > minDist)
				return inf;
			
			for (int j = 0; j < p; j++) {
				if (!visited[j] && dist[j] < nextSmallestDist) { //find smallest distance node that hasn't been chosen.
					nextSmallest = j;
					nextSmallestDist = dist[j];
				}
			}
			s = nextSmallest; //set s as that node.
			
			count++;
		}
		
		return sum;
	}
}