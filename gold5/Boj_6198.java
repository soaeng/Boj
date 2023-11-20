package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
    옥상 정원 꾸미기
    https://www.acmicpc.net/problem/6198
*/
public class Boj_6198 {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
//        Deque<Integer> deque = new ArrayDeque<>();
//        for (int i = 0; i < N; i++) {
//            deque.offer(Integer.parseInt(br.readLine()));
//        }
//        while (!deque.isEmpty()) stack.push(deque.pollLast());
        long count = 0;
//        for (int i = 0; i < N; i++) {
//            deque = new ArrayDeque<>();
//            int current = stack.pop();
//            while (!stack.isEmpty() && stack.peek() <= current) {
//                deque.add(stack.pop());
//            }
//            count += deque.size();
//            while (!deque.isEmpty()) stack.push(deque.pollLast());
//        }
        for (int i = 0; i < N; i++) {
            int current = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }
            stack.push(current);
            count += stack.size() - 1;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Boj_6198().solution();
    }
}
