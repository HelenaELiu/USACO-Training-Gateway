
/*
ID: helena.6
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		
		double n = Double.parseDouble(f.readLine());

		List<Fraction> fractions = new ArrayList<>();
		List<Double> decimal = new ArrayList<Double>();
		fractions.add(new Fraction(0, 1));
		for (int i = 1; i <= n; i++) { // numerator
			for (int k = 1; k <= n; k++) { // denominator
				if (k > i) {
					Fraction fraction = new Fraction(i, k);
					if (!decimal.contains(fraction.getValue())) {
						if (fraction.getNumerator() != 1)
							fraction = simplify(fraction);
						fractions.add(fraction);
						decimal.add(fraction.getValue());
					}
				}
			}
		}

		fractions.add(new Fraction(1, 1));

		Collections.sort(fractions, new Comparator<Fraction>() {
			@Override
			public int compare(Fraction x, Fraction y) {
				if (x.getValue() - y.getValue() < 0)
					return -1;
				else if (x.getValue() - y.getValue() > 0)
					return 1;
				return 0;
			}
		});

		for (Fraction g : fractions) {
			out.println(g.toString());
		}
		out.close();

	}

	private static Fraction simplify(Fraction fraction) {
		int g = gcd(fraction.getNumerator(), fraction.getDenominator());
		fraction.setFraction(fraction.getNumerator() / g, fraction.getDenominator() / g);
		return fraction;
	}

	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
}

class Fraction {
	private int numerator;
	private int denominator;

	public Fraction() {
		numerator = 0;
		denominator = 0;
	}

	public Fraction(int x, int y) {
		numerator = x;
		denominator = y;
	}

	public double getValue() {
		return (double) numerator / (double) denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setFraction(int n, int d) {
		numerator = n;
		denominator = d;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(numerator + "/" + denominator);
		return sb.toString();
	}
}