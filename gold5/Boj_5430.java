package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
    AC
    https://www.acmicpc.net/problem/5430
*/
public class Boj_5430 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String command = br.readLine();
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }
            boolean isLIFO = false;
            int length = command.length();
            for (int i = 0; i < length; i++) {
                switch (command.charAt(i)) {
                    case 'R':
                        isLIFO ^= true;
                        break;
                    case 'D':
                        if (deque.isEmpty()) {
                            sb.append("error\n");
                            length = 0;
                            break;
                        }
                        if (isLIFO) deque.pollLast();
                        else deque.pollFirst();
                        break;
                }
            }
            if (length > 0) {
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(isLIFO ? deque.pollLast() : deque.pollFirst());
                    if (!deque.isEmpty()) sb.append(",");
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
