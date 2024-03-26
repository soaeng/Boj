package level3;

import java.util.ArrayList;
import java.util.Collections;

/*
    여행경로
    https://school.programmers.co.kr/learn/courses/30/lessons/43164
*/
public class Pro_43164 {
    static boolean[] visited;
    static ArrayList<String> list;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        list = new ArrayList<>();
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(list);
        System.out.println(list.get(0));
        return list.get(0).split(",");
    }

    private static void dfs(int depth, String start, String route, String[][] tickets) {
        if (depth == tickets.length) {
            list.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;
            if (!tickets[i][0].equals(start)) continue;
            visited[i] = true;
            dfs(depth + 1, tickets[i][1], route + "," + tickets[i][1], tickets);
            visited[i] = false;
        }
    }


    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] tickets = {{"ICN", "A"}, {"A", "B"}, {"B", "A"}, {"A", "ICN"}, {"ICN", "A"}};
        String[][] tickets = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}};
//        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        new Pro_43164().solution(tickets);
    }
}
