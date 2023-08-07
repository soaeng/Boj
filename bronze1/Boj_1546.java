package bronze1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    평균
    https://www.acmicpc.net/problem/1546
 */
public class Boj_1546 {
    static int N;
    static float[] subject;
    static float M = 0, ans = 0;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        subject = new float[N];
        for (int i = 0; i < N; i++) {
            subject[i] = Integer.parseInt(st.nextToken());
            if (M < subject[i]) M = subject[i];
        }
        for (int i = 0; i < N; i++) {
            ans += subject[i] / M * 100;
        }
        ans /= N;
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1546().solution();
    }
}
