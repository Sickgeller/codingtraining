package Hard;

import java.util.Arrays;


public class Baekjoon2470 {
    public static void main(String[] args) {
        // 입력 받기
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int N = sc.nextInt();  // 용액의 수
        int[] liquids = new int[N];
        
        for (int i = 0; i < N; i++) {
            liquids[i] = sc.nextInt();  // 용액의 특성값
        }
        
        // 1. 용액의 특성값을 오름차순으로 정렬
        Arrays.sort(liquids);
        
        // 2. 두 포인터를 이용하여 특성값의 합이 0에 가장 가까운 두 용액을 찾음
        int left = 0, right = N - 1;
        int bestSum = Integer.MAX_VALUE;  // 0에 가장 가까운 합
        int bestLeft = 0, bestRight = 0;
        
        while (left < right) {
            int sum = liquids[left] + liquids[right];
            
            // 현재 합과 가장 가까운 합을 업데이트
            if (Math.abs(sum) < Math.abs(bestSum)) {
                bestSum = sum;
                bestLeft = left;
                bestRight = right;
            }
            
            // 합이 0보다 작으면 left 포인터를 오른쪽으로 이동
            if (sum < 0) {
                left++;
            }
            // 합이 0보다 크면 right 포인터를 왼쪽으로 이동
            else {
                right--;
            }
        }
        
        // 결과 출력: 특성값이 작은 것부터 출력
        System.out.println(liquids[bestLeft] + " " + liquids[bestRight]);
    }
}
