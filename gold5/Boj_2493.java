package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    탑
    https://www.acmicpc.net/problem/2493
*/
public class Boj_2493 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek()[1] >= height) {
                    sb.append(stack.peek()[0]).append(" ");
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) sb.append("0 ");
            stack.push(new int[]{i, height});
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2493().solution();
    }
}
