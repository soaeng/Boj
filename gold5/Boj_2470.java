package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    두 용액
    https://www.acmicpc.net/problem/2470
*/
public class Boj_2470 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        if (arr[0] > 0) {
            sb.append(arr[0]).append(" ").append(arr[1]);
            System.out.println(sb);
            return;
        } else if (arr[N - 1] < 0) {
            sb.append(arr[N - 2]).append(" ").append(arr[N - 1]);
            System.out.println(sb);
            return;
        }
        int start = 0;
        int end = N - 1;
        int zero = Integer.MAX_VALUE;
        int a = 0, b = 0;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (zero > Math.abs(sum)) {
                zero = Math.abs(sum);
                a = arr[start];
                b = arr[end];
                if (sum == 0) break;
            }
            if (sum < 0) start++;
            else if (sum > 0) end--;
        }

        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
