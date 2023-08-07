package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
    괄호
    https://www.acmicpc.net/problem/9012
 */
public class Boj_9012 {
    static int T;
    static String PS;
    static String ans;
    static Stack<String> stack;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        int len;
        for (int t = 0; t < T; t++) {
            ans = "";
            stack = new Stack<>();
            PS = br.readLine();
            len = PS.length();
            for (int i = 0; i < len; i++) {
                if (PS.charAt(i) == '(') stack.push("(");
                else {
                    if (stack.isEmpty()) {
                        ans = "NO";
                        break;
                    } else stack.pop();
                }
            }
            if (ans.equals("NO") || !stack.isEmpty()) ans = "NO";
            else ans = "YES";
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_9012().solution();
    }
}
