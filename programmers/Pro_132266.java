package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/*  
    부대복귀
    https://school.programmers.co.kr/learn/courses/30/lessons/132266
*/
public class Pro_132266 {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] dist;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dist = new int[n];
        graph = new ArrayList<>();
        int[] answer = new int[sources.length];
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] road : roads) {
            graph.get(road[0] - 1).add(road[1] - 1);
            graph.get(road[1] - 1).add(road[0] - 1);
        }
        dijkstra(destination - 1);
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i] - 1] == INF) answer[i] = -1;
            else answer[i] = dist[sources[i] - 1];
        }
        return answer;
    }

    private static void dijkstra(int destination) {
        Queue<Integer> queue = new ArrayDeque<>();
        Arrays.fill(dist, INF);
        queue.offer(destination);
        dist[destination] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : graph.get(node)) {
                if (dist[next] > dist[node] + 1) {
                    dist[next] = dist[node] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[][] roads = {{1, 2}, {2, 3}};
//        int[] sources = {2, 3};
//        int destination = 1;

        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;
        System.out.println(Arrays.toString(new Pro_132266().solution(n, roads, sources, destination)));
    }
}
