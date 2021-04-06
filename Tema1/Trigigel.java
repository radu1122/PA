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
			if (count % 1000000007 == 0) {
				count = 0;
			}
			
			if (i % 3 == 1) {
				count0++;
				count0 += count2;
				count++;
				count = count + count2;
			} else if (i % 3 == 2) {
				count1++;
				count1 += count0;
				count++;
				count = count + count0;
			} else {
				count2++;
				count2 += count1;
				count++;
				count = count + count1;
			}

			if (count % 1000000007 == 0) {
				count = 0;
			}
			
		}

		FileWriter myWriter = new FileWriter("trigigel.out");
		myWriter.write(Long.toString(count % 1000000007));
		myWriter.close();
	}
}