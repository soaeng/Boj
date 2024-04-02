package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    치킨 배달
    https://www.acmicpc.net/problem/15686
*/
public class Boj_15686 {
    static int N, M, min;
    static ArrayList<int[]> house, store, tgt;

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
                        house.add(new int[]{r, c});
                        break;
                    case '2':
                        store.add(new int[]{r, c});
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
            for (int[] h : house) {
                int dist = Integer.MAX_VALUE;
                for (int[] t : tgt) {
                    dist = Math.min(dist, Math.abs(h[0] - t[0]) + Math.abs(h[1] - t[1]));
                }
                sum += dist;
            }
            min = Math.min(min, sum);
            return;
        }
        for (int i = start; i < store.size(); i++) {
            tgt.add(store.get(i));
            comb(depth + 1, i + 1);
            tgt.remove(store.get(i));
        }
    }


    public static void main(String[] args) throws Exception {
        solution();
    }
}
