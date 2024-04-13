package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    통계학
    https://www.acmicpc.net/problem/2108
*/
public class Boj_2108 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[8001];
        int sum = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (++count[arr[i] + 4000] > max) max = count[arr[i] + 4000];
            sum += arr[i];
        }
        sb.append(Math.round((float) sum / N)).append("\n");
        Arrays.sort(arr);
        sb.append(arr[N / 2]).append("\n");
        int idx = 0;
        int[] freq = new int[2];
        for (int i = 0; i <= 8000; i++) {
            if (count[i] == max) {
                freq[idx++] = i - 4000;
                if (idx == 2) break;
            }
        }
        sb.append(idx == 2 ? freq[1] : freq[0]).append("\n");
        sb.append(arr[N - 1] - arr[0]);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
