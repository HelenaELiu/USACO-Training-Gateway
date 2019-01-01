
/*
ID: helena.6
LANG: JAVA
TASK: namenum
*/

import java.io.*;

public class namenum {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader r = new BufferedReader(new FileReader("dict.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

		long num = Long.parseLong(f.readLine());
		String name = "";
		long serialNum = 0;
		int count = 0;
		
		while ((name = r.readLine()) != null) {
			serialNum = getSerialNum(name);

			if (serialNum == num) {
				out.println(name);
				count++;
			}

			serialNum = 0;
		}

		if (count == 0) {
			out.println("NONE");
		}

		out.close();
	}
	
	private static long getSerialNum(String name) {
		long serialNum = 0;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == 'A' || name.charAt(i) == 'B' || name.charAt(i) == 'C')
				serialNum = serialNum * 10 + 2;
			else if (name.charAt(i) == 'D' || name.charAt(i) == 'E' || name.charAt(i) == 'F')
				serialNum = serialNum * 10 + 3;
			else if (name.charAt(i) == 'G' || name.charAt(i) == 'H' || name.charAt(i) == 'I')
				serialNum = serialNum * 10 + 4;
			else if (name.charAt(i) == 'J' || name.charAt(i) == 'K' || name.charAt(i) == 'L')
				serialNum = serialNum * 10 + 5;
			else if (name.charAt(i) == 'M' || name.charAt(i) == 'N' || name.charAt(i) == 'O')
				serialNum = serialNum * 10 + 6;
			else if (name.charAt(i) == 'P' || name.charAt(i) == 'R' || name.charAt(i) == 'S')
				serialNum = serialNum * 10 + 7;
			else if (name.charAt(i) == 'T' || name.charAt(i) == 'U' || name.charAt(i) == 'V')
				serialNum = serialNum * 10 + 8;
			else if (name.charAt(i) == 'W' || name.charAt(i) == 'X' || name.charAt(i) == 'Y')
				serialNum = serialNum * 10 + 9;
			else if (name.charAt(i) == 'Q' || name.charAt(i) == 'Z') {
				serialNum = 0;
				break;
			}
		}
		
		return serialNum;
	}
}