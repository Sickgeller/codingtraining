package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon22116 {
    
    static int N, map[][];
    static int[] moveX = {1,-1,0,0};
    static int[] moveY = {0,0,1,-1};
    static int[][] dist;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        
        // 맵 입력 받기
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // dist 배열을 무한대로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 다익스트라 알고리즘 시작
        dijkstra(0, 0);
        
        // 결과 출력
        System.out.println(dist[N-1][N-1]);
    }
    
    private static void dijkstra(int startY, int startX) {
        
        // 우선순위 큐: (가중치, y, x)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startY, startX, 0));
        dist[startY][startX] = 0;
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            // 이미 방문한 경로는 무시
            if (now.weight > dist[now.indexY][now.indexX]) continue;
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextY = now.indexY + moveY[i];
                int nextX = now.indexX + moveX[i];
                
                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
                
                // 현재 칸과 다음 칸의 경사 차이 계산
                int newWeight = Math.max(now.weight, Math.abs(map[nextY][nextX] - map[now.indexY][now.indexX]));
                
                // 더 작은 경사 차이로 갱신할 수 있으면 갱신
                if (newWeight < dist[nextY][nextX]) {
                    dist[nextY][nextX] = newWeight;
                    pq.add(new Node(nextY, nextX, newWeight));
                }
            }
        }
    }
}

// Node 클래스는 우선순위 큐에서 사용할 객체
class Node implements Comparable<Node> {
    int indexY, indexX, weight;
    
    public Node(int indexY, int indexX, int weight) {
        this.indexY = indexY;
        this.indexX = indexX;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
