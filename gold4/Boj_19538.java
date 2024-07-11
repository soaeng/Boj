package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    루머
    https://www.acmicpc.net/problem/19538
*/
public class Boj_19538 {
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static Queue<Integer> queue;
    static int[] log, count;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        queue = new ArrayDeque<>();
        log = new int[N];
        count = new int[N];
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
            log[i] = -1;
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num;
            while ((num = Integer.parseInt(st.nextToken())) != 0) {
                list.get(i).add(num - 1);
                count[i] = (list.get(i).size() + 1) / 2;
            }
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            queue.offer(num);
            log[num] = 0;
            for (int near : list.get(num)) {
                count[near]--;
            }
        }
        // end of input

        bfs();
        for (int l : log) sb.append(l).append(" ");
        System.out.println(sb);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : list.get(node)) {
                if (log[next] != -1) continue;
                if (count[next] > 0) continue;
                queue.offer(next);
                log[next] = log[node] + 1;
                count[node]--;
                for (int near : list.get(next)) {
                    count[near]--;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
