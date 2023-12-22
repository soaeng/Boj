package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
    스택 수열
    https://www.acmicpc.net/problem/1874
*/
public class Boj_1874 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            sb.append("+").append("\n");
            stack.push(i);
            while (!stack.isEmpty() && stack.peek() == numbers[index]) {
                stack.pop();
                index++;
                sb.append("-").append("\n");
            }
        }
        if (!stack.isEmpty()) System.out.println("NO");
        else System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1874().solution();
    }
}
