package easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon6593 {

	static int L = -1,R = -1 ,C = -1, pointS[],pointE[], result;
	static char[][][] map;
	static boolean[][][] visited;
	static int[] moveX= {0,0,0,0,1,-1};
	static int[] moveY = {0,0,1,-1,0,0};
	static int[] moveZ = {1,-1,0,0,0,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {

			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
		
			if(L == 0 && R == 0 && C == 0) {
				bw.flush();
				return;
			}
			
			visited = new boolean[L][R][C];
			map = new char[L][R][C];
			for(int i = 0 ; i < L ; i++) {
				for(int j = 0 ; j < R ; j++) {
					String tmp = br.readLine();
					for(int z = 0 ; z < C ; z++) {
						char ch = tmp.charAt(z);
						if(ch == 'S') {
							pointS = new int[]{i,j,z};
						}else if(ch == 'E') {
							pointE = new int[] {i,j,z};
						}
						map[i][j][z] = ch;
					}
				}
				br.readLine();
			}
			result = 0;
			if(bfs()) {
				bw.write(String.format("Escaped in %d minute(s).\n", result));
			}else {
				bw.write("Trapped!\n");
			}

		}
	}
	private static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {pointS[0],pointS[1],pointS[2]});
		visited[pointS[0]][pointS[1]][pointS[2]] = true;
		
		while(!q.isEmpty()) {
			

			int sizeQ = q.size();
			
			for(int i = 0 ; i < sizeQ ; i++) {
				
				int[] now = q.poll();
				int nowZ = now[0];
				int nowY = now[1];
				int nowX = now[2];
				
				if(map[nowZ][nowY][nowX] == 'E') return true;
				
				for(int j = 0 ; j < 6 ; j++) {
					int nextZ = nowZ + moveZ[j];
					int nextY = nowY + moveY[j];
					int nextX = nowX + moveX[j];
					if(nextZ >= L || nextZ < 0 || nextY >= R || nextY < 0 || nextX >= C || nextX < 0 ) {
						continue;
					}
					if(!visited[nextZ][nextY][nextX] && map[nextZ][nextY][nextX] != '#') {
						visited[nextZ][nextY][nextX] = true;
						q.add(new int[] {nextZ,nextY,nextX});
					}
					
				}
			}
			result++;
		}
		return false;
	}
}
