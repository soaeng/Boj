package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    가운데를 말해요
    https://www.acmicpc.net/problem/1655
*/
public class Boj_1655 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (minHeap.size() == maxHeap.size()) {
                if (!minHeap.isEmpty() && num > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(num);
                } else maxHeap.offer(num);
            } else {
                if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                } else minHeap.offer(num);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
