import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Poduri {
	static class Node  {
		int x;
		int y;
		int min;
	
		public Node(int x, int y, int min) {
			this.x = x;
			this.y = y;
			this.min = min;
		}
	};

	public static int searchFn(int n, int m, char[][] matrix, boolean[][] visited, int x, int y) {
		visited[x][y] = true;

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(x, y, 0));

		while (!queue.isEmpty()) {
			Node curr = queue.peek();
			if (curr.x == 0 || curr.x == n + 1 || curr.y == 0 || curr.y == m + 1) {
				return curr.min;
			}

			queue.remove();

			x = curr.x;
			y = curr.y;

			if (matrix[x][y] == 'V' || matrix[x][y] == 'D') {
				if (matrix[x - 1][y] != '.' && !visited[x - 1][y]) {
					visited[x - 1][y] = true;
					if (x == 1) {
						return curr.min + 1;
					}
					queue.add(new Node(x - 1, y, curr.min + 1));
				}
				if (matrix[x + 1][y] != '.' && !visited[x + 1][y]) {
					visited[x + 1][y] = true;
					if (x == n) {
						return curr.min + 1;
					}
					queue.add(new Node(x + 1, y, curr.min + 1));
				}
			}

			if (matrix[x][y] == 'O' || matrix[x][y] == 'D') {
				if (matrix[x][y - 1] != '.' && !visited[x][y - 1]) {
					visited[x][y - 1] = true;
					if (y == 1) {
						return curr.min + 1;
					}
					queue.add(new Node(x, y - 1, curr.min + 1));
				}
				if (matrix[x][y + 1] != '.' && !visited[x][y + 1]) {
					visited[x][y + 1] = true;
					if (y == m) {
						return curr.min + 1;
					}
					queue.add(new Node(x, y + 1, curr.min + 1));
				}
			}
		}

		return -1;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("poduri.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);
		int m = Integer.parseInt(strLine[1]);

		line = reader.readLine();
		strLine = line.trim().split("\\s+");
		int x = Integer.parseInt(strLine[0]);
		int y = Integer.parseInt(strLine[1]);

		char[][] matrix = new char[n + 2][m + 2];
		boolean[][] visited = new boolean[n + 2][m + 2];

		for (int i = 1; i <= n; i++) { // O(n)
			line = "X" + reader.readLine() + "X";

			matrix[i] = Arrays.copyOf(line.toCharArray(), m + 2);

		}

		for (int j = 0; j <= m + 1; j++) {
			matrix[0][j] = 'X';
			matrix[n + 1][j] = 'X';
		}

		int min = searchFn(n, m, matrix, visited, x, y);
		
		FileWriter myWriter = new FileWriter("poduri.out");
		myWriter.write(Integer.toString(min));
		myWriter.close();

	}
}