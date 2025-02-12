package codingtraining;

import java.io.*;
import java.util.*;

public class Baekjoon28284 {
	
    static long[] cost1, cost2;
    static long oldT, ans1, ans2;
    static int cidx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        cost1 = new long[N];
        cost2 = new long[N];

        // 읽기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost1[i] = Long.parseLong(st.nextToken());
        }

        // cost1을 내림차순으로 정렬
        Arrays.sort(cost1);
        cost2 = Arrays.copyOf(cost1, cost1.length);
        cost1 = reverseArray(cost1);


        // 누적합 계산
        calculatePrefixSum(cost1);
        calculatePrefixSum(cost2);
        
        // 스케줄 처리
        List<Pair> sched = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sched.add(new Pair(S, 1));  // 시작
            sched.add(new Pair(E + 1, -1));  // 끝
        }

        // 스케줄 정렬
        sched.sort(Comparator.comparingLong(p -> p.key));

        // 스케줄을 처리하여 ans1, ans2를 계산
        for (Pair p : sched) {
            if (cidx > 0) {
                ans1 += (p.key - oldT) * cost1[cidx - 1];
                ans2 += (p.key - oldT) * cost2[cidx - 1];
            }
            oldT = p.key;
            cidx += p.value;  // 구간 처리
        }

        // 결과 출력
        System.out.printf("%d %d", ans2, ans1);
    }

    // 배열 뒤집기
    private static long[] reverseArray(long[] arr) {
        int N = arr.length;
        for (int i = 0; i < N / 2; i++) {
            long temp = arr[i];
            arr[i] = arr[N - i - 1];
            arr[N - i - 1] = temp;
        }
        return arr;
    }

    // 누적합 계산
    private static void calculatePrefixSum(long[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
    }
    static class Pair {
        long key;
        int value;
        Pair(long key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

