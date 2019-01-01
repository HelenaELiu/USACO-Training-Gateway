
/*
ID: helena.6
LANG: JAVA
TASK: beads
*/
import java.io.*;

public class beads {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

		int numBeads = Integer.parseInt(f.readLine());
		String beads = f.readLine();
		int max = 0;
		int forward = 0;
		int backward = 0;

		for (int i = 0; i < numBeads; i++) { // make sure no endless loop
			forward = countForward(beads);
			backward = countBackward(beads);
			if (forward + backward > max)
				max = forward + backward;
			beads = beads.substring(1) + beads.substring(0, 1);
		}

		if (max > numBeads)
			max = numBeads;

		out.println(max);
		out.close();
	}

	private static int countForward(String beads) {
		int count = 0;
		char color = ' ';
		char[] charbeads = beads.toCharArray();

		for (int i = 0; i < charbeads.length; i++) {
			char bead = charbeads[i];
			if (color == ' ') {
				if (bead == 'r')
					color = 'r';
				else if (bead == 'b')
					color = 'b';
				count++;
			} else {
				if (bead == color || bead == 'w')
					count++;
				else
					break;
			}
		}

		return count;
	}

	private static int countBackward(String beads) {

		int count = 0;
		char color = ' ';
		char[] charbeads = beads.toCharArray();

		for (int i = charbeads.length - 1; i >= 0; i--) {
			char bead = charbeads[i];
			if (color == ' ') {
				if (bead == 'r')
					color = 'r';
				else if (bead == 'b')
					color = 'b';
				count++;
			} else {
				if (bead == color || bead == 'w')
					count++;
				else
					break;
			}
		}

		return count;
	}
}
