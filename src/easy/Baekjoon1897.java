package easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Baekjoon1897 {

	static int d; // 사전등재 단어수
	static String word;
	static String result;
	static ArrayList<ArrayList<String>> dictionary;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	d = Integer.parseInt(st.nextToken());
    	word = st.nextToken();
    	result = word;
    	
    	dictionary = new ArrayList<>();
    	
    	IntStream.range(0, 81).forEach(o -> dictionary.add(new ArrayList<>()));
    	
    	while(d-- > 0) {
    		String w = br.readLine();
    		int length = w.length();
    		dictionary.get(length).add(w);
    	} // 사전등록끝
    	todalgi(word);
    	
    	System.out.println(result);
    }
	private static void todalgi(String wordT) {
		
		if(result.length() < wordT.length()) {
			result = wordT;
		}
		ArrayList<String> list = dictionary.get(wordT.length()+1);
		for(String tmp : list) {
			if(isDiff(wordT,tmp)) {
				todalgi(tmp);
			}
		}
		
	}
	private static boolean isDiff(String wordT, String tmp) {
		boolean diffOnce = false;
		for(int i = 0 ; i < wordT.length() ; i++) {
			if(wordT.charAt(i) != (diffOnce ? tmp.charAt(i+1) : tmp.charAt(i))) {
				if(diffOnce == false) {
					i--;
					diffOnce = true;
				}else {
					return false;
				}
			}
		}
		return true;
	}
}

