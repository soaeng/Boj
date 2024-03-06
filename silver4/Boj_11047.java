package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    동전 0
    https://www.acmicpc.net/problem/11047
*/
public class Boj_11047 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] unit = new int[N];
        for (int i = 0; i < N; i++) {
            unit[i] = Integer.parseInt(br.readLine());
        }
        N--;
        int ans = 0;
//        for (int i = N; i >= 0 && K > 0; i--) {
//            if (K % unit[i] == 0) {
//                ans += K / unit[i];
//                K = 0;
//            } else {
//                while (K >= unit[i]) {
//                    ans++;
//                    K -= unit[i];
//                }
//            }
//        }

        for (int i = N; i >= 0 && K > 0; i--) {
            if (K >= unit[i]) {
                ans += K / unit[i];
                K = K % unit[i];
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
