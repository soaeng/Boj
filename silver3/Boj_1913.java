package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    달팽이
    https://www.acmicpc.net/problem/1913
*/
public class Boj_1913 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int[][] array = new int[N][N];
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int r = N / 2;
        int c = N / 2;
        int idx = 1;
        int size = 0;
        array[r][c] = idx;
        int rr = r + 1, cc = c + 1;
        while (idx < N * N) {
            for (int d = 0; d < 4 && idx != N * N; d++) {
                if (d % 2 == 0) size++;
                for (int i = 0; i < size; i++) {
                    r += deltas[d][0];
                    c += deltas[d][1];
                    array[r][c] = ++idx;
                    if (idx == target) {
                        rr = r + 1;
                        cc = c + 1;
                    }
                    if (idx == N * N) break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] nn : array) {
            for (int n : nn) sb.append(n).append(" ");
            sb.append("\n");
        }
        sb.append(rr).append(" ").append(cc);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
