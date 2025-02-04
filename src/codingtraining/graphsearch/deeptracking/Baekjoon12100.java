package codingtraining.graphsearch.deeptracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon12100 {

    static int N, map[][], maxResult;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 초기화 끝
        maxResult = 0;
        game(map, 0, 0);
        System.out.println(maxResult);
    }

    private static void game(int[][] map, int count, int currentMax) {
        // 5번의 이동을 했으면 종료
        if (count == 5) {
            maxResult = Math.max(maxResult, currentMax); // 최댓값 갱신
            return;
        }

        // 4가지 방향에 대해 탐색
        for (int K = 0; K < 4; K++) {
            int[][] mapC = cloneMap(map);  // 상태 복사
            int maxInMove = sort(mapC, K); // 이동 후 얻을 수 있는 최대값 계산
            game(mapC, count + 1, Math.max(currentMax, maxInMove)); // 재귀 호출
        }
    }

    private static int sort(int[][] map, int sort) {
        int currentMax = 0;  // 현재 방향에 대한 최댓값

        switch (sort) {
            case 0: // 위
                for (int i = 0; i < N; i++) {
                    int[] arr = new int[N];
                    for (int j = 0; j < N; j++) {
                        arr[j] = map[j][i];
                    }
                    int[] result = sortArr(arr);
                    currentMax = Math.max(currentMax, Arrays.stream(result).max().getAsInt());
                    for (int j = 0; j < N; j++) {
                        map[j][i] = result[j];
                    }
                }
                break;

            case 1: // 아래
                for (int i = 0; i < N; i++) {
                    int[] arr = new int[N];
                    for (int j = N - 1, k = 0; j >= 0; j--) {
                        arr[k++] = map[j][i]; // 역순으로 세팅
                    }
                    int[] result = sortArr(arr);
                    currentMax = Math.max(currentMax, Arrays.stream(result).max().getAsInt());
                    for (int j = N - 1, k = 0; j >= 0; j--) {
                        map[j][i] = result[k++];
                    }
                }
                break;

            case 2: // 왼쪽
                for (int i = 0; i < N; i++) {
                    int[] result = sortArr(map[i]);
                    currentMax = Math.max(currentMax, Arrays.stream(result).max().getAsInt());
                    map[i] = result;
                }
                break;

            case 3: // 오른쪽
                for (int i = 0; i < N; i++) {
                    int[] arr = map[i];
                    int[] result = sortArr(reverseArray(arr));
                    currentMax = Math.max(currentMax, Arrays.stream(result).max().getAsInt());
                    map[i] = reverseArray(result);
                }
                break;
        }

        return currentMax; // 각 방향에 대해 계산된 최댓값을 반환
    }

    private static int[] sortArr(int[] arr) {
        int firstNum = -1;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                if (firstNum == -1) {
                    index = i;
                    firstNum = arr[i];
                } else {
                    if (firstNum != arr[i]) {
                        firstNum = arr[i];
                        index = i;
                    } else {
                        arr[index] *= 2;
                        arr[i] = 0;
                        firstNum = -1;
                    }
                }
            }
        }

        // 0이 없도록 정렬
        int[] result = new int[arr.length];
        Arrays.fill(result, 0);
        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                result[newIndex++] = arr[i];
            }
        }

        return result;
    }

    private static int[] reverseArray(int[] arr) {
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        return reversed;
    }

    private static int[][] cloneMap(int[][] map) {
        return Arrays.stream(map)
                .map(int[]::clone)
                .toArray(int[][]::new); // 깊은 복사
    }
}
