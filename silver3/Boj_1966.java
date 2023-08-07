package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    프린터 큐
    https://www.acmicpc.net/problem/1966
 */
public class Boj_1966 {
    static int T, N, M, cnt;
    static Queue<Document> queue;
    static Document document;
    static int[] priority;
    static boolean finish;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            queue = new LinkedList<>();
            priority = new int[9];
            finish = false;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int p;
            for (int i = 0; i < N; i++) {
                p = Integer.parseInt(st.nextToken());
                priority[p - 1]++;
                Document d = new Document(i, p);
                queue.add(d);
                if (i == M) document = d;
            }
            cnt = 0;
            for (int i = 8; i >= 0; i--) {
                if (priority[i] == 0) continue;
                if (finish) break;
                p = priority[i];
                while (p > 0) {
                    Document d = queue.poll();
                    if (d.priority == (i + 1)) {
                        cnt++;
                        if (d == document) {
                            sb.append(cnt).append("\n");
                            finish = true;
                            break;
                        }
                        p--;
                    } else if (d.priority != i + 1) {
                        queue.offer(d);
                    }
                }
            }
        }
        System.out.println(sb);
    }

    private static class Document {
        int order;
        int priority;

        public Document(int order, int priority) {
            this.order = order;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_1966().solution();
    }
}
