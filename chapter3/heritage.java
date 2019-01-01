
/*
ID: helena.6
LANG: JAVA
TASK: heritage
*/

import java.io.*;

public class heritage {
	static char[] in, pre;
	static int length;
	static int preIndex = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		
		in = f.readLine().toCharArray();
		pre = f.readLine().toCharArray();
		length = pre.length;
		
		String post = "";
		post = findPost(0, length - 1, "");
		out.println(post);
		out.close();
	}

	private static String findPost(int startIndex, int endIndex, String post) {
		if (startIndex > endIndex)
			return post;

		int rootIndex = searchIn(startIndex, endIndex, pre[preIndex++]);
		
		post = findPost(startIndex, rootIndex - 1, post); //left subtree
		post = findPost(rootIndex + 1, endIndex, post); //right subtree
		post = post + in[rootIndex]; //root

		return post;
	}
	
	private static int searchIn(int start, int end, char c) { 
		int index = 0;
        for (index = start; index < end; index++)  
            if (in[index] == c)  
                break;

        return index; 
    }       
}
