package Hard;

import java.util.*;

public class Baekjoon2370 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        List<int[]> L = new ArrayList<>();
        List<Integer> plane = new ArrayList<>();
        
        // 입력 받기
        for (int i = 0; i < N; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            L.add(new int[]{l, r});
            plane.add(l);
            plane.add(r);
        }

        // 중복 제거 및 정렬
        Set<Integer> setPlane = new HashSet<>(plane);
        plane = new ArrayList<>(setPlane);
        Collections.sort(plane);

        // 값과 인덱스를 매핑하는 Map
        Map<Integer, Integer> dictPlane = new HashMap<>();
        for (int i = 0; i < plane.size(); i++) {
            dictPlane.put(plane.get(i), i);
        }

        // 범위 별로 값을 할당할 배열
        int[] answer = new int[plane.size()];
        Arrays.fill(answer, -1);

        // 구간에 값 할당
        for (int i = 0; i < N; i++) {
            int l = L.get(i)[0];
            int r = L.get(i)[1];
            int dl = dictPlane.get(l);
            int dr = dictPlane.get(r);

            for (int k = dl; k <= dr; k++) {
                answer[k] = i;
            }
        }

        // 방문한 값 개수 세기
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < answer.length; i++) {
            visited.add(answer[i]);
        }

        // 결과 출력
        System.out.println(visited.size());
    }
}
