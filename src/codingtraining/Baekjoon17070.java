package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17070 {

	static int status, N; // 0이면 가로 1이면 세로 2면 대각
	static int result;
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N  ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) {
					map[i][j] = 1;
				}
			}
		}

		deeptracking(0,1,0);
		System.out.println(result);
	}

	private static void deeptracking(int y, int x, int status) {
		if (y == N - 1 && x == N - 1) {
			result++;
			return;
		}

		switch (status) {
		case 0:
			if (x + 1 < N && map[y][x + 1] == 0) {
				deeptracking(y, x + 1, 0);
			}
			if (x + 1 < N && y + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0) {
				deeptracking(y + 1, x + 1, 2);
			}
			break;

		case 1:
			if (y + 1 < N && map[y + 1][x] == 0) {
				deeptracking(y + 1, x, 1);
			}
			if (x + 1 < N && y + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0) {
				deeptracking(y + 1, x + 1, 2);
			}
			break;
		case 2:
			if (x + 1 < N && map[y][x + 1] == 0) {
				deeptracking(y, x + 1, 0);
			}
			if (y + 1 < N && map[y + 1][x] == 0) {
				deeptracking(y + 1, x, 1);
			}
			if (x + 1 < N && y + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0) {
				deeptracking(y + 1, x + 1, 2);
			}
			break;
		}
	}
}