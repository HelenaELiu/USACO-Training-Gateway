
/*
ID: helena.6
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.*;

public class msquare {
	static int goal;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		goal = 0;
		for (int i = 0; i < 8; i++)
			goal = goal * 10 + Integer.parseInt(st.nextToken());

		String sequence = bfs();
		int len = sequence.length();

		out.println(len);
		int index = 0;
		while (index + 60 <= sequence.length()) {
			out.println(sequence.substring(index, index + 60));
			index += 60;
		}
		out.println(sequence.substring(index));

		out.close();
	}

	private static String bfs() {
		if (goal == 12345678)
			return "";

		LinkedList<Square> queue = new LinkedList<Square>();
		HashSet<Integer> set = new HashSet<Integer>();

		Square s = new Square(12345678, "");
		queue.add(s);
		set.add(s.getSequence());

		while (queue.size() != 0) {
			s = queue.poll();

			if (s.getSequence() == goal) {
				return s.getTransform();
			}

			Square s2 = new Square();

			s2 = transformA(s);
			if (!set.contains(s2.getSequence())) {
				queue.add(s2);
				set.add(s2.getSequence());
			}

			s2 = transformB(s);
			if (!set.contains(s2.getSequence())) {
				queue.add(s2);
				set.add(s2.getSequence());
			}

			s2 = transformC(s);
			if (!set.contains(s2.getSequence())) {
				queue.add(s2);
				set.add(s2.getSequence());
			}
		}

		return null; // should be unreachable
	}

	private static int[] intToArray(int pattern) {
		int[] pat = new int[8];
		for (int i = 0; i < 8; i++) {
			pat[i] = Integer.parseInt(Integer.toString(pattern).substring(i, i + 1));
		}

		return pat;
	}

	private static int arrayToInt(int[] pattern) {
		int pat = 0;
		for (int i = 0; i < 8; i++) {
			pat = pat * 10 + pattern[i];
		}

		return pat;
	}

	public static Square transformA(Square s) {
		Square s2 = new Square();
		int pattern = s.getSequence();
		int[] pat = intToArray(pattern);

		for (int i = 0; i < pat.length / 2; i++) {
			Integer temp = pat[i];
			pat[i] = pat[pat.length - i - 1];
			pat[pat.length - i - 1] = temp;
		}
		pattern = arrayToInt(pat);
		s2.setSequence(pattern);
		s2.setTransform(s.getTransform() + "A");

		return s2;
	}

	public static Square transformB(Square s) {
		Square s2 = new Square();
		int pattern = s.getSequence();
		int[] pat = intToArray(pattern);

		Integer x = pat[3];
		for (int i = 3; i > 0; i--)
			pat[i] = pat[i - 1];
		pat[0] = x;

		Integer y = pat[4];
		for (int i = 4; i < 7; i++)
			pat[i] = pat[i + 1];
		pat[7] = y;
		pattern = arrayToInt(pat);
		s2.setSequence(pattern);
		s2.setTransform(s.getTransform() + "B");

		return s2;
	}

	public static Square transformC(Square s) {
		Square s2 = new Square();
		int pattern = s.getSequence();
		int[] pat = intToArray(pattern);

		Integer x = pat[6];
		pat[6] = pat[5];
		pat[5] = pat[2];
		pat[2] = pat[1];
		pat[1] = x;

		pattern = arrayToInt(pat);
		s2.setSequence(pattern);
		s2.setTransform(s.getTransform() + "C");

		return s2;
	}
}

class Square {
	private int sequence;
	private String transform; // transformations

	public Square() {
		sequence = 0;
		transform = "";
	}

	public Square(int x, String y) {
		sequence = x;
		transform = y;
	}

	public int getSequence() {
		return sequence;
	}

	public String getTransform() {
		return transform;
	}

	public void setSequence(int x) {
		sequence = x;
	}

	public void setTransform(String s) {
		transform = s;
	}
}