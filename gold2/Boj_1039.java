package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    교환
    https://www.acmicpc.net/problem/1039
*/
public class Boj_1039 {
    static String N;
    static int K, MAX;
    static HashSet<String> visited;
    static Queue<Number> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        MAX = -1;
        queue = new LinkedList<>();
        queue.offer(new Number(N, 0));
        for (int i = 0; i < K; i++) {
            visited = new HashSet<>();
            bfs();
        }
        System.out.println(MAX);
    }

    private static void bfs() {
        int length = N.length();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Number number = queue.poll();
            for (int j = 0; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    String num = number.num;
                    if (j == 0 && num.charAt(k) == '0') continue;
                    String swap = swapDigit(num, j, k);
                    if (visited.contains(swap)) continue;
                    queue.offer(new Number(swap, number.cnt + 1));
                    visited.add(swap);
                    if (number.cnt == K - 1) MAX = Math.max(MAX, Integer.parseInt(swap));
                }
            }
        }
    }

    private static class Number {
        String num;
        int cnt;

        public Number(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    private static String swapDigit(String str, int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        new Boj_1039().solution();
    }
}
