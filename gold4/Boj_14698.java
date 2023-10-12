package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    전생했더니 슬라임 연구자였던 건에 대하여 (Hard)
    https://www.acmicpc.net/problem/14698

*/
public class Boj_14698 {
    static int N;
    static PriorityQueue<Long> slime;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            slime = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                slime.offer(Long.parseLong(st.nextToken()));
            }
            long ans = 1;
            while (slime.size() > 1) {
                long a = slime.poll();
                long b = slime.poll();
                long c = a * b;
                ans = (ans * (c % 1000000007)) % 1000000007;
                slime.offer(c);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_14698().solution();
    }
}
