
/*
ID: helena.6
LANG: JAVA
TASK: palsquare
*/

import java.io.*;

public class palsquare {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		
		int b = Integer.parseInt(f.readLine()); //base b in base 10
		String s = "";
		
		for (int i = 1; i <= 300; i++) {
			int sq = i*i; //square n in base 10
			s = Integer.toString(Integer.parseInt(Integer.toString(sq), 10), b).toUpperCase();
			
			if (isPalindrome(s)) {
				StringBuilder output = new StringBuilder();
				output.append(Integer.toString(Integer.parseInt(Integer.toString(i), 10), b).toUpperCase()).append(" ").append(s);
				out.println(output.toString());
			}
		}
		
		out.close();
	}
	
	private static boolean isPalindrome(String s) {
		StringBuilder sb = new StringBuilder(s);
		
		return (s.equals(sb.reverse().toString()));
	}
}
