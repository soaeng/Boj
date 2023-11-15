package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    마법사 상어와 파이어볼
    https://www.acmicpc.net/problem/20056
*/
public class Boj_20056 {
    static int N, M, K;
    static ArrayList<Fireball>[][] map;
    static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static ArrayList<Fireball> fireballs;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        fireballs = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        for (int i = 0; i < K; i++) {
            init();
            moveFireball();
            fireballs = afterMove();
        }
        int total = 0;
        for (Fireball fireball : fireballs) {
            total += fireball.m;
        }
        System.out.println(total);
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }

    private static void moveFireball() {
        for (Fireball fireball : fireballs) {
            int nr = (N + fireball.r + (deltas[fireball.d][0] * (fireball.s) % N)) % N;
            int nc = (N + fireball.c + (deltas[fireball.d][1] * (fireball.s) % N)) % N;
            map[nr][nc].add(new Fireball(nr, nc, fireball.m, fireball.s, fireball.d));
        }
        fireballs.clear();
    }

    private static ArrayList<Fireball> afterMove() {
        ArrayList<Fireball> temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 1) temp.addAll(getFireball(map[i][j]));
                else {
                    for (Fireball fireball : map[i][j]) {
                        temp.add(new Fireball(fireball));
                    }
                }
            }
        }
        return temp;
    }

    private static ArrayList<Fireball> getFireball(ArrayList<Fireball> fireballs) {
        ArrayList<Fireball> list = new ArrayList<>();
        int totalM = 0, totalS = 0;
        boolean odd = false, even = false;
        for (Fireball fireball : fireballs) {
            totalM += fireball.m;
            totalS += fireball.s;
            if (fireball.d % 2 != 0) odd = true;
            else even = true;
        }
        for (int i = 0; i < 4; i++) {
            int r = fireballs.get(0).r;
            int c = fireballs.get(0).c;
            int m = totalM / 5;
            if (m == 0) break;
            Fireball fireball = new Fireball(r, c, m, totalS / fireballs.size(), (odd ^ even ? (i * 2) : (i * 2) + 1));
            list.add(fireball);
        }
        return list;
    }

    private static class Fireball {
        /*
         * r: 행, c: 열
         * m: 질량, s: 속력, d: 방향
         */
        int r, c, m, s, d;

        public Fireball(Fireball fireball) {
            this.r = fireball.r;
            this.c = fireball.c;
            this.m = fireball.m;
            this.s = fireball.s;
            this.d = fireball.d;
        }

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + m + ", " + s + ", " + d + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_20056().solution();
    }
}
