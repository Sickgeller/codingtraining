package Hard;

import java.io.*;
import java.util.*;

public class Baekjoon2087 {

	static int N, K;
	static int[] numArr, leftNumArr, rightNumArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numArr = new int[N];

		for(int i = 0 ; i < N ; i++) {
			numArr[i] = Integer.parseInt(br.readLine());
		}

		K = Integer.parseInt(br.readLine());

		makeSumArr();
	}

	private static void makeSumArr() {
		List<Integer> leftSumArr = new ArrayList<>();
		List<Integer> rightSumArr = new ArrayList<>();

		leftNumArr = Arrays.copyOfRange(numArr, 0, N/2);
		rightNumArr = Arrays.copyOfRange(numArr, N/2, N);

		for(int i = 0 ; i < (1 << leftNumArr.length) ; i++) { // 
			int sum = 0;
			for(int j = 0 ; j < leftNumArr.length ; j++) {
				if((i & (1 << j)) != 0) {
					sum += leftNumArr[j];
				}
			}
			leftSumArr.add(sum);
		}


		for(int i = 0 ; i < (1 << rightNumArr.length) ; i++) {
			int sum = 0;
			for(int j = 0 ; j < rightNumArr.length ; j++) {
				if((i & (1 << j)) != 0) {
					sum += rightNumArr[j];
				}
			}
			rightSumArr.add(sum);
		}

		List<Integer> tmpArr = new ArrayList<>(rightSumArr);

		Collections.sort(tmpArr);
		for(int i = 0 ; i < leftSumArr.size() ; i++) {
			if(binarySearch(K - leftSumArr.get(i), tmpArr)) {
				print(i,leftSumArr,rightSumArr);
				return;
			}
		}
		System.out.println(-1);
		return;
	}

	private static boolean binarySearch(int target, List<Integer> arr) {
		int bot = 0;
		int top = arr.size()-1;
		int mid = 0;

		while(bot <= top) {
			mid =  (bot + top) >> 1;
			int midV = arr.get(mid);
			if(target == midV) {
				return true;
			}else if(target > midV) {
				bot = mid + 1;
			}else if(target < midV) {
				top = mid - 1;
			}
		}

		return false;
	}

	private static void print(int index, List<Integer> leftSumArr, List<Integer> rightSumArr) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		int left = leftSumArr.get(index);
		int rightIndex = rightSumArr.indexOf(K - left);
		sb.append(String.format("%" + leftNumArr.length + "s", Integer.toBinaryString(index)).replace(' ', '0')).reverse();
		sb2.append(String.format("%" + rightNumArr.length + "s", Integer.toBinaryString(rightIndex)).replace(' ', '0')).reverse();
		sb.append(sb2);
		System.out.println(sb.toString());
		int result = 0;
		for(int i = 0 ; i < sb.toString().length(); i++) {
			if(sb.toString().charAt(i) == '1') {
				result += numArr[i];
			}
		}
		System.out.println(result);
	}
}
