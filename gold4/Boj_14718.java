package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
    용감한 용사 진수
    https://www.acmicpc.net/problem/14718
*/
public class Boj_14718 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] stats = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stats[i][0] = Integer.parseInt(st.nextToken());
            stats[i][1] = Integer.parseInt(st.nextToken());
            stats[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stats, Comparator.comparingInt(o -> o[0]));
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int count = 0;
                    for (int l = 0; l < N; l++) {
                        if (stats[i][0] < stats[l][0]) continue;
                        if (stats[j][1] < stats[l][1]) continue;
                        if (stats[k][2] < stats[l][2]) continue;
                        count++;

                    }
                    if (count >= K) {
                        min = Math.min(min, stats[i][0] + stats[j][1] + stats[k][2]);
                    }
                }
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
