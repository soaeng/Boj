package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    스택
    https://www.acmicpc.net/problem/10828
 */
public class Boj_10828 {
    static int N;
    static int[] stack;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        stack = new int[N];
        int top = -1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    for (int j = 0; j < N; j++) {
                        if (stack[j] == 0) {
                            stack[j] = Integer.parseInt(st.nextToken());
                            top = j;
                            break;
                        }
                    }
                    break;
                case "pop":
                    if (top == -1) sb.append(top).append("\n");
                    else {
                        sb.append(stack[top]).append("\n");
                        stack[top--] = 0;
                    }
                    break;
                case "size":
                    sb.append(top + 1).append("\n");
                    break;
                case "empty":
                    if (top == -1) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "top":
                    if (top == -1) sb.append(-1).append("\n");
                    else sb.append(stack[top]).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10828().solution();
    }
}
