package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    내려가기
    https://www.acmicpc.net/problem/2096
*/
public class Boj_2096 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[] min = new int[3];
        int[] max = new int[3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.arraycopy(arr[0], 0, min, 0, 3);
        System.arraycopy(arr[0], 0, max, 0, 3);
        for (int i = 1; i < N; i++) {
            int[][] temp = {{Math.max(max[0], max[1]), Math.max(max[1], max[2])}, {Math.min(min[0], min[1]), Math.min(min[1], min[2])}};
            max[0] = temp[0][0] + arr[i][0];
            max[1] = Math.max(temp[0][0], temp[0][1]) + arr[i][1];
            max[2] = temp[0][1] + arr[i][2];
            min[0] = temp[1][0] + arr[i][0];
            min[1] = Math.min(temp[1][0], temp[1][1]) + arr[i][1];
            min[2] = temp[1][1] + arr[i][2];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(max[0], Math.max(max[1], max[2]))).append(" ");
        sb.append(Math.min(min[0], Math.min(min[1], min[2])));
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
