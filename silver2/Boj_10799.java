package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    쇠막대기
    https://www.acmicpc.net/problem/10799
*/
public class Boj_10799 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] stack = br.readLine().toCharArray();
        int ans = 0;
        int top = -1;
        for (int i = 0; i < stack.length; i++) {
            if (stack[i] == '(') top++;
            else {
                top--;
                if (stack[i - 1] == ')') ans++;
                else ans += top + 1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10799().solution();
    }
}
