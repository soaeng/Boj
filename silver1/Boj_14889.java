package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    스타트와 링크
    https://www.acmicpc.net/problem/14889
*/
public class Boj_14889 {
    static int N, min;
    static int[][] arr;
    static boolean[] visited;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0);
        System.out.println(min);
    }

    private static void comb(int depth, int start) {
        if (depth == N / 2) {
            getDifferent();
            if (min == 0) {
                System.out.println(min);
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < N; i++) {
            visited[i] = true;
            comb(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static void getDifferent() {
        int start = 0, link = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    start += arr[i][j] + arr[j][i];
                } else if (!visited[i] && !visited[j]) {
                    link += arr[i][j] + arr[j][i];
                }
            }
        }
        min = Math.min(min, Math.abs(start - link));
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
