package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
    베스트셀러
    https://www.acmicpc.net/problem/1302
*/
public class Boj_1302 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);
            max = Math.max(max, map.get(book));
        }
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) pq.offer(entry.getKey());
        }
        System.out.println(pq.poll());
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
