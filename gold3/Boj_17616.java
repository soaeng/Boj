package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    등수 찾기
    https://www.acmicpc.net/problem/17616

    - bfs: 152340KB / 684ms
    - dfs: 152652KB / 740ms
*/
public class Boj_17616 {
    static int N;
    //    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> better;
    static ArrayList<ArrayList<Integer>> worse;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        better = new ArrayList<>();
        worse = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            better.add(new ArrayList<>());
            worse.add(new ArrayList<>());
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            better.get(B).add(A); // A는 B보다 잘했음
            worse.get(A).add(B); // B는 A보다 못했음
        }
        StringBuilder sb = new StringBuilder();
//        visited = new boolean[N];
//        sb.append(dfs(X, true)).append(" ");
//        visited = new boolean[N];
//        sb.append(N - dfs(X, false) + 1);
        sb.append(bfs(X, true)).append(" ").append(bfs(X, false));
        System.out.println(sb);
    }

//    private static int dfs(int X, boolean isBetter) {
//        visited[X] = true;
//        int count = 1;
//        for (int next : isBetter ? better.get(X) : worse.get(X)) {
//            if (visited[next]) continue;
//            count += dfs(next, isBetter);
//        }
//        return count;
//    }

    private static int bfs(int X, boolean isBetter) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        queue.offer(X);
        visited[X] = true;
        int count = -1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            for (int next : isBetter ? better.get(node) : worse.get(node)) {
                if (visited[next]) continue;
                queue.offer(next);
                visited[next] = true;
            }
        }
        return isBetter ? ++count : N - count;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
