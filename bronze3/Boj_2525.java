package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    오븐 시계
    https://www.acmicpc.net/problem/2525
*/
public class Boj_2525 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int hour = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken()) + Integer.parseInt(br.readLine());
        sb.append((hour + minutes / 60) % 24).append(" ").append(minutes % 60);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2525().solution();
    }
}
