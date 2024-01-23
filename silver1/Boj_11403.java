package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    경로 찾기
    https://www.acmicpc.net/problem/11403
*/
public class Boj_11403 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0, idx = 0; j < N; j++, idx += 2) {
                if (input.charAt(idx) == '1') graph[i][j] = true;
            }
        }
        //s->k->e 갈 수 있으면 s->e 갈 수 있는 경로 존재
        for (int k = 0; k < N; k++) { // 경유 노드
            for (int s = 0; s < N; s++) { // 출발 노드
                for (int e = 0; e < N; e++) { // 도착 노드
                    if (graph[s][k] && graph[k][e]) graph[s][e] = true;
                }
            }
        }
        for (boolean[] bb : graph) {
            for (boolean b : bb) {
                sb.append(b ? 1 : 0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_11403().solution();
    }
}
