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

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            boolean flag = false; // 판단 완료했는지
//            stack = new Stack<>();
            stack.clear();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    stack.push('(');
                } else {
                    if (!stack.isEmpty()) stack.pop();
                    else {
                        flag = true;
                        break;
                    }
                }
            }
            if (!stack.isEmpty()) flag = true;
            if (flag) sb.append("NO");
            else sb.append("YES");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_9012().solution();
    }
}
