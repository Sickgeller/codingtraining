package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon11725 {

	static int N; /// 노드의 개수
	//union find 비틀어서 풀어보고싶음 -> 안될듯

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

		for(int i = 0 ; i <= N+1 ; i++) {
			tree.add(new ArrayList());
		}

		for(int i = 0 ; i < N-1 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a); // 트리 구현 완
		}

		int num[] = new int[N+1];
		boolean[] visited = new boolean[N+1];

		Queue<Integer> q = new LinkedList<>();

		//인접리스트 bfs
		q.add(1);
		visited[1] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			List<Integer> list = tree.get(now);
			for(int next : list) {
				if(visited[next]) continue;
				else {
					q.add(next);
					visited[next] = true;
					num[next] = now;
				}
			}
		}


		for(int i = 2 ; i <= N ; i++) {
			System.out.println(num[i]);
		}
	}	
	// 1이면 부모노드를 그냥 1로 바꾸고 진행
	// 다른거는 1이랑 떨어진거리 -> 구현이 관건
	// 재귀는 수행시간이 너무 길어짐
	// dp로 때려잡아볼수있을거같음 -> 순서보장이 되지않기때문에 정밀하지않음 그냥 풀던걸로 풀기
	// 시간복잡도 3N
}