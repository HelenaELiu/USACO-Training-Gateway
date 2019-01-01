
/*
ID: helena.6
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

public class gift1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

		int numGroups = Integer.parseInt(f.readLine());
		String[] nameOrder = new String[numGroups];
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int x = 0; x < numGroups; x++) {
			String name = f.readLine();
			map.put(name, 0);
			nameOrder[x] = name;
		}
		
		String giver = "";
		
		while ((giver = f.readLine()) != null) { 
			StringTokenizer st = new StringTokenizer(f.readLine());
			int amtMoney = Integer.parseInt(st.nextToken());
			int numPeople = Integer.parseInt(st.nextToken());
			int remainder = 0;
			if (amtMoney > 0 && numPeople > 0)
				remainder = amtMoney % numPeople;
			
			map.put(giver, map.get(giver) - amtMoney + remainder);
			for (int k = 0; k < numPeople; k++) {
				String reciever = f.readLine();
				map.put(reciever, map.get(reciever) + amtMoney/numPeople);
			}
			
		}
		
		for (int x = 0; x < numGroups; x++) {
			out.println(nameOrder[x] + " " + map.get(nameOrder[x]));
		}
		
		out.close();
	}
}
