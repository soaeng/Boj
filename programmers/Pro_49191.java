package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
    순위
    https://school.programmers.co.kr/learn/courses/30/lessons/49191
 */
public class Pro_49191 {
    static ArrayList<Integer>[] win;
    static Player[] players;

    public int solution(int n, int[][] results) {
        int answer = 0;
        win = new ArrayList[n + 1];
        players = new Player[n + 1];
        for (int i = 1; i <= n; i++) {
            win[i] = new ArrayList<>();
            players[i] = new Player(0, 0);
        }
        int r = results.length;
        for (int[] result : results) {
            win[result[0]].add(result[1]);
        }
        for (int i = 1; i <= n; i++) {
            bfs(n, i);
        }
        for (int i = 1; i <= n; i++) {
            if (players[i].win + players[i].lose == n - 1) answer++;
        }
        return answer;
    }

    static void bfs(int n, int player) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(player);
        visited[player] = true;

        while (!queue.isEmpty()) {
            int winner = queue.poll();
            for (int p : win[winner]) {
                if (!visited[p]) {
                    queue.offer(p);
                    visited[p] = true;
                    players[player].win++;
                    players[p].lose++;
                }
            }
        }
    }

    static class Player {
        int win, lose;

        public Player(int win, int lose) {
            this.win = win;
            this.lose = lose;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        new Pro_49191().solution(n, results);
    }
}
