package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    수 정렬하기
    https://www.acmicpc.net/problem/2750
*/
public class Boj_2750 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
//        int[] arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//        Arrays.sort(arr);
//        for (int n : arr) {
//            sb.append(n).append("\n");
//        }
        boolean[] arr = new boolean[2001];
        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000] = true;
        }
        for (int i = 0; i < 2001; i++) {
            if (arr[i]) sb.append(i - 1000).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
