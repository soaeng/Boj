package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
    제로
    https://www.acmicpc.net/problem/10773
*/
public class Boj_10773_ArrayDeque {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) ans -= stack.pop();
            else {
                stack.push(n);
                ans += n;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10773_ArrayDeque().solution();
    }
}
