/*
ID: helena.6
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.StringTokenizer;

public class hamming {
	static int num, length, distance, count = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		num = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		distance = Integer.parseInt(st.nextToken());
		
		int[] codewords = new int[num];
		findCodewords(codewords);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= count; i++) {
			sb.append(codewords[i-1]).append(" ");
			if (i % 10 == 0) {
				out.println(sb.toString().trim());
				sb.setLength(0);
			} else if (i == count) {
				out.println(sb.toString().trim());
			} 
		}
		
		out.close();
	}
	
	private static void findCodewords(int[] codewords) {
		for (int j = 1; j < num; j++) { //index of codewords	
			for (int i = codewords[j-1] + 1; i < Math.pow(2, length); i++) { //represents each number to be compared. starts at previous number
				if (hasHDist(i, j, codewords)) { //compare i to all previous entries in codewords
					codewords[j] = i;
					count++;
					break;
				}				
			}
		}
	}
	
	private static boolean hasHDist(int i, int j, int[] codewords) {
		for(int m = 0; m < j; m++) {
			if (!hDist(i, codewords[m])) {
				return false;
			}
		}
		
		return true;
	}
	
    private static boolean hDist(int x, int y) { // b is the length
        int cnt = 0;
        for (int i = 0; i < length; i++) {
            if ((x & 1) != (y & 1)) cnt++;
            
            if (cnt == distance) return true;

            x = x >> 1;
            y = y >> 1;
        }
        
        return false;
    }
}
