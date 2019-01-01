
/*
ID: helena.6
LANG: JAVA
TASK: ttwo
*/

import java.io.*;

public class ttwo {
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[][] grid;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

		int[] farmer = {-1, -1};
		int[] cows = {-1, -1};
		grid = new char[10][10];
		for (int i = 0; i < 10; i++) {
			String str = f.readLine();
			for (int k = 0; k < 10; k++) {
				char ch = str.charAt(k);
				grid[i][k] = ch;
				if (ch == 'F') {
					farmer[0] = i;
					farmer[1] = k;
				} else if (ch == 'C') {
					cows[0] = i;
					cows[1] = k;
				}
			}
		}
		
		int farmerDir = 0; // 0 is north 1 is east 2 is south 3 is west
		int cowsDir = 0;
		int minutes = 0;
		while (minutes < 160000) { //figure out <= or <
			if (noObstacle(farmer, farmerDir))
				move(farmer, farmerDir, 'F');
			else 
				farmerDir = (farmerDir + 1) % 4;
			if (noObstacle(cows, cowsDir))
				move(cows, cowsDir, 'C');
			else 
				cowsDir = (cowsDir + 1) % 4;
			minutes++;			
			if(farmer[0] == cows[0] && farmer[1] == cows[1]) //if the farmer and cows are on the same spot then break
				break;
		}
		
		if (minutes == 160000) {
			out.println(0);
		} else {
			out.println(minutes);
		}
		
		out.close();
	}
	
	private static void move(int[] coords, int dir, char c) {	
		grid[dirs[dir][0] + coords[0]][dirs[dir][1] + coords[1]] = c;
		grid[coords[0]][coords[1]] = '.';
		coords[0] += dirs[dir][0];
		coords[1] += dirs[dir][1];
	}

	private static boolean noObstacle(int[] coords, int dir) { //also covers edge of board
		if (dirs[dir][0] + coords[0] > 9 || dirs[dir][1] + coords[1] > 9)
			return false;
		if (dirs[dir][0] + coords[0] < 0 || dirs[dir][1] + coords[1] < 0)
			return false;
		if (grid[dirs[dir][0] + coords[0]][dirs[dir][1] + coords[1]] == '*')
			return false;
		return true;
	}
}
