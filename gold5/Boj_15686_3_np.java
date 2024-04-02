package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    치킨 배달
    https://www.acmicpc.net/problem/15686
*/
public class Boj_15686_3_np {
    static int N, M, min;
    static ArrayList<Node> house, store;
    static int[] index; // np 배열

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        house = new ArrayList<>();
        store = new ArrayList<>();
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
        index = new int[store.size()];
        // 뒤에서부터 1씩 M개만큼 채워줌 (가장 작은 수를 표현)
        for (int i = 0; i < M; i++) {
            index[(index.length - i) - 1] = 1;
        }

        while (true) {
            int sum = 0;
            for (Node h : house) {
                int dist = Integer.MAX_VALUE;
                for (int i = 0; i < index.length; i++) {
                    if (index[i] == 1)
                        dist = Math.min(dist, Math.abs(h.r - store.get(i).r) + Math.abs(h.c - store.get(i).c));
                }
                sum += dist;
            }
            min = Math.min(min, sum);
            if (!np()) break;
        }

        System.out.println(min);
    }

    static boolean np() {
        int[] src = index;

        int i = src.length - 1;
        while (i > 0 && src[i - 1] >= src[i]) --i;

        if (i == 0) return false;//이보다 더 큰 수는 없다.

        int j = src.length - 1;
        while (src[i - 1] >= src[j]) --j;
        swap(src, i - 1, j);

        int k = src.length - 1;
        while (i < k) swap(src, i++, k--);
        return true;
    }

    static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
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
