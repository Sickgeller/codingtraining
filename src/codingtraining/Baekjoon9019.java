package codingtraining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon9019 {
	
	/*
	 * 졸려서 S를 C로 계속 써놔서 왜 틀린지를 못찾았음
	 * 문자열들어가는 문제는 반드시 잘읽어보기
	 */

    static int T, result;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            result = Integer.parseInt(st.nextToken());
            if(Integer.parseInt(tmp) == result) {
            	System.out.println();
            	continue;
            }
            set = new HashSet<>();
            bw.write(bfs(Integer.parseInt(tmp)) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static String bfs(int num) {
        Queue<String[]> q = new LinkedList<>();
        q.add(new String[] {String.valueOf(num), ""});
        while (!q.isEmpty()) {
            String[] strArr = q.poll();
            int now = Integer.parseInt(strArr[0]);
            String str = strArr[1];
            if (now == result) return str;

            int d = calculateDouble(now);
            int s = calculateDecrement(now);
            int l = calculateLeftShift(now);
            int r = calculateRightShift(now);

            if (!set.contains(d)) {
            	set.add(d);
                q.add(new String[] {String.valueOf(d), str+"D"});
            }

            if (!set.contains(s)) {
            	set.add(s);
                q.add(new String[] {String.valueOf(s), str+"S"});
            }

            if (!set.contains(l)) {
            	set.add(l);
                q.add(new String[] {String.valueOf(l), str+"L"});
            }
            if (!set.contains(r)) {
            	set.add(r);
                q.add(new String[] {String.valueOf(r), str+"R"});
            }
        }
        return null;
    }

    private static int calculateDouble(int num) {
        return (num * 2) % 10000;
    }

    private static int calculateDecrement(int num) {
        return (num == 0) ? 9999 : num - 1;
    }

    private static int calculateLeftShift(int num) {
    	return num / 1000 + (num % 1000) * 10;
    
    }

    private static int calculateRightShift(int num) {
        return num / 10 + (num % 10) * 1000;
    }

}
