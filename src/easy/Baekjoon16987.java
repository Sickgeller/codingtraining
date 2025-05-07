package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon16987 {

    static int N;
    static ArrayList<Egg> list = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new Egg(weight, durability));
        }

        deepTracking(0);
        System.out.println(result);
    }

    private static void deepTracking(int index) {

        if (index == N) {
            int broken = 0;
            for (Egg egg : list) {
                if (egg.durability <= 0) {
                    broken++;
                }
            }
            result = Math.max(result, broken);
            return;
        }

        Egg now = list.get(index);

        // 현재 계란이 이미 깨졌거나 칠 수 있는 계란이 없으면 다음으로 넘어감
        if (now.durability <= 0 || otherAllBroken(index)) {
            deepTracking(index + 1);
            return;
        }

        boolean hitAny = false;

        for (int i = 0; i < N; i++) {
            if (i == index) continue;

            Egg next = list.get(i);
            if (next.durability <= 0) continue;

            hitAny = true;

            // 계란끼리 충돌
            now.durability -= next.weight;
            next.durability -= now.weight;

            deepTracking(index + 1);

            // 백트래킹 (상태 복원)
            now.durability += next.weight;
            next.durability += now.weight;
        }

        // 부딪힌 적이 없다면 다음 계란으로 그냥 넘기기
        if (!hitAny) {
            deepTracking(index + 1);
        }
    }

    private static boolean otherAllBroken(int index) {
        for (int i = 0; i < N; i++) {
            if (i != index && list.get(i).durability > 0) return false;
        }
        return true;
    }
}

class Egg {

    int weight;
    int durability;

    public Egg(int weight, int durability) {
        this.weight = weight;
        this.durability = durability;
    }
}
