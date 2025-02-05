package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon13549 {

	static boolean[] visited;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		System.out.println(bfs());
	}

	private static int bfs() {
		if(N > K) {
			return N - K;
		}
		
		
		Deque<Integer> q = new ArrayDeque<>(); 
		visited = new boolean[100001];
		int result = -1;
		q.add(N);
		visited[N] = true;
		
		while(!q.isEmpty()) {
			
			result++;
			int size = q.size();
			
			while(size-- > 0) {
				int now = q.pollFirst();
				if(now == K) return result;
				
				if(now*2 < 100001 && !visited[now * 2]) {
					q.addFirst(now * 2);
					visited[now*2] = true;
					size++;
				}
				if(now-1 >= 0 && !visited[now-1]) {
					q.addLast(now-1);
					visited[now-1] = true;
				}
				if(now+1 < 100001 && !visited[now+1]) {
					q.addLast(now+1);
					visited[now+1] = true;
				}
			}

		}
		return -1;
	}
}