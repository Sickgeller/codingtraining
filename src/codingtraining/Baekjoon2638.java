package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon2638 {

    static int N, M, map[][], count[][], result; // 모눈종이 가로세로
    static boolean[][] visited;
    static int moveX[] = {1, -1, 0, 0};
    static int moveY[] = {0, 0, 1, -1};
    static List<int[]> cheeseList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        // 외부 공기 탐색: (0,0)부터 시작

        while (true) {
        	q.add(new int[]{0, 0});
        	visited = new boolean[N][M];
        	visited[0][0] = true;
        	count = new int[N][M];
            boolean cheeseMelted = false;
            cheeseList = new ArrayList<>();

            while (!q.isEmpty()) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nextY = now[0] + moveY[i];
                    int nextX = now[1] + moveX[i];
                    if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= M || visited[nextY][nextX]) {
                        continue;
                    }
                    if (map[nextY][nextX] == 0) {
                        q.add(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                    } else {
                        count[nextY][nextX]++;
                        if (count[nextY][nextX] == 2) {
                            cheeseList.add(new int[]{nextY, nextX});
                        }
                    }
                }
            }
            // 치즈가 녹으면 상태 업데이트
            if (!cheeseList.isEmpty()) {
                cheeseMelted = true;
                for (int[] pos : cheeseList) {
                    map[pos[0]][pos[1]] = 0;
                }
            }

            // 치즈가 더 이상 녹지 않으면 종료
            if (!cheeseMelted) {
                break;
            }
      
            result++;
            q.clear();
            q.add(new int[] {0,0});
        }
    }
}
