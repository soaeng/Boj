package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    센서
    https://www.acmicpc.net/problem/2212
*/
public class Boj_2212 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        if (N <= K) {
            System.out.println(0);
            return;
        }
        int[] sensor = new int[N];
        int[] diff = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) sensor[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensor);
        for (int i = 0; i < N - 1; i++) diff[i] = sensor[i + 1] - sensor[i];
        Arrays.sort(diff);
        int answer = 0;
        for (int i = 0; i < N - K; i++) answer += diff[i];
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
