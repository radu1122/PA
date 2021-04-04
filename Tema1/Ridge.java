import java.io.*;

public class Ridge {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("ridge.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);

		int[] mountains = new int[n];
		int[] costs = new int[n];


		for (int i = 0; i < n; i++) { // O(n)
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			mountains[i] = Integer.parseInt(strLine[0]);
			costs[i] = Integer.parseInt(strLine[1]);
		}

		long[][] dp = new long[n][3];


		dp[0][0] = 0;
		dp[0][1] = costs[0];
		dp[0][2] = 2 * costs[0];

		if (mountains[0] - 1 < 0) {
			dp[0][1] = Long.MAX_VALUE;
			dp[0][2] = Long.MAX_VALUE;
		}

		if (mountains[0] - 2 < 0) {
			dp[0][2] = Long.MAX_VALUE;
		}
		
		for(int i = 1; i < n; i++) { // O(n)
			dp[i][0] = 0;
			dp[i][1] = costs[i];
			dp[i][2] = 2 * costs[i];
			
			if (mountains[i] == mountains[i - 1]) {
				dp[i][0] = Long.min(dp[i - 1][1], dp[i - 1][2]);
			} else if (mountains[i] == mountains[i - 1] - 1) {
				dp[i][0] = Long.min(dp[i - 1][0], dp[i - 1][2]);
			} else if (mountains[i] == mountains[i - 1] - 2) {
				dp[i][0] = Long.min(dp[i - 1][0], dp[i - 1][1]);
			} else {
				dp[i][0] = Long.min(Long.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
			}

			if (mountains[i] - 1 == mountains[i - 1]) {
				dp[i][1] = Long.min(dp[i - 1][1], dp[i - 1][2]) + dp[i][1];
			} else if (mountains[i] - 1 == mountains[i - 1] - 1) {
				dp[i][1] = Long.min(dp[i - 1][0], dp[i - 1][2]) + dp[i][1];
			} else if (mountains[i] - 1 == mountains[i - 1] - 2) {
				dp[i][1] = Long.min(dp[i - 1][0], dp[i - 1][1]) + dp[i][1];
			} else {
				dp[i][1] = Long.min(Long.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + dp[i][1];
			}

			if (mountains[i] - 2 == mountains[i - 1]) {
				dp[i][2] = Long.min(dp[i - 1][1], dp[i - 1][2]) + dp[i][2];
			} else if (mountains[i] - 2 == mountains[i - 1] - 1) {
				dp[i][2] = Long.min(dp[i - 1][0], dp[i - 1][2]) + dp[i][2];
			} else if (mountains[i] - 2 == mountains[i - 1] - 2) {
				dp[i][2] = Long.min(dp[i - 1][0], dp[i - 1][1]) + dp[i][2];
			} else {
				dp[i][2] = Long.min(Long.min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + dp[i][2];
			}

			if (mountains[i] - 1 < 0) {
				dp[i][1] = Long.MAX_VALUE;
				dp[i][2] = Long.MAX_VALUE;
				continue;
			}

			if (mountains[i] - 2 < 0) {
				dp[i][2] = Long.MAX_VALUE;
				continue;
			}
		}

		FileWriter myWriter = new FileWriter("ridge.out");
		myWriter.write(Long.toString(Long.min(Long.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2])));
		myWriter.close();
	}
}