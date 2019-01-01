
/*
ID: helena.6
LANG: JAVA
TASK: friday
*/
import java.io.*;

public class friday {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

		int finalYear = 1899 + Integer.parseInt(f.readLine());
		int currentYear = 1900;
		boolean leapYear = false;
		int[] thirteen = new int[7];
		int firstDay = 2; // 0 is saturday, 1 is sunday, 2 is monday, etc.

		while (currentYear <= finalYear) {
			leapYear = getLeapYear(leapYear, currentYear);
			for (int month = 1; month <= 12; month++) {
				thirteen[(firstDay + 5) % 7]++;
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					firstDay = nextFirstDay(firstDay, 30);
				} else if (month == 2) {
					if (leapYear)
						firstDay = nextFirstDay(firstDay, 29);
					else
						firstDay = nextFirstDay(firstDay, 28);
				} else {
					firstDay = nextFirstDay(firstDay, 31);
				}
			}
			leapYear = false;
			currentYear++;
		}

		StringBuilder output = new StringBuilder();

		for (int i = 0; i < 7; i++) {
			output = output.append(thirteen[i]).append(" ");
		}

		out.println(output.toString().trim());
		out.close();
	}

	private static int nextFirstDay(int firstDay, int daysInMonth) {
		daysInMonth = daysInMonth % 7;
		firstDay = (firstDay + daysInMonth) % 7;
		
		return firstDay;
	}

	private static boolean getLeapYear(boolean leapYear, int currentYear) {
		if (currentYear % 100 == 0) {
			if (currentYear % 400 == 0)
				leapYear = true;
		} else if (currentYear % 4 == 0) {
			leapYear = true;
		}
		
		return leapYear;
	}
}
