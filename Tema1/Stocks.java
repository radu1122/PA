import java.io.*;
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


		for (int i = 1; i <= n; i++) {
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			currentValues[i] = Integer.parseInt(strLine[0]);
			minValues[i] = Integer.parseInt(strLine[1]);
			maxValues[i] = Integer.parseInt(strLine[2]);
		}


			// dp este o matrice de dimensiune (n + 1)x(b+1)
		// pentru ca folosim dp[0][*] pentru multimea vida
		//                   dp[*][0] pentru situatia in care ghiozdanul are capacitate 0
		int[][][] dp = new int [2][b + 1][l + 1];

		// cazul de baza
		// for (int cap = 0; cap <= b; ++cap) {
		//     dp[0][cap] = 0;
		// }

		// cazul general
		for (int i = 1; i <= n; ++i) {
			for (int cap = 0; cap <= b; ++cap) {
				// nu folosesc obiectu i => e solutia de la pasul i - 1
				for (int loss = 0; loss <= l; loss++) {
					dp[1][cap][loss] = dp[0][cap][loss];
					int tempLoss = minValues[i] - currentValues[i];
					System.out.println(Math.min(loss,tempLoss));
					// folosesc obiectul i, deci trebuie sa rezerv w[i] unitati in rucsac
					// inseamna ca inainte trebuie sa ocup maxim cap - w[i] unitati
					if (cap - currentValues[i] >= 0) {
						int sol_aux = dp[1][cap - currentValues[i]][Math.min(loss, loss)] + maxValues[i];
						
						if (tempLoss < 0) {
							// if (loss - (minValues[i] - currentValues[i]) <= loss) {
								dp[1][cap][loss] = Math.max(dp[1][cap][loss], sol_aux);

							// }
						} else {
							dp[1][cap][loss] = Math.max(dp[1][cap][loss], sol_aux);
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

		// System.out.println(dp[1][b]); 

		FileWriter myWriter = new FileWriter("stocks.out");
		myWriter.write(Integer.toString(dp[1][b][l]));
		myWriter.close();

	}
}