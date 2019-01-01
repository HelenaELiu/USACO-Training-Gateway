
/*
ID: helena.6
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.*;

public class shopping {	
	static int[] prices = new int[5];
	static HashMap<Integer, Integer> itemCodes = new HashMap<Integer, Integer>();
	static HashMap<Purchase, Integer> offers = new HashMap<Purchase, Integer>();
	static Purchase goal;
	static int s, b;
	static int inf = 25000; //max number of products is 25 and max price per product is 999. 999 * 25 is smaller than 25000 
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		
		s = Integer.parseInt(f.readLine());
		String [] tempOffers = new String[s];
		
		for (int i = 0; i < s; i++)
			tempOffers[i] = f.readLine();

		b = Integer.parseInt(f.readLine());
		String[] tempProducts = new String[b];
		for (int i = 0; i < b; i++)
			tempProducts[i] = f.readLine();
		
		initialize(tempOffers, tempProducts);
		int minCost = calculateCost();
		
		out.println(minCost);
		out.close();
	}
	
	private static void initialize(String[] tempOffers, String[] tempProducts) {
		int[] items = new int[5];
		for (int i = 0; i < b; i++) {
			StringTokenizer st = new StringTokenizer(tempProducts[i]);
			itemCodes.put(Integer.parseInt(st.nextToken()), i);	
			items[i] = Integer.parseInt(st.nextToken());
			prices[i] = Integer.parseInt(st.nextToken());
		}
		
		goal = new Purchase(items);
		
		for (int i = 0; i < s; i++) {
			int[] offerContent = new int[5];
			StringTokenizer st = new StringTokenizer(tempOffers[i]);
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int code = Integer.parseInt(st.nextToken());
				int number = Integer.parseInt(st.nextToken());
				offerContent[itemCodes.get(code)] = number;
			}
			int price = Integer.parseInt(st.nextToken());

			offers.put(new Purchase(offerContent), price);
		}
	}
	
	private static int calculateCost() {
		int[] dp = new int[inf];

		getLargestPrices(dp);

		for (int i = 0; i <= goal.getState(); i++) {
			Purchase current = new Purchase(i);
			for (Purchase offer : offers.keySet()) {
				if (current.contains(offer)) {
					if (dp[i - offer.state] + offers.get(offer) < dp[i])
						dp[i] = dp[i - offer.state] + offers.get(offer);
				}
			}
		}

		return dp[goal.getState()];
	}
	
	private static void getLargestPrices(int[] dp) {
		for (int i = 0; i < inf; i++) {		
			Purchase p = new Purchase(i);
			int sum = 0;
			
			for (int j = 0; j < 5; j++)
				sum += (prices[j] * p.getItem(j));
			
			dp[i] = sum;
		}
	}
}

class Purchase {
	int state;
	private int[] powersofSix = new int[] { 1, 6, 36, 216, 1296, 7776 };

	public Purchase(int[] items) {
		for (int i = 0; i < items.length; i++) {
			state += (items[i] * powersofSix[i]);
		}
	}

	public Purchase(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public int getItem(int item) {
		return (state % powersofSix[item + 1]) / powersofSix[item];
	}

	public boolean contains(Purchase p) {
		for (int i = 0; i < 5; i++) {
			if (getItem(i) < p.getItem(i)) 
				return false;
		}
		
		return true;
	}
}