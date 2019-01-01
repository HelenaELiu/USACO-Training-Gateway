
/*
ID: helena.6
LANG: JAVA
TASK: fence9
*/

import java.io.*;
import java.util.StringTokenizer;

public class fence9 {
	static int n, m, p;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		int a = getArea();
		int b = gcd(n, m) + gcd(Math.abs(p - n), m) + p; // gcd (p,0) is p

		int i = a - b / 2 + 1;
		out.println(i);
		out.close();
	}

	private static int gcd(int x, int y) {
		if (x == 0)
			return y;
		if (y == 0)
			return x;
		return gcd(y, x % y);
	}

	private static int getArea() {
		return m * p / 2;
	}
}
