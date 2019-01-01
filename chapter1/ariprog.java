
/*
ID: helena.6
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.*;

public class ariprog {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

		int length = Integer.parseInt(f.readLine());
		int maxPQ = Integer.parseInt(f.readLine());

		int[] bisquares = new int[2 * maxPQ * maxPQ + 1]; // the number of bisquares is equal to (maxPQ+2) choose 2

		for (int i = 0; i <= maxPQ; i++) { // i is p
			for (int k = i; k <= maxPQ; k++) {
				bisquares[i * i + k * k] = 1;
			}
		}

		int count = 0;

		List<Sequence> sequences = new ArrayList<Sequence>();
		for (int a = 0; a < 2 * maxPQ * maxPQ; a++) {
			int b = 1;
			while (a + (length - 1) * b <= 2 * maxPQ * maxPQ) { // biggest num in sequence is smaller than or equal
																// to max
				if (sequenceWorks(a, b, length, bisquares)) {
					sequences.add(new Sequence(a, b));
					count++;
				}
				b++;
			}
		}

		Collections.sort(sequences, new Comparator<Sequence>() {
			@Override
			public int compare(Sequence x, Sequence y) {
				if (x.getB() != y.getB())
					return x.getB() - y.getB();
				return x.getA() - y.getA();
			}
		});

		if (count == 0) {
			out.println("NONE"); // none or 'none'?
		} else {
			for (Sequence s : sequences)
				out.println(s.getA() + " " + s.getB());
		}

		out.close();
	}

	private static boolean sequenceWorks(int a, int b, int length, int[] bisquares) {
		for (int i = 0; i < length; i++) {
			int term = a + i * b;
			if (bisquares[term] != 1) {
				return false;
			}
		}

		return true;
	}
}

class Sequence {
	private int a;
	private int b;

	public Sequence() {
		a = 0;
		b = 0;
	}

	public Sequence(int x, int y) {
		a = x;
		b = y;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}
}