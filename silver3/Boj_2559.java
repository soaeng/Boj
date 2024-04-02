package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    수열
    https://www.acmicpc.net/problem/2559
*/
public class Boj_2559 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        int max = sum;
        for (int i = 0; i < N - K; i++) {
            sum -= arr[i];
            sum += arr[i + K];
            if (max < sum) max = sum;
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
