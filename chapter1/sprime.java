
/*
ID: helena.6
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.ArrayList;

public class sprime {
	static int[] firstDigit = { 2, 3, 5, 7 };
	static int[] odds = { 1, 3, 5, 7, 9 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

		int n = Integer.parseInt(f.readLine()); // length of superprimes
		ArrayList<Integer> supprimes = new ArrayList<>();
		supprimes = generateSuperprimes(n, 0, supprimes);

		for (int s : supprimes) {
			out.println(s);
		}

		out.close();
	}

	private static ArrayList<Integer> generateSuperprimes(int n, int supprime, ArrayList<Integer> supprimes) {
		int length = Integer.toString(supprime).length();
		if (supprime == 0) {
			for (int i = 0; i < 4; i++) { // length of firstDigit
				supprime = firstDigit[i];
				supprimes = generateSuperprimes(n, supprime, supprimes);
			}
		} else if (length == n) {
			if (isPrime(supprime))
				supprimes.add(supprime);
		} else {
			for (int i = 0; i < 5; i++) { // length of odds
				if (isPrime(supprime)) {
					supprimes = generateSuperprimes(n, supprime * 10 + odds[i], supprimes);
				}
			}
		}

		return supprimes;
	}

	private static boolean isPrime(int n) {
		if (n == 2)
			return true;
		else if (n % 2 == 0)
			return false;

		double sqrt = Math.sqrt(n);
		for (int i = 3; i <= sqrt; i = i + 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}
	
	/*
	public static void superprime(int n)
	   {
	      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
	      recur(3, n); 
	      recur(5, n);
	      recur(7, n);
	   }

	   private static void recur(int k, int n)
	   {
	      if (isPrime(k) && Integer.toString(k).length() == n) {
	         count++;
	         System.out.println(k);
	      } 
	      else if (isPrime(k)) {
	         recur(k * 10 + 1, n);
	         recur(k * 10 + 3, n); 
	         recur(k * 10 + 7, n);
	         recur(k * 10 + 9, n);
	      }
	   }
	   
	    public static boolean isPrime(int n)
   {
      if(n == 2 || n == 3) 
         return true;
      if(n % 2 == 0 || n % 3 == 0) 
         return false;
    
      int sqrtN = (int) Math.sqrt(n) + 1;
      for (int i = 6; i <= sqrtN; i+= 6) {
         if (n % (i-1) == 0 || n%(i+1) == 0)
            return false;
      }
      
      return true;
   }
	*/
}
