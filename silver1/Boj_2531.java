package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
    회전 초밥
    https://www.acmicpc.net/problem/2531
*/
public class Boj_2531 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int max = map.containsKey(c) ? map.size() : map.size() + 1;
        for (int i = 0; i < N; i++) {
            map.put(arr[i], map.get(arr[i]) - 1);
            if (map.get(arr[i]) == 0) map.remove(arr[i]);
            map.put(arr[(i + k) % N], map.getOrDefault(arr[(i + k) % N], 0) + 1);
            max = Math.max(max, map.containsKey(c) ? map.size() : map.size() + 1);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
