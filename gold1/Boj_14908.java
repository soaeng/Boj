package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    구두 수선공
    https://www.acmicpc.net/problem/14908
*/
public class Boj_14908 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Work> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Work(i, day, cost));
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (Work work : list) sb.append(work.id).append(" ");
        System.out.println(sb);
    }

    private static class Work implements Comparable<Work> {
        int id, day, cost;

        public Work(int id, int day, int cost) {
            this.id = id;
            this.day = day;
            this.cost = cost;
        }

        @Override
        public int compareTo(Work work) {
            return (this.day * work.cost) - (work.day * this.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
