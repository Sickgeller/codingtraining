package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7576 {

	static int N,M, box[][], result;
	static List<int[]> list = new ArrayList<>();
	static int[] moveY = {1,-1,0,0};
	static int[] moveX = {0,0,1,-1};
	static boolean[][] visited;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		visited = new boolean[N][M];

		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				int tmp = Integer.parseInt(st.nextToken());	
				box[i][j] = tmp;	
				if(tmp == 1) {
					list.add(new int[] {i,j});
				}
			}
		}
		bfs();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(box[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(result-1);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for(int[] pos : list) {
			q.add(pos);
			visited[pos[0]][pos[1]] = true;
		}

		while(!q.isEmpty()) {
			int size = q.size();
			result++;
			for(int K  = 0 ; K < size ; K++) {
				int[] now = q.poll();
				for(int i = 0 ; i < 4 ; i++) {
					int nextY = now[0] + moveY[i];
					int nextX = now[1] + moveX[i];
					if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) continue;
					if(!visited[nextY][nextX]) {
						if(box[nextY][nextX] == 0) {
							box[nextY][nextX] = 1;
							q.add(new int[] {nextY, nextX});
						}
						visited[nextY][nextX] = true;
					}
				}
			}
		}
	}
}