
/*
ID: helena.6
LANG: JAVA
TASK: spin
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class spin {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

		Wheel[] wheels = new Wheel[5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			wheels[i] = new Wheel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int j = 0; j < wheels[i].getNumWedges(); j++) {
				wheels[i].setWedges(j, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
		}

		int time = -1;
		for (int i = 0; i < 360; i++) {
			if (checkWheels(wheels)) {
				time = i;
				break;
			}
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < wheels[j].getNumWedges(); k++) {
					wheels[j].setWedgeStart(k, wheels[j].getSpeed());
				}
			}
		}

		if (time == -1)
			out.println("none");
		else
			out.println(time);
		
		out.close();
	}

	private static boolean checkWheels(Wheel[] wheels) {
		boolean[] empty = new boolean[360];
		Arrays.fill(empty, true);

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 360; k++) {
				if (empty[k]) {
					boolean inBound = false;
					for (int j = 0; j < wheels[i].getNumWedges(); j++) {
						inBound = inBounds(wheels, i, j, k);
						if (inBound)
							break;
					}
					if (!inBound)
						empty[k] = false;
				}
			}
		}

		for (int i = 0; i < 360; i++) {
			if (empty[i])
				return true;
		}

		return false;
	}

	private static boolean inBounds(Wheel[] wheels, int i, int j, int k) {
		if (wheels[i].getWedgeStart(j) <= wheels[i].getWedgeEnd(j)) {
			if (k >= wheels[i].getWedgeStart(j) && k <= wheels[i].getWedgeEnd(j))
				return true;
		} else {
			if (k >= wheels[i].getWedgeStart(j) || k <= wheels[i].getWedgeEnd(j))
				return true;
		}

		return false;
	}
}

class Wheel {
	private int numWedges;
	private int speed;
	private int[] wedgeStart;
	private int[] wedgeSize;

	public Wheel(int x, int y) {
		speed = x;
		numWedges = y;
		wedgeStart = new int[y];
		wedgeSize = new int[y];
	}

	public void setWedges(int x, int y, int z) {
		wedgeStart[x] = y;
		wedgeSize[x] = z;
	}

	public int getNumWedges() {
		return numWedges;
	}

	public int getSpeed() {
		return speed;
	}

	public int getWedgeStart(int x) {
		return wedgeStart[x];
	}

	public int getWedgeEnd(int x) {
		return (wedgeStart[x] + wedgeSize[x]) % 360;
	}

	public void setWedgeStart(int x, int y) {
		wedgeStart[x] = (wedgeStart[x] + y) % 360;
	}
}
