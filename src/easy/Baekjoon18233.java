package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon18233{

	static int N,P,E;
	static List<int[]> numList = new ArrayList<>();
	static boolean[] used;
	static int usedNum;
	static boolean flag;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // 총원
    	P = Integer.parseInt(st.nextToken()); // 명의사람에게
    	E = Integer.parseInt(st.nextToken()); // 개의 인형전달
    	
    	used = new boolean[N];
    	
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			numList.add(new int[] {start,end-start}); // 0은 최소값 1은 가중치 범위
		}
		deeptracking(0);
		
		if(!flag) System.out.println(-1);
		//최소값만 더해두고 가중치로 잘라내서 구하는 방식
	}

	private static void deeptracking(int index) {
		
		if(usedNum == P) {
			// 로직시작
			int minSum = 0;
			int plusSum = 0;
			
			for(int i = 0 ; i < N ; i++) {
				if(used[i]) {
					minSum += numList.get(i)[0];
					plusSum += numList.get(i)[1];
				}
			}
			
			if(minSum <= E && minSum + plusSum >= E) {
				flag = true;
				printResult(minSum, plusSum);
				System.exit(0);
			}
			
			return;
		}
		
		for(int i = index ; i < N ; i++) {
			if(used[i]) continue;
			used[i] = true;
			usedNum++;
			deeptracking(i+1);
			used[i] = false;
			usedNum--;
		}
	}

	private static void printResult(int minSum, int plusSum) {
	    int[] numArr = new int[N];
	    int remain = E - minSum;

	    for (int i = 0; i < N; i++) {
	        if (!used[i]) continue;
	        numArr[i] = numList.get(i)[0]; // 최소값 먼저 할당
	    }

	    for (int i = 0; i < N; i++) {
	        if (!used[i]) continue;
	        int addable = Math.min(numList.get(i)[1], remain);
	        numArr[i] += addable;
	        remain -= addable;
	        if (remain == 0) break;
	    }

	    for (int num : numArr) {
	        System.out.print(num + " ");
	    }
	}

}

