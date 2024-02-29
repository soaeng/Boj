package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    우체국
    https://www.acmicpc.net/problem/2141
*/
public class Boj_2141 {

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Town> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        long total = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken());
            int population = Integer.parseInt(st.nextToken());
            pq.offer(new Town(position, population));
            total += population;
        }

        total = total % 2 == 0 ? total / 2 : (total + 1) / 2;
        long ans = 0;
        while (!pq.isEmpty() && total > 0) {
            Town town = pq.poll();
            total -= town.population;
            ans = town.position;
        }
        System.out.println(ans);
    }

    private static class Town implements Comparable<Town> {
        int position, population;

        public Town(int position, int population) {
            this.position = position;
            this.population = population;
        }

        @Override
        public int compareTo(Town o) {
            return this.position - o.position;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
