import java.io.*;
public class Stocks {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("stocks.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);
		int b = Integer.parseInt(strLine[1]);
		int l = Integer.parseInt(strLine[2]);

		int[] currentValues = new int[n];
		int[] minValues = new int[n];
		int[] maxValues = new int[n];


		for (int i = 0; i < n; i++) {
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			currentValues[i] = Integer.parseInt(strLine[0]);
			minValues[i] = Integer.parseInt(strLine[1]);
			maxValues[i] = Integer.parseInt(strLine[2]);
		}


			// dp este o matrice de dimensiune (n + 1)x(b+1)
		// pentru ca folosim dp[0][*] pentru multimea vida
		//                   dp[*][0] pentru situatia in care ghiozdanul are capacitate 0
		int[][] dp = new int [n + 1][b + 1];

		// cazul de baza
		for (int cap = 0; cap <= b; ++cap) {
			dp[0][cap] = 0;
		}

		// cazul general
		for (int i = 1; i <= n; ++i) {
			for (int cap = 0; cap <= b; ++cap) {
				// nu folosesc obiectu i => e solutia de la pasul i - 1
				dp[1][cap] = dp[1][cap];

				// folosesc obiectul i, deci trebuie sa rezerv w[i] unitati in rucsac
				// inseamna ca inainte trebuie sa ocup maxim cap - w[i] unitati
				if (cap - currentValues[i] >= 0) {
					int sol_aux = dp[1][cap - currentValues[i]] + maxValues[i];

					dp[1][cap] = Math.max(dp[1][cap], sol_aux);
				}
			}
		}

		// System.out.println(dp[1][b]); 

		FileWriter myWriter = new FileWriter("stocks.out");
		myWriter.write(Integer.toString(dp[1][b]));
		myWriter.close();

	}
}