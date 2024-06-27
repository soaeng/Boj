package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    지금 만나러 갑니다
    https://www.acmicpc.net/problem/18235
*/
public class Boj_18235 {
    static int N;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        System.out.println(bfs(A, B));
    }

    private static int bfs(int A, int B) {
        int time = 1;
        int[] deltas = {-1, 1};
        Queue<Integer> queA = new ArrayDeque<>();
        Queue<Integer> queB = new ArrayDeque<>();
        int[] visited = new int[N + 1];
        queA.offer(A);
        queB.offer(B);
        while (!queA.isEmpty() || !queB.isEmpty()) {
            int size = queA.size();
            for (int i = 0; i < size; i++) {
                int node = queA.poll();
                for (int d = 0; d < 2; d++) {
                    int nn = node + (int) Math.pow(2, time - 1) * deltas[d];
                    if (isOutOfRange(nn)) continue;
                    queA.offer(nn);
                    visited[nn] = time;
                }
            }
            size = queB.size();
            for (int i = 0; i < size; i++) {
                int node = queB.poll();
                for (int d = 0; d < 2; d++) {
                    int nn = node + (int) Math.pow(2, time - 1) * deltas[d];
                    if (isOutOfRange(nn)) continue;
                    if (visited[nn] == time) return time;
                    queB.offer(nn);
                }
            }
            time++;
        }

        return -1;
    }

    private static boolean isOutOfRange(int n) {
        return n <= 0 || n > N;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
