package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    보스몬스터 전리품
    https://www.acmicpc.net/problem/20005
*/
public class Boj_20005 {
    static int N, M, P, bossR, bossC, bossHp;
    static int[] dps;
    static char[][] map;
    static boolean[][][] visited;
    static Map<Character, Player> players;
    static ArrayList<Player> got;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dps = new int[P];
        map = new char[M][N];
        got = new ArrayList<>();
        visited = new boolean[P][M][N];
        players = new HashMap<>();
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 'a' && map[i][j] <= 'z')
                    players.put(map[i][j], new Player(i, j));
                if (map[i][j] == 'B') {
                    bossR = i;
                    bossC = j;
                }
            }
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            Player player = players.get(st.nextToken().charAt(0));
            player.seq = i;
            player.dps = Integer.parseInt(st.nextToken());
        }
        bossHp = Integer.parseInt(br.readLine());
        bfs();
        System.out.println(got.size());
    }

    private static void bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Player> queue = new LinkedList<>(players.values());

        for (Player p : queue) visited[p.seq][p.r][p.c] = true;

        while (bossHp > 0) {
            int size = queue.size();
//            int dps = 0;
            while (size-- > 0) {
                Player player = queue.poll();
                if (got.contains(player)) continue;
                if (player.r == bossR && player.c == bossC) {
                    got.add(player);
//                    dps += player.dps;
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = player.r + deltas[d][0];
                    int nc = player.c + deltas[d][1];
                    if (nr >= 0 && nr < M && nc >= 0 && nc < N && !visited[player.seq][nr][nc] && map[nr][nc] != 'X') {
                        queue.offer(new Player(player.seq, nr, nc, player.dps));
                        visited[player.seq][nr][nc] = true;
                    }
                }
            }
//            bossHp -= dps;
            for (Player p : got) {
                bossHp -= p.dps;
            }
        }
    }

    private static class Player {
        int seq, r, c, dps;

        public Player(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Player(int seq, int r, int c, int dps) {
            this.seq = seq;
            this.r = r;
            this.c = c;
            this.dps = dps;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + dps + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_20005().solution();
    }
}
