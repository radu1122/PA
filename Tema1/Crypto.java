import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

		String line = null;
		BufferedReader fileReader = new BufferedReader(new FileReader("crypto.in"));

		line = fileReader.readLine();
		Scanner scanner = new Scanner(line);

		int n = scanner.nextInt();
		int b = scanner.nextInt();

		List<Computer> computers = new ArrayList<>();

		int sumForOne = 0;
		for (int i = 0; i < n; i++) {
			line = fileReader.readLine();
			scanner = new Scanner(line);

			int p = scanner.nextInt();
			int u = scanner.nextInt();
			computers.add(new Computer(p, u));
			sumForOne = sumForOne + u;
		}

		Collections.sort(computers); // O(n log n)
//		System.out.println(computers);

		int min = computers.get(0).money;
		int currPrice = computers.get(0).price;

		long buff;
		int i, diff;
		for (i = 1; i < n; i++) { // O(n)
			if (computers.get(i).money == min) {
				currPrice = currPrice + computers.get(i).price;
			} else {
				buff = ((long)computers.get(i).money - (long)min);
				if ((long)b - (long)(currPrice * buff)  >= 0) {
					buff = currPrice * buff;
					b = b - (int)buff;
					currPrice = currPrice + computers.get(i).price;
					min = computers.get(i).money;

				} else {
					diff = b / currPrice;
					b = 0;
					min = min + diff;
//					System.out.println(i + "----" + currPrice + " - " + b + " - " + min + "\n");

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
