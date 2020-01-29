package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import entities.VoteRecords;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Map<VoteRecords, Integer> votesCount = new LinkedHashMap<>();

		System.out.print("Enter file full path: ");
		String path = sc.nextLine(); // D:\\ws-Github\\vote_count\\VoteRecords.txt

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");
				String name = fields[0];
				int count = Integer.parseInt(fields[1]);

				VoteRecords vr = new VoteRecords(name, count);

				if (votesCount.containsKey(vr)) {
					int votesSoFar = votesCount.get(vr);
					votesCount.put(vr, count + votesSoFar);
				} else {
					votesCount.put(vr, count);
				}

				line = br.readLine();
			}

			System.out.println();
			for (VoteRecords key : votesCount.keySet()) {
				System.out.println(key + ": " + votesCount.get(key) + " votes");
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
