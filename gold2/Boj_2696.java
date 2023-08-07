package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    중앙값 구하기
    https://www.acmicpc.net/problem/2696
*/
public class Boj_2696 {
    static int T, M;
    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        int index;
        for (int t = 0; t < T; t++) {
            M = Integer.parseInt(br.readLine());
            index = 0;
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            sb.append(M % 2 == 0 ? M / 2 : (M + 1) / 2).append("\n");
            M = M % 10 == 0 ? M / 10 : M / 10 + 1;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    minHeap.offer(Integer.parseInt(st.nextToken()));
                    if (maxHeap.size() < minHeap.size()) maxHeap.offer(minHeap.poll());
                    if (index == 0) sb.append(maxHeap.peek()).append(" ");
                    if (index % 2 == 0 && index != 0) {
                        while (maxHeap.peek() > minHeap.peek()) {
                            minHeap.offer(maxHeap.poll());
                            maxHeap.offer(minHeap.poll());
                        }
                        sb.append(maxHeap.peek()).append(" ");
                    }
                    index++;
                    if (index % 20 == 0 && index / 10 > 1) {
                        sb.append("\n");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2696().solution();
    }
}
