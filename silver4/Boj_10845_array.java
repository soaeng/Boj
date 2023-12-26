package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    큐
    https://www.acmicpc.net/problem/10845
*/
public class Boj_10845_array {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] queue = new int[N]; // N번 모두 offer라 생각했을 때의 크기
        int front = -1; // queue의 머리
        int rear = -1;  // queue의 꼬리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    queue[++rear] = Integer.parseInt(st.nextToken());
                    break;
                case "pop":
                    sb.append(front == rear ? -1 : queue[++front]).append("\n");
                    break;
                case "size":
                    sb.append(rear - front).append("\n");
                    break;
                case "empty":
                    sb.append(front == rear ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(front == rear ? -1 : queue[front + 1]).append("\n");
                    break;
                case "back":
                    sb.append(front == rear ? -1 : queue[rear]).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_10845_array().solution();
    }
}
