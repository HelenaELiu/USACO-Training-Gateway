
/*
ID: helena.6
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {
	static int seqlength;
	static String sequence;
	static boolean[] visited;
    static List<String> primitives = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		primitives = new ArrayList<>();

		String line = "";
		while (!(line = f.readLine()).equals(".")) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++)
				primitives.add(temp[i]);
		}

		StringBuilder sb = new StringBuilder();
		while (f.ready()) {
			sb.append(f.readLine());
		}

		sequence = sb.toString();
		seqlength = sequence.length();
		int longestPrefix = findLongestPrefix();
		
		out.println(longestPrefix);
		out.close();
	}

	private static int findLongestPrefix() {
		visited = new boolean[seqlength+1];
		visited[0] = true;
		int max = 0;

		for (int i = 0; i < seqlength; i++) { // index in string
			if (visited[i])
			    findPrefixes(i);
		}

		for (int i = seqlength; i >= 0; i--) {
			if (visited[i]) {
				max = i;
				break;
			}
		}

		return max;
	}

	private static void findPrefixes(int index) {
		for (String p : primitives) {
			int length = p.length();
			if (index + length <= seqlength && !visited[index + length] && isPrefix(p, sequence.substring(index, index + length))) {
				visited[index + length] = true;
			}
		}
	}

	private static boolean isPrefix(String primitive, String substring) {
		int length = primitive.length();
		for (int i = 0; i < length; i++) {
			if (primitive.charAt(i) != substring.charAt(i))
				return false;
		}

		return true;
	}
}
