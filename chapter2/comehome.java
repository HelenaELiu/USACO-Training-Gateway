
/*
ID: helena.6
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {
	static int[][] grid;
	static LinkedList<Integer> adj[];
	static int max = 99999999;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		
		grid = new int[52][52];
		for (int i = 0; i < 52; i++)
			Arrays.fill(grid[i], max);
		
		int p = Integer.parseInt(f.readLine());
		for (int i = 0; i < p; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = chartoInt(st.nextToken());
			int b = chartoInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (grid[a][b] > c) {
				grid[a][b] = c;
				grid[b][a] = c;
			}
		}

		floydWarshall();
		
		adj = new LinkedList[52]; // Adjacency Lists
		for (int i = 0; i < 52; i++)
			adj[i] = new LinkedList();
				
		for (int i = 0; i < 52; i++) {
			for (int k = 0; k < 52; k++) {
				if (grid[i][k] < max) {
					adj[i].add(k);
				}
			}
		}

		int[] a = bfs(51);
		int min = max;
		int index = -1;
		for (int i = 26; i < 51; i++) {
			if (a[i] < min) {
				index = i;
				min = a[i];
			}
		}
		
		out.println(inttoChar(index) + " " + min); //CHANGE THE SYSTEM.OUT THING
		out.close();
	}

	private static char inttoChar(int j) {
		if (j < 26) { //lowercase
			j = j + 97;
		} else { //uppercase
			j = j + 39;
		}
		char c = (char) j;
		
		return c;
	}

	private static int chartoInt(String s) {
		char c = s.charAt(0);
		int j = (int) c; // ascii
		if (j <= 90) { // capital letter
			j = j - 39; // 39 because A is 65 and in array it is 26. 65 - 26 = 39
		} else { // lowercase letter
			j = j - 97;
		}

		return j;
	}

	private static void floydWarshall() {
		for (int k = 0; k < 52; k++) {
			for (int i = 0; i < 52; i++) {
				for (int j = 0; j < 52; j++) {
					if (grid[i][k] + grid[k][j] < grid[i][j])
						grid[i][j] = grid[i][k] + grid[k][j];
				}
			}
		}
	}

	private static int[] bfs(int s) {
		int[] dist = new int[52];
		boolean visited[] = new boolean[52];

		LinkedList<Integer> queue = new LinkedList<Integer>(); // bfs queue

		for (int i = 0; i < 52; i++)
			dist[i] = max;

		visited[s] = true;
		queue.add(s);
		dist[s] = 0;

		while (queue.size() != 0) {
			s = queue.poll(); // removes object at front of queue

			Iterator<Integer> i = adj[s].listIterator(); // finds all adjacent to node s
			
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
					dist[n] = dist[s] + grid[s][n];
				}
			}
		}

		return dist;
	}
}