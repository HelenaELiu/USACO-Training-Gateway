/*
ID: helena.6
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.StringTokenizer;

public class dualpal {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken()) + 1;
		int count = 0;
		
		while (count < n) {
			if (isDualPalindrome(s)) {
				out.println(s);
				count++;
			}
			s++;			
		}
		
		out.close();
	}

	private static boolean isDualPalindrome(int s) { //checks to see if s is palindromic in 2+ bases
		int count = 0;
		for (int i = 2; i<=10; i++) {
			if (isPalindrome(Integer.toString(Integer.parseInt(Integer.toString(s), 10), i))) {
				count++;
			}
			if (count == 2) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean isPalindrome(String s) {
		StringBuilder sb = new StringBuilder(s);
		
		return (s.equals(sb.reverse().toString()));
	}
}