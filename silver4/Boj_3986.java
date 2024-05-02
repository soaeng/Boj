package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
    좋은 단어
    https://www.acmicpc.net/problem/3986
*/
public class Boj_3986 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        while (N-- > 0) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            char[] word = br.readLine().toCharArray();
            for (char ch : word) {
                if (!stack.isEmpty() && stack.peek() == ch) stack.pop();
                else stack.push(ch);
            }
            if (stack.isEmpty()) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
