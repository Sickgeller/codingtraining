package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon13164 {
	
	static int N, K; 
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> tallList = new ArrayList<Integer>();
		
		for(int i = 0 ; i < N - 1  ; i++) {
			tallList.add(Math.abs(list.get(i)-list.get(i+1)));
		}

		Collections.sort(tallList);
		
		int result = 0 ;
		
		for(int i = 0 ; i < N-K ; i ++) {
			result += tallList.get(i);
		}
		
		System.out.println(result);
	}
}