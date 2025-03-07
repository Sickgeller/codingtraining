package easy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Baekjoon5430 {

	public static void main(String[] args) throws NumberFormatException, IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test = Integer.parseInt(br.readLine());
		Loop : while(test-- > 0) {
			char[] chArr = br.readLine().toCharArray();
			int numCount = Integer.parseInt(br.readLine());
			int[] intArr = new int[numCount];
			StringBuilder sb = new StringBuilder(br.readLine());
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(0); // [] 삭제 숫자와 ,로만 이뤄진 무나졍ㄹ
			StringTokenizer st = new StringTokenizer(sb.toString(), ",");
			for(int i = 0; i < numCount ; i ++) {
				intArr[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer> list= new ArrayList<>();

			for(int num : intArr) {
				list.add(num);
			}

			boolean reverse = false;

			for(int i = 0 ; i < chArr.length ; i++) {
				if(chArr[i] == 'D') {
					if(list.isEmpty()) {
						bw.write("error\n");
						continue Loop;
					}

					if(reverse) {
						list.remove(list.size()-1);
					}else {
						list.remove(0);
					}

				}else {
					reverse = !reverse;
				}
			}
			if(reverse) {
				Collections.reverse(list);
			}
			sb = new StringBuilder();
			sb.append("[");
			if(list.size() != 0) {
				for(int num : list) {
					sb.append(num).append(",");
				}
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]");
			bw.write(sb.toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
