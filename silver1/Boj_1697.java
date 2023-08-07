package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    숨바꼭질
    https://www.acmicpc.net/problem/1697
*/
public class Boj_1697 {
    static int N, K, MIN = 0, MAX = 100000;
    static boolean[] visit;
    static int t = -1;
    static Queue<Integer> queue = new LinkedList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new boolean[MAX + 1];
        queue.offer(N);

        System.out.println(bfs(N));
    }

    private static int bfs(int n) {
        visit[n] = true;
        int size;
        while (!queue.isEmpty()) {
            t++;
            size = queue.size();
            for (int i = 0; i < size; i++) {
                n = queue.poll();
                if ((n - 1) >= MIN && !visit[n - 1]) {
                    if (n - 1 == K) return t + 1;
                    queue.offer(n - 1);
                    visit[n - 1] = true;
                }
                if ((n + 1) <= MAX && !visit[n + 1]) {
                    if (n + 1 == K) return t + 1;
                    queue.offer(n + 1);
                    visit[n + 1] = true;
                }
                if ((2 * n) >= MIN && (2 * n) <= MAX && !visit[2 * n]) {
                    if (2 * n == K) return t + 1;
                    queue.offer(2 * n);
                    visit[2 * n] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        new Boj_1697().solution();
    }
}
