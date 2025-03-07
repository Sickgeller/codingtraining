package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16928 {

	static int N, M, result;
	static Map<Integer, Integer> ladderNSnake = new HashMap<>();
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사다리
		M = Integer.parseInt(st.nextToken()); // 뱀

		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			ladderNSnake.put(start, dest);
		}// 사다리

		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			ladderNSnake.put(start, dest);
		} // 뱀

		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int K = 0 ; K < size ; K++) {
				int point = q.poll();
				if(point == 100) return;

				for(int i = 6 ; i > 0 ; i--) {
					int next = point + i;
					if(next > 100) continue;

					if(!visited[next]) {
						visited[next] = true; // 다음좌표는 일단 방문처리
						
						if(ladderNSnake.containsKey(next)) { // 뱀이나 사다리
							int mapN = ladderNSnake.get(next);
							if(!visited[mapN]) {
								visited[mapN] = true;
								q.add(mapN);
							}
						}else { // 뱀 사다리 없을때
							q.add(next);
							
						}
					}
				}
			}
			result++;
		}
	}

}
