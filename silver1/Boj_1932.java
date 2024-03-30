package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    정수 삼각형
    https://www.acmicpc.net/problem/1932
*/
public class Boj_1932 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            arr[i][0] += arr[i - 1][0];
            arr[i][i] += arr[i - 1][i - 1];
            for (int j = 1; j < i; j++) {
                arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[n - 1][i]);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
