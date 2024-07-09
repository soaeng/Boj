package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    어드벤처 게임
    https://www.acmicpc.net/problem/2310
*/
public class Boj_2310 {
    static int N;
    static boolean flag;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> list;
    static ArrayList<Room> rooms;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            flag = false;
            visited = new boolean[N];
            list = new ArrayList<>();
            rooms = new ArrayList<>();
            for (int i = 0; i < N; i++) list.add(new ArrayList<>());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                rooms.add(new Room(st.nextToken().charAt(0), Integer.parseInt(st.nextToken())));
                while (true) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 0) break;
                    list.get(i).add(num - 1);
                }
            }
            N--;
            dfs(0, 0);
            sb.append(flag ? "Yes" : "No").append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, int cost) {
        if (cost < 0) return;
        if (flag) return;
        if (idx == N) {
            flag = true;
            return;
        }
        for (int next : list.get(idx)) {
            if (visited[next]) continue;
            Room nr = rooms.get(next);
            switch (nr.type) {
                case 'L':
                    cost = Math.max(cost, nr.cost);
                    break;
                case 'T':
                    if (cost - nr.cost < 0) return;
                    cost -= nr.cost;
                    break;
            }
            visited[next] = true;
            dfs(next, cost);
            visited[next] = false;
        }
    }

    private static class Room {
        char type;
        int cost;

        public Room(char type, int cost) {
            this.type = type;
            this.cost = cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
