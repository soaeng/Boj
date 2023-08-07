package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_10872 {
    static int N;
    static int ans;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = N;
        factorial(N);
        System.out.println(ans);
    }

    private static void factorial(int n) {
        if (n == 0) {
            ans = 1;
            return;
        }
        if (n == 1) return;
        ans *= (n - 1);
        factorial(n - 1);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10872().solution();
    }
}
