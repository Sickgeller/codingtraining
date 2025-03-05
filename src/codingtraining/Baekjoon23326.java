package codingtraining;

import java.util.Scanner;
import java.util.TreeSet;

public class Baekjoon23326 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N: 구역의 개수, Q: 쿼리의 개수
		int N = sc.nextInt();
		int Q = sc.nextInt();

		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		TreeSet<Integer> places = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			if (A[i] == 1) {
				places.add(i); //
			}
		}

		int currentPosition = 0;

		for (int i = 0; i < Q; i++) {
			String query = sc.next();

			if (query.equals("1")) {
				int index = sc.nextInt() - 1; 
				if (A[index] == 1) {
					A[index] = 0;
					places.remove(index);
				} else {
					A[index] = 1;
					places.add(index); 
				}
			} else if (query.equals("2")) {
				int x = sc.nextInt();
				currentPosition = (currentPosition + x) % N; 
			} else if (query.equals("3")) {
				if (places.isEmpty()) {
					System.out.println(-1); // 명소가 없다면 -1 출력
				} else {
					Integer nextPlace = places.ceiling(currentPosition);
					if (nextPlace == null) {
						nextPlace = places.first();
					}
					
					int minDistance = (nextPlace - currentPosition + N) % N;
					System.out.println(minDistance);
				}
			}
		}
	}
}
