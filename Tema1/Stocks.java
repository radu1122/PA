import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stocks {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("stocks.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);
		int b = Integer.parseInt(strLine[1]);
		int l = Integer.parseInt(strLine[2]);

		int[] currentValues = new int[n + 1];
		int[] minValues = new int[n + 1];
		int[] maxValues = new int[n + 1];


		for (int i = 1; i <= n; i++) { // O(n)
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			currentValues[i] = Integer.parseInt(strLine[0]);
			minValues[i] = Integer.parseInt(strLine[1]);
			maxValues[i] = Integer.parseInt(strLine[2]);
		}

		int[][][] dp = new int [n + 1][b + 1][l + 1];

		for (int i = 1; i <= n; ++i) {
			for (int cap = 0; cap <= b; ++cap) {
				for (int loss = 0; loss <= l; loss++) {
					dp[i][cap][loss] = dp[i - 1][cap][loss];
					int tempLoss = minValues[i] - currentValues[i];
					if (cap - currentValues[i] >= 0) {
						int sol_aux = dp[i][cap - currentValues[i]][loss]
									+ maxValues[i];
						
						if (tempLoss < 0) {
							if (loss - (minValues[i] - currentValues[i]) <= loss) {
								dp[i][cap][loss] = Math.max(dp[i][cap][loss], sol_aux);
							}
						} else {
							dp[i][cap][loss] = Math.max(dp[i][cap][loss], sol_aux);
						}
					}
				}

			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < b + 1; j++) {
				for (int k = 1; k < l + 1; k++) {
					System.out.println("INDICI:" + i + " " + j + " " + k + " VAL: " + dp[i][j][k]);
				}
			}
			System.out.println();

		}

		FileWriter myWriter = new FileWriter("stocks.out");
		myWriter.write(Integer.toString(dp[1][b][l]));
		myWriter.close();

	}
}