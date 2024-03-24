package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
    막대기
    https://www.acmicpc.net/problem/17608
*/
public class Boj_17608 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peekLast() <= n) {
                stack.pollLast();
            }
            stack.add(n);
        }
        System.out.println(stack.size());

//        int num = Integer.parseInt(br.readLine());
//        int[] arr = new int[num];
//
//        for (int i = 0; i < num; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//
//        int max = arr[num - 1];
//        int count = 1;
//        for (int i = num - 2; i >= 0; i--) {
//            if (arr[i] > max) {
//                count++;
//                max = arr[i];
//            }
//        }
//        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
