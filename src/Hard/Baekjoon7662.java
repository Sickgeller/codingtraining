package Hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon7662 {
	
	//우선순위큐 두개 이용해서 구현 상당히 난이도 있다고 생각
	
    public static void main(String[] args) throws IOException {
       
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트케이스

        while(T-- > 0) {
        	
            int k = Integer.parseInt(br.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            
            PriorityQueue<Integer> minQue = new PriorityQueue<>(); // 최소값큐
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder()); // 최대값 큐

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char ch = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                if (ch == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1);

                    minQue.add(n);
                    maxQue.add(n);
                } else {
                    if (map.size() == 0)
                        continue;

                    PriorityQueue<Integer> que = n == 1 ? maxQue : minQue;
                    removeMap(que, map);
                }
            }

            if (map.size() == 0)
                System.out.println("EMPTY");
            else {
            	
                int n = removeMap(maxQue, map);
                System.out.println(n + " " + (map.size() > 0 ? removeMap(minQue, map) : n));
            }
        }
    }

    static int removeMap(PriorityQueue<Integer> que, Map<Integer, Integer> map) {
        int num;
        while (true) {
            num = que.poll();
            int cnt = map.getOrDefault(num, 0);

            if (cnt == 0)
                continue;

            if (cnt == 1)
                map.remove(num);
            else
                map.put(num, cnt - 1);

            break;
        }

        return num;
    }
}