
/*
ID: helena.6
LANG: JAVA
TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {
	static int r, c;
	static int[][][][] grid;
	static ArrayList<Pos> knights;
	static Pos king;
	static int[] moverow = { 2, 1, -1, -2, -2, -1, 1, 2 }; // knights
	static int[] movecol = { -1, -2, -2, -1, 1, 2, 2, 1 }; // knights

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		grid = new int[r][c][r][c];

		boolean first = true;

		knights = new ArrayList<>();
		while (f.ready()) {
			String[] parts = f.readLine().split(" ");
			for (int i = 0; i < parts.length; i += 2) {
				if (first) {
					int kingCol = (int) parts[i].charAt(0) - 65;
					int kingRow = Integer.parseInt(parts[i + 1]) - 1;
					king = new Pos(kingRow, kingCol);
					first = false;
				} else {
					int knightCol = (int) parts[i].charAt(0) - 65;
					int knightRow = Integer.parseInt(parts[i + 1]) - 1;
					knights.add(new Pos(knightRow, knightCol));
				}
			}
		}

		int numMoves = move();

		out.println(numMoves);
		out.close();
	}

	private static int move() {
		int numMoves = Integer.MAX_VALUE;

		fillGrid();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int moves = numSteps(new Pos(i, j));	
				if (moves < numMoves) {
					numMoves = moves;
				}
			}
		}
		
		return numMoves;
	}
	
	private static void fillGrid() {
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				for (int k = 0; k < r; k++)
					Arrays.fill(grid[i][j][k], -1);

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++)
				movetoDest(new Pos(i, j));
		}
	}

	private static void movetoDest(Pos dest) {
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.add(dest);
		grid[dest.getRow()][dest.getCol()][dest.getRow()][dest.getCol()] = 0;
		
		while (!queue.isEmpty()) {
			Pos node = queue.poll();
			int x = node.getRow();
			int y = node.getCol();
			
			for (int i = 0; i < moverow.length; i++) {
				int x2 = x + moverow[i];
				int y2 = y + movecol[i];
				if (x2 >= 0 && x2 < r && y2 >= 0 && y2 < c) {
					if (grid[dest.getRow()][dest.getCol()][x2][y2] == -1 || 
							grid[dest.getRow()][dest.getCol()][x][y] + 1 < grid[dest.getRow()][dest.getCol()][x2][y2]) {
						int a = grid[dest.getRow()][dest.getCol()][x][y] + 1;
						grid[dest.getRow()][dest.getCol()][x2][y2] = a;
						grid[x2][y2][dest.getRow()][dest.getCol()] = a;
						queue.add(new Pos(x2, y2));
					}
				}
			}
		}
	}
	
	private static int numSteps(Pos dest) {
		int moves = 0;
		for (Pos knight : knights) {
			if (grid[dest.getRow()][dest.getCol()][knight.getRow()][knight.getCol()] == -1)
				return Integer.MAX_VALUE;
			moves += grid[dest.getRow()][dest.getCol()][knight.getRow()][knight.getCol()];
		}
		
		int temp = Math.max(Math.abs(dest.getRow() - king.getRow()), Math.abs(dest.getCol() - king.getCol()));
		
		for (int x = Math.max(0, king.getRow() - 2); x <= Math.min(r - 1, king.getRow() + 2); x++) {
			for (int y = Math.max(0, king.getCol() - 2); y <= Math.min(c - 1, king.getCol() + 2); y++) {
				for (Pos knight : knights) {
					if (grid[dest.getRow()][dest.getCol()][x][y] != -1 && grid[x][y][knight.getRow()][knight.getCol()] != -1) {
						temp = Math.min(temp,
								grid[dest.getRow()][dest.getCol()][x][y] + grid[x][y][knight.getRow()][knight.getCol()]
										- grid[dest.getRow()][dest.getCol()][knight.getRow()][knight.getCol()]
										+ Math.max(Math.abs(king.getRow() - x), Math.abs(king.getCol() - y)));
					}
				}
			}
		}

		return moves + temp;
	}
}

class Pos {
	private int row;
	private int col;

	public Pos(int row, int col) {
		this.col = col;
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
}