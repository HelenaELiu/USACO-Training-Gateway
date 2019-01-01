
/*
ID: helena.6
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
	static int w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		Graph g = new Graph(w * h);
		char[][] maze = new char[2 * h + 1][2 * w + 1];

		for (int i = 0; i < 2 * h + 1; i++) {
			String s = f.readLine();
			for (int k = 0; k < 2 * w + 1; k++) {
				maze[i][k] = s.charAt(k);
			}
		}

		int exit1 = -1;
		int exit2 = -1;
		for (int j = 1; j < 2 * w + 1; j += 2) {// find the exits. start with the top and bottom
			if (maze[0][j] == ' ') { //top row
				if (exit1 >= 0)
					exit2 = (j-1)/2;
				else
					exit1 = (j-1)/2;
			}
			if (maze[2*h][j] == ' ') { //bottom row
				if (exit1 >= 0)
					exit2 = (h-1)*w+(j-1)/2;
				else
					exit1 = (h-1)*w+(j-1)/2;
			}
		}

		for (int j = 1; j < 2 * h + 1; j += 2) {// search the left and right
			if (maze[j][0] == ' ') { //left row
				if (exit1 >= 0)
					exit2 = (j-1)/2*w;
				else
					exit1 = (j-1)/2*w;
			}
			if (maze[j][2*w] == ' ') { //right row
				if (exit1 >= 0)
					exit2 = (j-1)/2*w+w-1;
				else
					exit1 = (j-1)/2*w+w-1;
			}
		}
			
		int square = 0; // i could calculate it from i and k but i'm too lazy
		for (int i = 1; i < 2 * h + 1; i += 2) { // build the graph by finding edges
			for (int k = 1; k < 2 * w + 1; k += 2) { // start on 1 and +2 because fenceposts are only odd
				if (i > 1 && maze[i - 1][k] == ' ') // if there is an up square and can reach it
					g.addEdge(square, square - w);
				if (k > 1 && maze[i][k - 1] == ' ') // if there is a left square and can reach it
					g.addEdge(square, square - 1);
				if (i < 2 * h - 1 && maze[i + 1][k] == ' ') // if there is a down square and can reach it
					g.addEdge(square, square + w);
				if (k < 2 * w - 1 && maze[i][k + 1] == ' ') // if there is a right square and can reach it
					g.addEdge(square, square + 1);
				square++;
			}
		}

		int[] a = g.bfs(exit1); // bfs the exits.
		int[] b = g.bfs(exit2);
		int max = 0;
		for (int i = 0; i < w*h; i++) {
			int m = Math.min(a[i], b[i]);
			if (m > max)
				max = m;
		}
		
		out.println(max + 1); // plus one because you have to step out
		
		out.close();
	}
}

class Graph {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency Lists
	
	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++)
			adj[i] = new LinkedList();
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	int[] bfs(int s) {
		int[] dist = new int[V];
		boolean visited[] = new boolean[V];

		LinkedList<Integer> queue = new LinkedList<Integer>(); // bfs queue
		
		for (int i = 0; i < V; i++)
			dist[i] = Integer.MAX_VALUE;
		
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
					dist[n] = dist[s] + 1;
				}
			}
		}

		return dist;
	}
}