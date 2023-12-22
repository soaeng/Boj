package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    제로
    https://www.acmicpc.net/problem/10773
*/
public class Boj_10773_Array {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int ans = 0;
        int[] num = new int[K];
        int top = -1;
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                ans -= num[top];
                top--;
            } else {
                top++;
                num[top] = n;
                ans += n;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10773_Array().solution();
    }
}
