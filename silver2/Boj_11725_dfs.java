package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    트리의 부모 찾기
    https://www.acmicpc.net/problem/11725
*/
public class Boj_11725_dfs {
    static int N;
    static int[] visited;
    static ArrayList<Integer>[] tree;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine()) + 1;
        tree = new ArrayList[N];
        visited = new int[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        dfs(1);
        for (int i = 2; i < N; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int from) {
        for (int n : tree[from]) {
            if (visited[n] != 0) continue;
            visited[n] = from;
            dfs(n);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_11725_dfs().solution();
    }
}
