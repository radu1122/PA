import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Adrese {
	public static void main(String[] args) throws IOException {
		BufferedReader reader  = new BufferedReader(new FileReader("adrese.in"));

		String line = reader.readLine();
		String[] strLine = line.trim().split("\\s+");
		int n = Integer.parseInt(strLine[0]);

		HashMap<String, Integer> mailToId = new HashMap<String, Integer>();
		HashMap<Integer, String> idToName = new HashMap<Integer, String>();


		for (int i = 0; i < n; i++) { // O(n)
			line = reader.readLine();
			strLine = line.trim().split("\\s+");

			String name = strLine[0];
			int k = Integer.parseInt(strLine[1]);

			int userId = -1;
			String[] mails = new String[k + 1];
			for (int j = 0; j < k; j++) {
				line = reader.readLine();
				strLine = line.trim().split("\\s+");
				mails[j] = strLine[0];

				if (mailToId.containsKey(mails[j])) {
					if (userId != -1) {
						int userId2 = mailToId.get(mails[j]);
						if (userId != userId2) {
							idToName.remove(userId);
							userId = -1;
						}

					}
					userId = mailToId.get(mails[j]);
				}
			}
			if (userId == -1) {
				userId = i;
				idToName.put(userId, name);
			} else {
				String oldName = idToName.get(userId);
				if (oldName == null) {
					oldName = "";
				}
				String newName = "";
				if (oldName.compareTo(name) < 0) {
					newName = oldName;
				} else {
					newName = name;
				}
				idToName.put(userId, newName);
			}

			for (int j = 0; j < k; j++) {
				mailToId.put(mails[j], userId);
			}
		}
		FileWriter myWriter = new FileWriter("adrese.out");
		myWriter.write(Integer.toString(idToName.size()));
		myWriter.close();
	}
}