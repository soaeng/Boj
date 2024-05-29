package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
    기사들의 여행
    https://www.acmicpc.net/problem/30396
*/
public class Boj_30396 {
    static int origin, target;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 2; i++) {
            for (int r = 3; r >= 0; r--) {
                String input = br.readLine();
                for (int c = 0; c < 4; c++) {
                    if (input.charAt(c) == '1') {
                        if (i == 0) origin |= (1 << (r * 4 + (3 - c)));
                        else target |= (1 << (r * 4 + (3 - c)));
                    }
                }
            }
        }
//        printBinary(origin);
//        printBinary(target);
        System.out.println(bfs());
    }

    private static void printBinary(int num) {
        System.out.printf("%16s\n", Integer.toBinaryString(num));
    }

    private static int bfs() {
        int[][] deltas = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[1 << 16];
        queue.offer(origin);
        visited[origin] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int node = queue.poll();
                if (node == target) return time;
                for (int i = 0; i < 16; i++) {
                    int pos = 1 << i;
                    if ((node & pos) == 0) continue;
                    int r = i / 4;
                    int c = i % 4;
                    for (int d = 0; d < 8; d++) {
                        int nr = r + deltas[d][0];
                        int nc = c + deltas[d][1];
                        // 체스판을 벗어나거나
                        if (isOutOfRange(nr, nc)) continue;
                        int next = 1 << (nr * 4 + nc);
                        // 같은 칸에 두 개 이상의 나이트가 존재해서는 안 된다.
                        if ((node & next) > 0) continue;
//                        next = (node | next) & ~(1 << (r * 4 + c));
                        next = node + next - pos;
//                        printBinary(next);
                        if (visited[next]) continue;
                        queue.offer(next);
                        visited[next] = true;
                    }
                }

            }
            time++;
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= 4 || c < 0 || c >= 4;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
