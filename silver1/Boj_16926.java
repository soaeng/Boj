package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    배열 돌리기 1
    https://www.acmicpc.net/problem/16926
*/
public class Boj_16926 {

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                array[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int size = Math.min(N, M) / 2;
        while (R-- > 0) {
            for (int i = 0; i < size; i++) {
                int temp = array[i][i];
                if (M - i >= i + 1) System.arraycopy(array[i], i + 1, array[i], i + 1 - 1, M - i - (i + 1));
//                for (int c = i + 1; c < M - i; c++) {
//                    array[i][c - 1] = array[i][c];
//                }
                for (int r = i; r < N - i - 1; r++) {
                    array[r][M - i - 1] = array[r + 1][M - i - 1];
                }
                if (M - i - 1 >= i) System.arraycopy(array[N - i - 1], i, array[N - i - 1], i + 1, M - i - 1 - i);
//                for (int c = M - i - 1; c > i; c--) {
//                    array[N - i - 1][c] = array[N - i - 1][c - 1];
//                }
                for (int r = N - i - 1; r > i; r--) {
                    array[r][i] = array[r - 1][i];
                }
                array[i + 1][i] = temp;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] nn : array) {
            for (int n : nn) sb.append(n).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
