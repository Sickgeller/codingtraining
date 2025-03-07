package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon11404 {
	
	static int N, M;
	static ArrayList<ArrayList<Node>> list = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i <= N ; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for(int i = 0 ; i < M ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(start).add(new Node(dest, weight));
		}// 입력된 간선 적용
		
		for(int i = 1 ; i <= N ; i++) { // for문으로 다익스트라 돌려서 구현
			int[] arr = dijkstra(i);
			for(int j = 1 ; j <= N ; j++) {
				int tmp = arr[j];
				if(tmp != Integer.MAX_VALUE) System.out.print(tmp + " ");
				else System.out.print(0 + " ");
			}
			System.out.println();
		}
	}

	private static int[] dijkstra(int start) {
		
		PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> o1.weight - o2.weight);
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		boolean[] visited = new boolean[N+1];
		q.add(new Node(start,0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(visited[now.index]) continue;
			visited[now.index] = true;
			for(Node next : list.get(now.index)) {
				if(!visited[next.index] && dist[next.index] > dist[now.index] + next.weight) {
					dist[next.index] = dist[now.index] + next.weight;
					q.add(new Node(next.index , dist[next.index]));
				}
			}
		}
		return dist;
	}
}
class Node{
	
	int index;
	int weight;
	public Node(int index, int weight) {
		super();
		this.index = index;
		this.weight = weight;
	}
}