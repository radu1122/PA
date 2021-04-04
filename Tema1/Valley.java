import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Valley {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("valley.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);

		int[] mountains = new int[n];

		line = reader.readLine();
		strLine = line.trim().split("\\s+");
		
		for (int i = 0; i < n; i++) { // O(n)
			mountains[i] = Integer.parseInt(strLine[i]);
		}

		long[] sumLeft = new long[n];
		long[] sumRight = new long[n];
		long[] currCostLeft = new long[n];
		long[] currCostRight = new long[n];


		sumLeft[0] = 0;
		int min = mountains[0];
		for (int i = 1; i < n; i++) { // O(n)
			if (mountains[i] > min) {
				sumLeft[i] = sumLeft[i - 1] + (long )(mountains[i] - min);
				currCostLeft[i] = (long )(mountains[i] - min);
			} else {
				sumLeft[i] = sumLeft[i - 1];
				currCostLeft[i] = 0;
			}

			if (mountains[i] < min) {
				min = mountains[i];
			}
		}

		sumRight[n - 1] = 0;
		min = mountains[n - 1];
		for (int i = n - 2; i >= 0; i--) { // O(n)
			if (mountains[i] > min) {
				sumRight[i] = sumRight[i + 1] + (long )(mountains[i] - min);
				currCostRight[i] = (long )(mountains[i] - min);

			} else {
				sumRight[i] = sumRight[i + 1];
				currCostRight[i] = 0;

			}

			if (mountains[i] < min) {
				min = mountains[i];
			}
		}

		long minX = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) { // O(n)
			long buff = sumLeft[i] + sumRight[i] - currCostRight[i] - currCostLeft[i]
					+ Math.max(currCostRight[i], currCostLeft[i]);
			if (buff < minX && i != 0 && i != n - 1) {
				minX = buff;
			}
		}

		FileWriter myWriter = new FileWriter("valley.out");
		myWriter.write(Long.toString(minX));
		myWriter.close();


	}
}