package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    주식
    https://www.acmicpc.net/problem/11501
*/
public class Boj_11501 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int result = 0;
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int max = arr[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (arr[i] <= max) result += max - arr[i];
                else max = arr[i];
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
