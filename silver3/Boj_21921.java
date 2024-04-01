package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    블로그
    https://www.acmicpc.net/problem/21921
*/
public class Boj_21921 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i = N - K; i < N; i++) sum += arr[i];

        int max = sum;
        int total = 1;

        for (int i = N - 1; i >= K; i--) {
            sum -= arr[i];
            sum += arr[i - K];
            if (max == sum) total++;
            else if (max < sum) {
                max = sum;
                total = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (max == 0) sb.append("SAD");
        else sb.append(max).append("\n").append(total);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
