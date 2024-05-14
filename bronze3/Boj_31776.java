package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    예비 소집 결과 보고서
    https://www.acmicpc.net/problem/31776
*/
public class Boj_31776 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int count = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean condition1 = false;
            boolean condition2 = true;
            int idx = 3;
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] >= 0) condition1 = true;
                if (idx == 3 && arr[i][j] == -1) idx = j;
            }
            for (int j = 1; j < idx; j++) {
                if (arr[i][j - 1] > arr[i][j]) {
                    condition2 = false;
                    break;
                }
            }
            for (int j = idx + 1; j < 3; j++) {
                if (arr[i][j] >= 0) {
                    condition2 = false;
                    break;
                }
            }
            if (condition1 && condition2) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
