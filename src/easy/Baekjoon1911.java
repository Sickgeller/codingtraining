package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1911 {

	static int N,L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.start - o2.start);

		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		int lastP = 0;
		int result = 0;

		while(!pq.isEmpty()) {

			Node now = pq.poll();
			if(now.start > lastP) { // 시작점이 마지막 판떄기보다 뒤에있을때(새로까는경우)
				lastP = now.start + L;
				result++;
			}
			
			while(lastP < now.end) { // 종료지점까지 판때기가 안닿은경우(계속 더 까는경우)
				lastP = lastP+L;
				result++;
			}
			
		}
		
		System.out.println(result);
	}

}
class Node{
	int start,end;
	public Node(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
}
