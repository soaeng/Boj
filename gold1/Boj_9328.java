package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    열쇠
    https://www.acmicpc.net/problem/9328
*/
public class Boj_9328 {
    static int h, w, count;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static HashSet<Character> keys;
    static Queue<Node> queue;
    static ArrayList<Node> door;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            queue = new LinkedList<>();
            keys = new HashSet<>();
            door = new ArrayList<>();
            count = 0;
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }
            String str = br.readLine();
            if (!str.equals("0")) {
                for (int i = 0; i < str.length(); i++) {
                    keys.add(str.charAt(i));
                }
            }
            findEntrance();
            bfs();
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (map[node.r][node.c] == '$') count++;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != '*') {
                    if (map[nr][nc] >= 'a') {
                        keys.add(map[nr][nc]);
                        openDoor();
                    }
                    if (map[nr][nc] >= 'A' && map[nr][nc] < 'a')
                        if (!canOpen(map[nr][nc])) {
                            door.add(new Node(nr, nc));
                            continue;
                        }
                    queue.offer(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static void findEntrance() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                    if (map[i][j] == '.') {
                        queue.offer(new Node(i, j));
                        visited[i][j] = true;
                    } else if (map[i][j] >= 97) {
                        queue.offer(new Node(i, j));
                        keys.add(map[i][j]);
                        visited[i][j] = true;
                    } else if (map[i][j] >= 65 && map[i][j] < 97) {
                        if (canOpen(map[i][j])) {
                            queue.offer(new Node(i, j));
                            visited[i][j] = true;
                        } else door.add(new Node(i, j));
                    } else if (map[i][j] == '$') {
                        queue.offer(new Node(i, j));
                        visited[i][j] = true;
                    }
                }
                if (map[i][j] == '*') visited[i][j] = true;
            }
        }
    }


    private static boolean canOpen(char door) {
        return keys.contains((char) (door + 32));
    }

    private static void openDoor() {
        int size = door.size();
        for (int i = 0; i < size; i++) {
            Node node = door.get(i);
            if (canOpen(map[node.r][node.c])) {
                queue.offer(new Node(node.r, node.c));
                visited[node.r][node.c] = true;
                door.remove(i);
                i--;
                size--;
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    private static void printMap() {
        for (char[] mm : map) {
            for (char m : mm) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_9328().solution();
    }
}
