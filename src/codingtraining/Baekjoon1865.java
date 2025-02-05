package codingtraining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1865 {

	// 벨만포드 알고리즘을 사용하여 음의 사이클을 체크하는 코드
	static int TC, N, M, W, S, E, T;
	static ArrayList<Edge> list;
	static boolean isCircular;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 0; t < TC; t++) {
			list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// 도로 입력 (양방향 간선 추가)
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken()); // 시작점
				E = Integer.parseInt(st.nextToken()); // 도착점
				T = Integer.parseInt(st.nextToken()); // 가중치
				list.add(new Edge(S, E, T));
				list.add(new Edge(E, S, T)); // 양방향 도로 처리
			}

			// 웜홀 입력 (가중치를 음수로 설정)
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken()); // 시작점
				E = Integer.parseInt(st.nextToken()); // 도착점
				T = Integer.parseInt(st.nextToken()); // 가중치
				list.add(new Edge(S, E, -T)); // 웜홀은 가중치 음수
			}

			// Bellman-Ford 알고리즘을 위한 초기화
			isCircular = false;  // 음수 사이클 체크 변수 초기화

			// 벨만포드 알고리즘 수행
			int[] dist = new int[N + 1]; // 노드 번호는 1부터 N까지
			Arrays.fill(dist, 100000001); // 모든 거리를 무한대로 초기화
			dist[1] = 0; // 시작점의 거리는 0으로 설정

			// N-1번 반복하여 최단 거리 갱신
			for (int i = 1; i < N; i++) {
				for (Edge edge : list) {
					if (dist[edge.start] + edge.weight < dist[edge.destination]) {
						dist[edge.destination] = dist[edge.start] + edge.weight;
					}
				}
			}

			// N번째 반복에서 갱신이 되면 음수 사이클이 있다는 뜻
			for (Edge edge : list) {
				if (dist[edge.start] + edge.weight < dist[edge.destination]) {
					isCircular = true;
					break;
				}
			}
			// 결과 출력
			if (isCircular) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
class Edge {
	int start;
	int destination;
	int weight;
	
	public Edge(int start, int destination, int weight) {
		this.start = start;
		this.destination = destination;
		this.weight = weight;
	}
}

