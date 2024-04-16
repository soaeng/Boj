package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    개똥벌레
    https://www.acmicpc.net/problem/3020
*/
public class Boj_3020 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] bottom = new int[H + 1];
        int[] top = new int[H + 1];
        int[] dp = new int[H];
        for (int i = 0; i < N / 2; i++) {
            bottom[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }
//        for (int i = 1; i < H; i++) {
//            bottom[i] += bottom[i + 1];
//        }
//        for (int i = H - 1; i > 0; i--) {
//            top[i] += top[i + 1];
//        }

        top[H] = N / 2;
        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int h = 0; h < H; h++) {
            bottom[H] += bottom[H - h - 1];
            dp[h] += top[H] + bottom[H];
            top[H] -= top[h];
            if (dp[h] < min) {
                min = dp[h];
                count = 1;
            } else if (dp[h] == min) count++;
        }
        for (int i = 1; i <= H; i++) {
            int temp = bottom[i] + top[H - i + 1];
            if (temp < min) {
                min = temp;
                count = 1;
            } else if (temp == min) count++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(count);
        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
