package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon5430 {


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Node> list = new ArrayList<>();

		StringTokenizer st;
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			list.add(new Node(start,dest));
		}

		Collections.sort(list);

		int pos = 0;
		int count = 0;

		Iterator<Node> it = list.iterator();
		while(it.hasNext()) {
			Node now = it.next();
			if(pos <= now.start) {
				pos = now.dest;
				count++;
			}else {
				continue;
			}
		}
		System.out.println(count);
	}
}

class Node implements Comparable<Node>{
	int start;
	int dest;
	@Override
	public int compareTo(Node o) {
		if(this.dest == o.dest) return this.start - o.start;  
		return this.dest - o.dest; // 시작점 ~ 다음 출발점 가장 적은경우부터 정렬
	}
	public Node(int start, int dest) {
		super();
		this.start = start;
		this.dest = dest;
	}
}
