package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    상어 중학교
    https://www.acmicpc.net/problem/21609
*/
public class Boj_21609 {
    static int N, M;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static Deque<Group> groups;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int score = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            groups = new ArrayDeque<>();
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > 0) findGroup(i, j);
                }
            }
            if (groups.isEmpty()) break;
            score += Math.pow(removeBlock(), 2);
            gravity();
            rotation();
            gravity();
        }

        System.out.println(score);
    }

    private static void gravity() {
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[j][i] < 0) continue;
                for (int k = j; k < N - 1; k++) {
                    if (map[k][i] < 0) continue;
                    if (map[k + 1][i] == -2) {
                        map[k + 1][i] = map[k][i];
                        map[k][i] = -2;
                    }
                }
            }
        }
    }

    private static void rotation() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - 1 - j][i] = map[i][j];
            }
        }
        map = temp;
    }

    private static int removeBlock() {
        Group group = groups.poll();
        for (int[] nn : group.blocks) {
            map[nn[0]][nn[1]] = -2;
        }
        return group.size;
    }

    private static void findGroup(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        int size = 0;
        ArrayList<int[]> rainbow = new ArrayList<>();
        ArrayList<int[]> blocks = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            blocks.add(node);
            size++;
            for (int d = 0; d < 4; d++) {
                int nr = node[0] + deltas[d][0];
                int nc = node[1] + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && (map[nr][nc] == map[r][c] || map[nr][nc] == 0)) {
                    if (map[nr][nc] == 0) rainbow.add(new int[]{nr, nc});
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        if (size >= 2) {
            Group newGroup = new Group(r, c, rainbow.size(), size, blocks);
            if (!groups.isEmpty() && groups.peek().compareTo(newGroup) < 0) {
                groups.offerFirst(newGroup);
            } else groups.offer(newGroup);
        }
        for (int[] nn : rainbow) visited[nn[0]][nn[1]] = false;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void printMap() {
        for (int[] mm : map) {
            for (int m : mm) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    private static class Group implements Comparable<Group> {
        int r, c, rainbow, size;
        ArrayList<int[]> blocks;

        public Group(int r, int c, int rainbow, int size, ArrayList<int[]> blocks) {
            this.r = r;
            this.c = c;
            this.rainbow = rainbow;
            this.size = size;
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + rainbow + ", " + size + ")";
        }

        @Override
        public int compareTo(Group o) {
            if (this.size != o.size) return this.size - o.size;
            if (this.rainbow != o.rainbow) return this.rainbow - o.rainbow;
            if (this.r != o.r) return this.r - o.r;
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_21609().solution();
    }
}
