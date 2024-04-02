package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    치킨 배달
    https://www.acmicpc.net/problem/15686
*/
public class Boj_15686_2 {
    static int N, M, min;
    static ArrayList<Node> house, store, tgt;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        house = new ArrayList<>();
        store = new ArrayList<>();
        tgt = new ArrayList<>();
        min = Integer.MAX_VALUE;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                switch (st.nextToken().charAt(0)) {
                    case '1':
                        house.add(new Node(r, c));
                        break;
                    case '2':
                        store.add(new Node(r, c));
                        break;
                }
            }
        }
        comb(0, 0);
        System.out.println(min);
    }

    private static void comb(int depth, int start) {
        if (depth == M) {
            int sum = 0;
            for (Node h : house) {
                int dist = Integer.MAX_VALUE;
                for (Node t : tgt) {
                    dist = Math.min(dist, Math.abs(h.r - t.r) + Math.abs(h.c - t.c));
                }
                sum += dist;
            }
            min = Math.min(min, sum);
            return;
        }
        if (start == store.size()) return;
        tgt.add(store.get(start));
        comb(depth + 1, start + 1);
        tgt.remove(store.get(start));
        comb(depth, start + 1);
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
