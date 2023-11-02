package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    감시
    https://www.acmicpc.net/problem/15683
*/
public class Boj_15683 {
    static int N, M;
    static int[][] map;
    static int[][] deltas = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[][] camDeltas = {{1, 0, 0, 0}, {1, 0, 1, 0}, {1, 1, 0, 0}, {1, 1, 1, 0}, {1, 1, 1, 1}};
    static int[] caseNum;
    static ArrayList<Camera> cameras;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        caseNum = new int[]{4, 2, 4, 4, 1};
        cameras = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) cameras.add(new Camera(i, j, map[i][j] - 1));
            }
        }
        System.out.println(solve(0));
    }

    private static int solve(int cnt) {
        if (cnt == cameras.size()) {
            return getCount();
        }
        int[][] temp = new int[N][M];
        int ans = Integer.MAX_VALUE;
        int type = cameras.get(cnt).type;
        for (int d = 0; d < caseNum[type]; d++) {
            copyMap(temp, map);
            for (int i = 0; i < 4; i++) {
                if (camDeltas[type][i] == 1) getView(cameras.get(cnt), (d + i) % 4);
            }
            ans = Math.min(ans, solve(cnt + 1));
            copyMap(map, temp);
        }
        return ans;
    }

    private static void getView(Camera camera, int d) {
        int r = camera.r;
        int c = camera.c;
        while (true) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isInRange(nr, nc) || map[nr][nc] == 6) return;
            map[nr][nc] = 9;
            r = nr;
            c = nc;
        }
    }

    private static void copyMap(int[][] dst, int[][] src) {
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(src[i], 0, dst[i], 0, M);
        }
    }

    private static int getCount() {
        int count = 0;
        for (int[] mm : map) {
            for (int m : mm) {
                if (m == 0) count++;
            }
        }
        return count;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Camera {
        int r, c, type;

        public Camera(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + type + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15683().solution();
    }
}
