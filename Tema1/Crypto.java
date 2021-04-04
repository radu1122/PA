import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Computer implements Comparable {
	public int money = 0;
	public int price = 0;

	public Computer(int money, int price) {
		this.money = money;
		this.price = price;
	}

	@Override
	public int compareTo(Object o) {
		return this.money - ((Computer) o).money;
	}
}

public class Crypto {
	public static void main(String[] args) throws IOException {

		BufferedReader reader  = new BufferedReader(new FileReader("crypto.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);
		int b = Integer.parseInt(strLine[1]);


		List<Computer> computers = new ArrayList<>();

		int sumForOne = 0;
		for (int i = 0; i < n; i++) { // O(n)
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			int p = Integer.parseInt(strLine[0]);
			int u = Integer.parseInt(strLine[1]);
			computers.add(new Computer(p, u));
			sumForOne = sumForOne + u;
		}

		Collections.sort(computers); // O(n log n)

		int min = computers.get(0).money;
		int currPrice = computers.get(0).price;

		long buff;
		int i, diff;
		for (i = 1; i < n; i++) { // O(n)
			if (computers.get(i).money == min) {
				currPrice = currPrice + computers.get(i).price;
			} else {
				buff = ((long)computers.get(i).money - (long)min);
				if ((long)b - (long)(currPrice * buff) >= 0) {
					buff = currPrice * buff;
					b = b - (int)buff;
					currPrice = currPrice + computers.get(i).price;
					min = computers.get(i).money;

				} else {
					diff = b / currPrice;
					b = 0;
					min = min + diff;
					break;
				}
			}
		}

		if (min == computers.get(computers.size() - 1).money && b > 0) {
			diff = b / sumForOne;
			min = min + diff;
		}

		FileWriter myWriter = new FileWriter("crypto.out");
		myWriter.write(Integer.toString(min));
		myWriter.close();
	}
}
