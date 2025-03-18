package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon12865 {

	static int N, K, V, dp[]; // 각각 물건의 개수 물건의 무게 가치
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1]; // 무게별 최대 가치
		
		for(int i = 0 ; i < N  ;i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			for(int j = K ; j >= W ; j--) {
				dp[j] = Math.max(dp[j], dp[j-W] + V);
			}
			
		}
		
		System.out.println(dp[K]);
	}
}
