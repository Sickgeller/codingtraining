package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon9177 {

    static int T;
    static String word1, word2, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            word1 = st.nextToken();
            word2 = st.nextToken();
            result = st.nextToken();
            
            if (isPossible()) {
                System.out.printf("Data set %d: yes\n", i);
            } else {
                System.out.printf("Data set %d: no\n", i);
            }
        }
    }

    private static boolean isPossible() {
        int n1 = word1.length();
        int n2 = word2.length();
        int m = result.length();
        
        // DP 테이블 초기화
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;

        // DP 계산
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (i < n1 && dp[i][j] && word1.charAt(i) == result.charAt(i + j)) {
                    dp[i + 1][j] = true;
                }
                if (j < n2 && dp[i][j] && word2.charAt(j) == result.charAt(i + j)) {
                    dp[i][j + 1] = true;
                }
            }
        }
        
        return dp[n1][n2]; // 마지막 위치에서 result를 만들 수 있는지 확인
    }
}
