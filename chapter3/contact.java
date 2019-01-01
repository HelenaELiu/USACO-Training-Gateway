
/*
ID: helena.6
LANG: JAVA
TASK: contact
*/

import java.io.*;
import java.util.*;

public class contact {
	static int a, b, n;
	static String s;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		while (f.ready())
			sb.append(f.readLine());
		s = sb.toString();

		ArrayList<Pattern> list = findPatterns();
		
		int count = 0;
		int k = 0;
		int consec = 0; //finds consecutive frequencies 
		StringBuilder sb2 = new StringBuilder();
		
		while (count < n && k < list.size()) {
			int freq = list.get(k).getFreq();
			if (k + 1 < list.size() && list.get(k+1).getFreq() == freq) { //if next number has same freq	
				sb2.append(list.get(k).getString()).append(" ");
				k++;
				consec++;
				if (consec % 6 == 0) { //check this logic
					sb2.append("\n");
				}
			} else { //next number does not have same frequency
				sb2.append(list.get(k).getString());
				out.println(freq);
				out.println(sb2.toString().trim().replaceAll(" \\n", "\n"));
				sb2.setLength(0);
				consec = 0;
				k++;
				count++;
			}
		}
		
		out.close();
	}

	private static ArrayList<Pattern> findPatterns() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = a; j <= b; j++) {
				if (i + j <= s.length()) {//if substring still in bounds
					String substr = s.substring(i, i+j);
					if (!map.containsKey(substr)) 
						map.put(substr, 1); //add key with count one
					else
						map.put(substr, map.get(substr)+1); //up count of key by one
				}
			}
		}
		
		ArrayList<Pattern> list = new ArrayList<Pattern>();
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		    list.add(new Pattern(entry.getKey(), entry.getValue()));
		
		Collections.sort(list, new Comparator<Pattern>() {
			@Override
			public int compare(Pattern x, Pattern y) {
				if (y.getFreq() == x.getFreq()) {
					if (x.getString().length() == y.getString().length())
						return Integer.parseInt(x.getString(), 2) - Integer.parseInt(y.getString(), 2);
					return x.getString().length() - y.getString().length();
				}
				return y.getFreq() - x.getFreq();
			}
		});
		
		return list;
	}
}

class Pattern {
	private String s;
	private int freq;

	public Pattern(String s, int freq) {
		this.s = s;
		this.freq = freq;
	}

	public String getString() {
		return s;
	}

	public int getFreq() {
		return freq;
	}
}
