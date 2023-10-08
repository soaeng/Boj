package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    낚시왕
    https://www.acmicpc.net/problem/17143
*/
public class Boj_17143 {
    static int R, C, M;
    static int[][] map;
    static Map<Integer, Shark> sharks;
    static Queue<Shark> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            sharks.put(i + 1, new Shark(i + 1, r, c, s, d, z));
            map[r][c] = i + 1;
        }
        int ans = 0;
        for (int i = 0; i < C; i++) {
            ans += catchShark(i);
            moveShark();
            relocation();
        }
        System.out.println(ans);
    }

    private static void moveShark() {
        queue = new LinkedList<>();
        int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 상하우좌
        for (Integer key : sharks.keySet()) {
            Shark shark = sharks.get(key);
            map[shark.r][shark.c] = 0;
            int move = setMovement(shark.s, shark.d);
            for (int i = 0; i < move; i++) {
                int nr = shark.r + deltas[shark.d][0];
                int nc = shark.c + deltas[shark.d][1];
                if (!checkRange(nr, nc)) {
                    if (shark.d < 2) shark.d = shark.d == 0 ? 1 : 0;
                    else shark.d = shark.d == 2 ? 3 : 2;
                    i--;
                    continue;
                }
                shark.r = nr;
                shark.c = nc;
            }
            queue.offer(shark);
        }
    }

    private static int setMovement(int s, int d) {
        if (d < 2) return s % ((R - 1) * 2);
        else return s % ((C - 1) * 2);
    }

    private static void relocation() {
        map = new int[R][C];
        while (!queue.isEmpty()) {
            Shark shark = queue.poll();
            if (map[shark.r][shark.c] != 0) {
                eatShark(sharks.get(map[shark.r][shark.c]), shark);
            } else map[shark.r][shark.c] = shark.seq;
        }
    }

    private static void eatShark(Shark a, Shark b) {
        if (a.z < b.z) {
            map[a.r][a.c] = b.seq;
            sharks.remove(a.seq);
        } else sharks.remove(b.seq);
    }

    private static int catchShark(int c) {
        for (int i = 0; i < R; i++) {
            if (map[i][c] != 0) {
                int size = sharks.get(map[i][c]).z;
                sharks.remove(map[i][c]);
                map[i][c] = 0;
                return size;
            }
        }
        return 0;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static class Shark {
        /**
         * r: 행<br/>c: 열<br/>s: 속력<br/>d: 이동 방향(1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽)<br/>z: 크기<br/>
         */
        int seq, r, c, s, d, z;

        public Shark(int seq, int r, int c, int s, int d, int z) {
            this.seq = seq;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + '}';
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_17143().solution();
    }
}
