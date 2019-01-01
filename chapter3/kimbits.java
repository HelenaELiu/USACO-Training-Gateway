
/*
ID: helena.6
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class kimbits {
	static int n, l;
	static BigInteger i;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		i = new BigInteger(st.nextToken());

		BigInteger sizeOf[][] = new BigInteger[n + 1][l + 1];

		for (int i = 0; i <= n; i++)
			Arrays.fill(sizeOf[i], BigInteger.ONE);

		sizeOf = getDP(sizeOf);
		
		String s = getString(sizeOf);
		
		out.println(s);
		out.close();
	}

	private static BigInteger[][] getDP(BigInteger sizeOf[][]) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= l; j++) {
				if (j > i)
					sizeOf[i][j] = sizeOf[i][j - 1];
				else
					sizeOf[i][j] = sizeOf[i][j - 1].add(numperms(i, j));
			}
		}
		
		return sizeOf;
	}

	private static String getString(BigInteger sizeOf[][]) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			int result = i.compareTo(sizeOf[n - 1][l]);

			if (l == 0)
				sb.append(0);
		    else if (result == 1) {
				sb.append(1);
				i = i.subtract(sizeOf[n - 1][l]);
				l -= 1;
			} else
				sb.append(0);

			n--;
		}
		
		return sb.toString();
	}

	private static BigInteger factorial(int n) {
		if (n == 0 || n == 1)
			return BigInteger.ONE;

		return (BigInteger.valueOf(n)).multiply(factorial(n - 1));
	}

	private static BigInteger numperms(int n, int bits_set) {
		return factorial(n).divide(factorial(bits_set).multiply(factorial(n - bits_set)));
	}
}
