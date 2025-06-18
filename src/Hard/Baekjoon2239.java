package Hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2239 {

	static boolean[][] visited;
	static final int[][] board = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		visited = new boolean[9][9];
		for(int i = 0 ; i < 9 ; i++) {
			String inputMap = br.readLine();
			for(int j = 0 ; j < 9 ; j++) {
				board[i][j] = inputMap.charAt(j)-'0';
			}
		}// Map 초기화 끝
		solve(0, 0);
	}

	// 스도쿠 풀이 함수 (백트래킹)
	static void solve(int row, int col) {
		if (col == 9) {
			solve(row + 1, 0);
			return;
		}

		if (row == 9) {
			printBoard();
			System.exit(0);  // 정답 하나만 출력하고 종료
		}

		if (board[row][col] == 0) {
			for (int num = 1; num <= 9; num++) {
				if (isValid(row, col, num)) {
					board[row][col] = num;
					solve(row, col + 1);
					board[row][col] = 0;  // 백트래킹
				}
			}
		} else {
			solve(row, col + 1);
		}
	}

	// 현재 위치에 num이 들어갈 수 있는지 체크
	static boolean isValid(int row, int col, int num) {
		// 행 검사
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num) return false;
		}

		// 열 검사
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == num) return false;
		}

		// 3x3 사각형 검사
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (board[i][j] == num) return false;
			}
		}

		return true;
	}

	// 스도쿠 출력
	static void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}




