package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon12100 {
	
	static int N,map[][], result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//초기화 끝
		game(map, 0);
	}
	
	private static void game(int[][] map, int count) {
		// 0번째줄 N-1번째줄 (위아래) 
		// 맨 왼쪽줄 맨 오른쪽줄 정렬 -> 같은수 합치고 
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				
			}
		}
	}
}
