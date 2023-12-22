package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
    균형잡힌 세상
    https://www.acmicpc.net/problem/4949
*/
public class Boj_4949 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;
            Deque<Character> stack = new ArrayDeque<>();
            boolean flag = false;
            for (int i = 0; i < str.length(); i++) {
                switch (str.charAt(i)) {
                    case '(':
                        stack.push('(');
                        break;
                    case '[':
                        stack.push('[');
                        break;
                    case ')':
                        if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                        else flag = true;
                        break;
                    case ']':
                        if (!stack.isEmpty() && stack.peek() == '[') stack.pop();
                        else flag = true;
                        break;
                }
                if (flag) break;
            }
            if (!stack.isEmpty()) flag = true;
            sb.append(flag ? "no" : "yes").append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_4949().solution();
    }
}
