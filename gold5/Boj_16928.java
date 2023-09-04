package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    뱀과 사다리 게임
    https://www.acmicpc.net/problem/16928
*/
public class Boj_16928 {
    static int N, M;
    static Map<Integer, Integer> space;
    static int[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        space = new HashMap<>();
        visited = new int[101];
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            space.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        bfs();
        System.out.println(visited[100]);
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = current + i;
                if (next > 100 || visited[next] != 0) continue;
                if (space.get(next) != null) next = space.get(next);
                if (visited[next] == 0) {
                    visited[next] = visited[current] + 1;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16928().solution();
    }
}
