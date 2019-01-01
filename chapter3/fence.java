
/*
ID: helena.6
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.*;

public class fence {
	static ArrayList<Integer> adj[]; // Adjacency Lists
	static int F;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		F = Integer.parseInt(f.readLine());
		
		adj = new ArrayList[501]; //1 based
		for (int i = 0; i < 501; i++)
			adj[i] = new ArrayList();
		
		for (int i = 0; i < F; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		for (int i = 0; i < 501; i++)
			Collections.sort(adj[i]);
		
		int start = calculateDegree();

		int[] tour = eulerian(start);
		for (int i = tour.length - 1; i >= 0; i--)
			out.println(tour[i]);
		
		out.close();
	}
	
	private static int calculateDegree() {
		for (int i = 1; i < 501; i++) {
			if (adj[i].size() % 2 == 1) {
				return i;
			}
		}
		
		return -1;
	}

	private static int[] eulerian(int start) {
		Stack<Integer> stack = new Stack<>();
		
		int loc = -1;
		if (start == -1)
			loc = 1;
		else 
			loc = start;
		
		int[] circuit = new int[F + 1];
		int count = 0;
		
		while (count <= F) { //while the circuit is not completed
			if (adj[loc].size() > 0) { //neighboring nodes exist
				stack.push(loc);
				
				int prevLoc = loc;
				loc = adj[loc].get(0); //set new Loc
				
				adj[prevLoc].remove(0); //remove the edge
				adj[loc].remove(adj[loc].indexOf(prevLoc)); //both ways
			} else { //neighboring nodes do not exist
				circuit[count] = loc;
				count++;
				
				if (count == F + 1)
					break;
				
				loc = stack.pop();
			}
		}
        
		return circuit;
	}
}
