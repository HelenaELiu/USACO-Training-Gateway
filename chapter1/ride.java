/*
ID: helena.6
LANG: JAVA
TASK: ride
*/
import java.io.*;

public class ride {	
	  public static void main (String [] args) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

	    int comet = total(f.readLine());    
	    int group = total(f.readLine());  

	    if (comet % 47 == group % 47)
	    	out.println("GO");   
	    else
	    	out.println("STAY");
	    
	    out.close();                       
	  }
	  
	  public static int total(String str) {
		  int total = 1;
		  for (int x = 0 ; x < str.length(); x++) {
			  total = total * ((int) str.charAt(x) - 64);
		  }
		  
		  return total;
	  }
}