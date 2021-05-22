import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Trigigel {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("trigigel.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		long n = Long.parseLong(strLine[0]);

		long count = 0;
		long count0 = 0;
		long count1 = 0;
		long count2 = 0;

		for (long i = 1; i <= n; i++) {
			count = count % 1000000007;
			
			if (i % 3 == 1) {
				count0++;
				count0 += count2;
				count0 = count0 % 1000000007;
				count++;
				count = count + count2;
				count = count % 1000000007;
<<<<<<< HEAD
			} else if (i % 3 == 2) {
=======
 			} else if (i % 3 == 2) {
>>>>>>> 94a926fc5f1da0b81c935f03091b89257dcbd92f
				count1++;
				count1 += count0;
				count1 = count1 % 1000000007;
				count++;
				count = count + count0;
				count = count % 1000000007;
			} else {
				count2++;
				count2 += count1;
				count2 = count2 % 1000000007;
				count++;
				count = count + count1;
				count = count % 1000000007;
			}
			count = count % 1000000007;
<<<<<<< HEAD


			
=======
>>>>>>> 94a926fc5f1da0b81c935f03091b89257dcbd92f
		}



		FileWriter myWriter = new FileWriter("trigigel.out");
		myWriter.write(Long.toString(count % 1000000007));
		myWriter.close();
	}
}