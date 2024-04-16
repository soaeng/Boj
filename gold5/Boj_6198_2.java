package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
    옥상 정원 꾸미기
    https://www.acmicpc.net/problem/6198
*/
public class Boj_6198_2 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long count = 0;
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peekLast() <= height) {
                stack.pollLast();
            }
            count += stack.size();
            stack.offerLast(height);
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
