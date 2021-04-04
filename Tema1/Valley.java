import java.io.*;

public class Valley {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("valley.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
        int n = Integer.parseInt(strLine[0]);

        int[] mountains = new int[n];

        line = reader.readLine();
        strLine = line.trim().split("\\s+");
        
        for (int i = 0; i < n; i++) {
			mountains[i] = Integer.parseInt(strLine[i]);
		}

        long sumLeft[] = new long[n];
        long sumRight[] = new long[n];


        int min = 0;
        sumLeft[0] = 1;
        for (int i = 1; i < n; i++) {
            if (mountains[i] > mountains[i - 1]) {
                sumLeft[i] = sumLeft[i - 1] + (long )(mountains[i] - mountains[i - 1]);
            } else {
                sumLeft[i] = sumLeft[i - 1];
            }
        }
    }
}