package Hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Baekjoon2350 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        Arrays.fill(parent, -1);

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.w, e1.w));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Edge(u, v, w));
        }

        // 크루스칼 알고리즘으로 MST 생성
        List<List<Node>> graph = new ArrayList<>();
        IntStream.range(0, N + 1).forEach(i -> graph.add(new ArrayList<>()));

        int count = 0;
        while (count < N - 1) {
            Edge e = pq.poll();

            // 유니온 파인드에서 트리 합치기
            if(!union(e)) continue;
            
            // 간선 추가
            graph.get(e.u).add(new Node(e.v, e.w));
            graph.get(e.v).add(new Node(e.u, e.w));
            count++;
        }

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(getMaxWidth(i, j, graph)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    // 유니온 파인드에서 find 연산
    static int find(int u) {
        if (parent[u] < 0) return u;
        return parent[u] = find(parent[u]);
    }

    // 유니온 파인드에서 union 연산 (두 트리를 합침)
    static boolean union(Edge e) {
    	 int p1 = find(e.u);
         int p2 = find(e.v);

         if (p1 == p2) return false;
         
        if (parent[p1] < parent[p2]) {
            parent[p1] += parent[p2];
            parent[p2] = p1;
        } else {
            parent[p2] += parent[p1];
            parent[p1] = p2;
        }
        return true;
    }

    // 두 도시 간의 최대 배의 폭을 찾는 함수
    static int getMaxWidth(int start, int end, List<List<Node>> graph) {
        int[] preNode = new int[graph.size()];  // 각 도시의 이전 도시를 기록하는 배열
        int[] maxWidth = new int[graph.size()];  // 각 도시까지 도달할 수 있는 최대 배의 폭을 기록하는 배열

        Arrays.fill(preNode, -1);  // 초기값 -1로 설정 (방문 안한 노드)
        Arrays.fill(maxWidth, 0);  // 초기값 0으로 설정 (배의 폭이 아직 설정되지 않은 도시)

        ArrayDeque<Integer> queue = new ArrayDeque<>();  // BFS 탐색을 위한 큐
        queue.add(start);  // 시작 도시를 큐에 넣음
        maxWidth[start] = Integer.MAX_VALUE;  // 시작 도시는 제한 없이 지나갈 수 있다고 가정 (최대 폭)

        while (!queue.isEmpty()) {
            int cur = queue.poll();  // 현재 노드를 큐에서 꺼냄

            // 만약 현재 노드가 목표 도시라면, 더 이상 탐색할 필요 없으므로 종료
            if (cur == end) break;

            // 현재 도시에서 연결된 모든 인접 도시를 탐색
            for (Node next : graph.get(cur)) {
                // 이미 방문한 도시라면 건너뛰기
                if (preNode[next.v] != -1) continue;

                // 아직 방문하지 않은 도시라면, 해당 도시를 방문했다고 표시
                preNode[next.v] = cur;
                // 해당 도시까지 갈 수 있는 최대 폭은 현재까지 도달한 폭과, 연결된 간선의 폭 중 작은 값을 취함
                maxWidth[next.v] = Math.min(maxWidth[cur], next.w);
                queue.add(next.v);  // 해당 도시를 큐에 추가
            }
        }

        // 목표 도시까지의 최대 배의 폭을 반환
        return maxWidth[end];
    }


    // 간선 클래스
    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // 노드 클래스
    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}