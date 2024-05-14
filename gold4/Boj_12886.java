package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    돌 그룹
    https://www.acmicpc.net/problem/12886
*/
public class Boj_12886 {
    static int sum;
    static boolean[][] visited;
    static Queue<Rock> queue;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        sum = A + B + C;
        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }
        queue = new ArrayDeque<>();
        visited = new boolean[sum + 1][sum + 1];
        queue.offer(new Rock(A, B));
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!queue.isEmpty()) {
            Rock rock = queue.poll();
            int a = rock.a;
            int b = rock.b;
            int c = sum - a - b;
            if (a == b && b == c) return 1;
            // A, B
            if (a != b) {
                int na = a > b ? a - b : a * 2;
                int nb = a < b ? b - a : b * 2;
                if (na >= sum || nb >= sum) continue;
                if (visited[na][nb] || visited[nb][na]) continue;
                queue.offer(new Rock(na, nb));
                visited[na][nb] = true;
                visited[nb][na] = true;
            }

            // B, C
            if (b != c) {
                int nb = b > c ? b - c : b * 2;
                int nc = b < c ? c - b : c * 2;
                if (nb >= sum || nc >= sum) continue;
                if (visited[nb][nc] || visited[nc][nb]) continue;
                queue.offer(new Rock(nb, nc));
                visited[nb][nc] = true;
                visited[nc][nb] = true;
            }
            // A, C
            if (a != c) {
                int na = a > c ? a - c : a * 2;
                int nc = a < c ? c - a : c * 2;
                if (na >= sum || nc >= sum) continue;
                if (visited[na][nc] || visited[nc][na]) continue;
                queue.offer(new Rock(na, nc));
                visited[na][nc] = true;
                visited[nc][na] = true;
            }
        }
        return 0;
    }

    private static class Rock {
        int a, b;

        public Rock(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
