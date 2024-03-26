package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    올림픽
    https://www.acmicpc.net/problem/8979
*/
public class Boj_8979 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Nation> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int seq = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            list.add(new Nation(seq, gold, silver, bronze));
        }
        Collections.sort(list);
        int idx = 1;
        int count = 1;

        for (int i = 0; i < N; i++) {

            if (i > 0) {
                Nation n1 = list.get(i);
                Nation n2 = list.get(i - 1);
                if (n1.gold == n2.gold && n1.silver == n2.silver && n1.bronze == n2.bronze) {
                    count++;
                } else {
                    idx += count;
                    count = 1;
                }
            }
            if (list.get(i).seq == K) {
                System.out.println(idx);
                break;
            }
        }
    }

    private static class Nation implements Comparable<Nation> {
        int seq, gold, silver, bronze;

        public Nation(int seq, int g, int s, int b) {
            this.seq = seq;
            gold = g;
            silver = s;
            bronze = b;
        }

        @Override
        public int compareTo(Nation o) {
            if (this.gold == o.gold && this.silver == o.silver) {
                return o.bronze - this.bronze;
            } else if (this.gold == o.gold) {
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
