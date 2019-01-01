
/*
ID: helena.6
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int quota = Integer.parseInt(st.nextToken());
		int numFarmers = Integer.parseInt(st.nextToken());
		Sale[] sales = new Sale[numFarmers];

		for (int i = 0; i < numFarmers; i++) {
			StringTokenizer st2 = new StringTokenizer(f.readLine());
			sales[i] = new Sale(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
		}

		Arrays.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale x, Sale y) {
				return x.getCost() - y.getCost();
			}
		});
		
		int minCost = 0;
		int k = 0;

		while (quota > 0) {
			int fullPrice = sales[k].getCost() * sales[k].getUnits();
			if (quota - sales[k].getUnits() < 0) {
				minCost = minCost + sales[k].getCost() * quota;
				quota = 0;
			} else {
				minCost = minCost + fullPrice;
				quota = quota - sales[k].getUnits();
			}
			k++;
		}

		out.println(minCost);
		out.close();
	}
}

class Sale {
	private int cost;
	private int numUnits;

	public Sale() {
		cost = 0;
		numUnits = 0;
	}

	public Sale(int c, int n) {
		cost = c;
		numUnits = n;
	}

	public int getCost() {
		return cost;
	}

	public int getUnits() {
		return numUnits;
	}
}