
/*
ID: helena.6
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

		int numCows = Integer.parseInt(f.readLine());
		Interval[] milkTimes = new Interval[numCows];

		for (int i = 0; i < numCows; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			milkTimes[i] = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(milkTimes, new Comparator<Interval>() {
			@Override
			public int compare(Interval x, Interval y) {
				return x.getStart() - y.getStart();
			}
		});
		
		int start = milkTimes[0].getStart(); //stores previous one's start
		int end = milkTimes[0].getEnd(); //stores previous one's ending
		int maxMilk = end - start;
		int maxNoMilk = 0;

		for (int k = 1; k < milkTimes.length; k++) { //start with 1 because 0 was already done
			Interval x = milkTimes[k];
			if (x.getStart() <= end) { //if it starts before the previous one ends
				if (x.getEnd() > end) {
					end = x.getEnd();
					maxMilk = Math.max(maxMilk, end - start);
				}
			} 
			else { //intervals do not overlap
				maxMilk = Math.max(maxMilk, x.getEnd() - x.getStart()); //the longest time is the bigger of the two intervals
				maxNoMilk = Math.max(maxNoMilk, x.getStart() - end); //previous vs new starting time minus old one
				start = x.getStart();
				end = x.getEnd();
			}
		}

		out.println(maxMilk + " " + maxNoMilk);
		out.close();
	}
}

class Interval {
	private int startTime;
	private int endTime;

	public Interval() {
		startTime = 0;
		endTime = 0;
	}

	public Interval(int s, int e) {
		startTime = s;
		endTime = e;
	}

	public int getStart() {
		return startTime;
	}

	public int getEnd() {
		return endTime;
	}
}