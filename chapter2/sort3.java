
/*
ID: helena.6
LANG: JAVA
TASK: sort3
*/

import java.io.*;

public class sort3 {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		n = Integer.parseInt(f.readLine());
		int[] count = new int[4]; // 1 based to count how many 1's, 2's, and 3's there are using index
		int[] nums = new int[n];

		for (int i = 0; i < n; i++) {
			int key = Integer.parseInt(f.readLine());
			nums[i] = key;
			count[key]++;
		}

		int swaps = 0;

		for (int i = 1; i <= 3; i++) {
			swaps = findOptimalSwaps(i, swaps, nums, count); // first do all optimal swaps
		}

		swaps = findNonOptimalSwaps(swaps, nums, count); // then do all non optimal swaps. fill one first
		out.println(swaps);
		out.close();
	}

	private static int findNonOptimalSwaps(int swaps, int[] nums, int[] count) {
		int misplaced = 0; // all the elements left are misplaced in cycles
		for (int k = 0; k < count[1]; k++) {
			if (nums[k] != 1)
				misplaced++;
		}
		for (int k = count[1]; k < count[1] + count[2]; k++) {
			if (nums[k] != 2)
				misplaced++;
		}
		for (int k = count[1] + count[2]; k < n; k++) {
			if (nums[k] != 3)
				misplaced++;
		}
		swaps = swaps + misplaced / 3 * 2;
		return swaps;
	}

	private static int findOptimalSwaps(int i, int swaps, int[] nums, int[] count) {
		if (i == 1) {// section 1 vs section 2
			for (int k = 0; k < count[1]; k++) {
				for (int j = count[1]; j < count[1] + count[2]; j++) {
					if (nums[j] == 1 && nums[k] == 2) {
						nums = swap(nums, k, j);
						swaps++;

					}
				}
			}
		} else if (i == 2) {// section 1 vs section 3
			for (int k = 0; k < count[1]; k++) {
				for (int j = count[1] + count[2]; j < n; j++) {
					if (nums[j] == 1 && nums[k] == 3) {
						nums = swap(nums, k, j);
						swaps++;
					}
				}
			}
		} else if (i == 3) {// section 2 vs section 3
			for (int k = count[1]; k < count[1] + count[2]; k++) {
				for (int j = count[1] + count[2]; j < n; j++) {
					if (nums[j] == 2 && nums[k] == 3) {
						nums = swap(nums, k, j);
						swaps++;
					}
				}
			}
		}
		return swaps;
	}

	private static int[] swap(int[] nums, int k, int j) {
		int t = nums[k];
		nums[k] = nums[j];
		nums[j] = t;
		return nums;
	}
}
