package codingtraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1629 {

	//자연수 A를 B번 곱한 수를 알고 싶다. 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.
	// A의 B제곱 C로 나눈거  A B 7 13 16

	// ( 4 4 4 4 4 4 ) % 3 -> 4
	// ( 7 7 ... 7) % 16 -> 4

	static long A,B,C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		System.out.println(modularExponentiation(A,B,C));

	}
	private static long modularExponentiation(long a, long b, long c) {
		long result = 1;
		a = a % c; // 먼저 A를 C로 나눈 나머지를 구함

		while (b > 0) {
			if (b % 2 == 1) {
				result = (result * a) % c; // B가 홀수일 때만 곱해짐
			}
			a = (a * a) % c; // A의 제곱을 계산하고 C로 나눈 나머지를 구함
			b = b / 2; // B는 반으로 나누어짐
		}

		return result;
	}
}