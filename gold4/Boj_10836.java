package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    여왕벌
    https://www.acmicpc.net/problem/10836
*/
public class Boj_10836 {
    static int M, N;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[2 * M - 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            for (int j = 1; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                for (int k = 0; k < num; k++) {
                    arr[zero++] += j;
                }
            }
        }
        for (int i = M - 1; i >= 0; i--) {
            sb.append(arr[i] + 1).append(" ");
            for (int j = M; j < M * 2 - 1; j++) {
                sb.append(arr[j] + 1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10836().solution();
    }
}
