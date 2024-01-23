package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    팩토리얼
    https://www.acmicpc.net/problem/10872
*/
public class Boj_10872_recursive {
    static int N;
    static long ans;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 1;
        factorial(N);
        System.out.println(ans);
    }

    //    private static int factorial(int depth) {
//        if (depth <= 1) return 1;
//        return depth * factorial(depth - 1);
//    }
    private static void factorial(int depth) {
        if (depth <= 1) return;
        ans *= depth;
        factorial(depth - 1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
