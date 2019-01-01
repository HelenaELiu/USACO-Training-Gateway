
/*
ID: helena.6
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;

public class barn1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int numBoards = Integer.parseInt(st.nextToken());
		int numStalls = Integer.parseInt(st.nextToken());
		int numCows = Integer.parseInt(st.nextToken());
		int[] cows = new int[numCows];
		List<Gap> gaps = new ArrayList<Gap>();

		for (int i = 0; i < numCows; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(cows);

		for (int i = 1; i < numCows; i++) {
			if (cows[i] - cows[i - 1] > 1)
				gaps.add(new Gap(cows[i - 1], cows[i]));
		}

		Collections.sort(gaps, new Comparator<Gap>() {
		    @Override
		    public int compare(Gap x, Gap y) {
		        return x.getLength() - y.getLength();
		    }
		});
		
		while (gaps.size() > numBoards - 1) { 
			gaps.remove(0);
		}
		
		int sum = 0;
		for (Gap g: gaps) {
			sum = sum + g.getLength();
		}
		
		int stalls = cows[cows.length-1] - cows[0] + 1;
		out.println(stalls - sum);
		
		out.close();
	}
}

class Gap {
	private int start;
	private int end;

	public Gap() {
		start = 0;
		end = 0;
	}

	public Gap(int s, int e) {
		start = s;
		end = e;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getLength() {
		return end - start - 1;
	}
}