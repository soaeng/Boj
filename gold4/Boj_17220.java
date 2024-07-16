package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    마약수사대
    https://www.acmicpc.net/problem/17220

    bfs - 14108 KB / 120 ms
    dfs - 14104 KB / 104 ms
*/
public class Boj_17220 {
    static int N, count;
    static boolean[] visited;
    static boolean[] parents;
    static ArrayList<ArrayList<Integer>> list;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        parents = new boolean[N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) list.add(new ArrayList<>());
        while (M-- > 0) {
            String input = br.readLine();
            int u = input.charAt(0) - 'A';
            int v = input.charAt(2) - 'A';
            list.get(u).add(v);
            parents[v] = true;
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            int u = st.nextToken().charAt(0) - 'A';
            visited[u] = true;
        } // end of input
        for (int i = 0; i < N; i++) {
            if (parents[i]) continue;
            if (visited[i]) continue;
            dfs(i);
        }
//        System.out.println(bfs());
        System.out.println(count);
    }

    private static void dfs(int node) {
        for (int next : list.get(node)) {
            if (visited[next]) continue;
            visited[next] = true;
            count++;
            dfs(next);
        }
    }

    private static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (parents[i]) continue;
            if (visited[i]) continue;
            queue.offer(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : list.get(node)) {
                if (visited[next]) continue;
                queue.offer(next);
                visited[next] = true;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
