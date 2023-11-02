package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    마법사 상어와 복제
    https://www.acmicpc.net/problem/23290
*/
public class Boj_23290 {
    static int M, S, sharkR, sharkC, max;
    static int[][] deltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] deltas2 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static ArrayList<Fish> fishes;
    static ArrayList<Fish>[][] map;
    static int[] perm, sharkDir;
    static int[][] smell;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new ArrayList[4][4];
        smell = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        fishes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fishes.add(new Fish(x, y, d));
        }
        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken()) - 1;
        sharkC = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < S; i++) {
            init();
            ArrayList<Fish> temp = backup(fishes);
            fishes = moveFish();
            getSharkDir(0);
            moveShark();
            decreaseFishSmell();
            copyFish(temp);
        }
        System.out.println(fishes.size());
    }

    private static ArrayList<Fish> moveFish() {
        ArrayList<Fish> temp = new ArrayList<>();
        for (Fish fish : fishes) {
            int nr = fish.r;
            int nc = fish.c;
            int nd = fish.d + 1;
            boolean isMoved = false;
            for (int d = 0; d < 8; d++) {
                nd = getFishDir(nd);
                nr = fish.r + deltas[nd][0];
                nc = fish.c + deltas[nd][1];
                if (isInRange(nr, nc) || isFishSmell(nr, nc) || isShark(nr, nc)) continue;
                isMoved = true;
                break;
            }
            if (isMoved) temp.add(new Fish(nr, nc, nd));
            else temp.add(new Fish(fish));
        }
        for (Fish fish : temp) map[fish.r][fish.c].add(fish);
        return temp;
    }

    private static void getSharkDir(int cnt) {
        if (cnt == 3) {
            int count = getMaxCount();
            if (count == -1) {
                return;
            } else {
                if (count > max) {
                    max = count;
                    System.arraycopy(perm, 0, sharkDir, 0, 3);
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            perm[cnt] = i;
            getSharkDir(cnt + 1);
        }
    }

    private static int getMaxCount() {
        int count = 0;
        boolean[][] visited = new boolean[4][4];
        int nr = sharkR;
        int nc = sharkC;
        for (int d : perm) {
            nr += deltas2[d][0];
            nc += deltas2[d][1];
            if (isInRange(nr, nc)) return -1;
            if (!visited[nr][nc]) {
                visited[nr][nc] = true;
                count += map[nr][nc].size();
            }
        }
        return count;
    }

    private static void moveShark() {
        for (int d : sharkDir) {
            sharkR += deltas2[d][0];
            sharkC += deltas2[d][1];
            if (map[sharkR][sharkC].size() > 0) {
                map[sharkR][sharkC].clear();
                smell[sharkR][sharkC] = 3;
            }
        }
        removedFish();
    }

    private static void removedFish() {
        fishes = new ArrayList<>();
        for (ArrayList<Fish>[] mm : map) {
            for (ArrayList<Fish> m : mm) {
                for (Fish fish : m) {
                    fishes.add(new Fish(fish));
                }
            }
        }
    }

    private static void init() {
        perm = new int[]{-1, -1, -1};
        sharkDir = new int[3];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j].clear();
            }
        }
    }

    private static void decreaseFishSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0) smell[i][j]--;
            }
        }
    }

    private static void copyFish(ArrayList<Fish> temp) {
        for (Fish fish : temp) fishes.add(new Fish(fish));
    }

    private static ArrayList<Fish> backup(ArrayList<Fish> fishes) {
        ArrayList<Fish> copy = new ArrayList<>();
        for (Fish fish : fishes) copy.add(new Fish(fish));
        return copy;
    }

    private static boolean isShark(int r, int c) {
        return r == sharkR && c == sharkC;
    }

    private static boolean isFishSmell(int r, int c) {
        return smell[r][c] != 0;
    }

    private static int getFishDir(int d) {
        return (d + 8 - 1) % 8;
    }

    private static boolean isInRange(int r, int c) {
        return r < 0 || r >= 4 || c < 0 || c >= 4;
    }

    private static class Fish {
        int r, c, d;

        public Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public Fish(Fish fish) {
            this.r = fish.r;
            this.c = fish.c;
            this.d = fish.d;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + d + ")";
        }

    }

    public static void main(String[] args) throws Exception {
        new Boj_23290().solution();
    }
}
