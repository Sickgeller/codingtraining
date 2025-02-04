package codingtraining.graphsearch.bellman_ford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon11657 {

	static int N,M;
	static List<Edge> list;
	static boolean cycle;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		if(N == 1) {
			return;
		}
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Edge>();
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Edge(start,dest,weight));
		}
		long dist[] = new long[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		for(int i = 1 ; i < N ; i++) {
			for(Edge edge : list) {
				if(dist[edge.start] == Integer.MAX_VALUE) continue;
				if(dist[edge.dest] > dist[edge.start] + edge.weight) {
					dist[edge.dest] = dist[edge.start] + edge.weight;
				}
			}
		}
		for(Edge edge : list) {
			if(dist[edge.start] == Integer.MAX_VALUE) continue;
			if(dist[edge.dest] > dist[edge.start] + edge.weight) {
				cycle = true;
			}
		}
		if(cycle) {
			System.out.println(-1);
		}
		else{
			for(int i = 2 ; i <= N ; i++) {
				if(dist[i] == Integer.MAX_VALUE) System.out.println(-1);
				else System.out.println(dist[i]);
			}
		}
	}
}
class Edge{
	int start;
	int dest;
	int weight;
	public Edge(int start, int dest, int weight) {
		super();
		this.start = start;
		this.dest = dest;
		this.weight = weight;
	}
}