package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BfsExample {
	
	//백준 2178 BFS 예시 https://www.acmicpc.net/problem/2178

	static int N, M, map[][], count; // 각각 세로 가로 , 맵, count(횟수)
	static boolean visited[][]; // 좌표별 방문여부 판단 배열 (이거없으면 무환루프남)
	static int[] moveX = {1,-1,0,0}; // 상하좌우 움직임구현 X축
	static int[] moveY = {0,0,1,-1}; // 상하자우 움직임 구현 Y축

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			String line = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		} // 입력받은 맵 대입
		bfs();
		System.out.println(count);
		
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0}); // 시작점은 0,0 0번째 인덱스는 Y축 1번째 인덱스는 X축
		visited[0][0] = true; // 방문 처리
		
		while(!q.isEmpty()) {
			count++;
			int length = q.size();
			for(int i = 0 ; i < length ; i++) {
				int[] now = q.poll();
				if(now[0] == N-1 && now[1] == M-1) {
					return;
				}
				for(int j = 0 ; j < 4 ; j++) { // 상하좌우 움직임 0,1,2,3이 진행되며 자연스럽게 
					// {0,1} {0,-1} , {1,0} , {-1,0} 진행됨
					int nextY = now[0] + moveY[j];
					int nextX = now[1] + moveX[j];
					if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= M) continue; // index범위 유효성검사
					if(visited[nextY][nextX]) continue; // 방문한 Node인직 확인
					if(map[nextY][nextX] == 1) { 
						visited[nextY][nextX] = true;
						q.add(new int[] {nextY, nextX}); // 유효한 Node를 Queue에 추가 
					}
				}
			}
		}
		
	}
}
