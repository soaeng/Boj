package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    샘터
    https://www.acmicpc.net/problem/18513
*/
public class Boj_18513 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] deltas = {-1, 1};
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int position = Integer.parseInt(st.nextToken());
            queue.add(new Node(position, 0));
            visited.add(position);
        }
        long answer = 0;
        while (!queue.isEmpty() && K > 0) {
            Node node = queue.poll();
            for (int d = 0; d < 2; d++) {
                int np = node.position + deltas[d];
//                if (!isInRange(np)) continue;
                if (visited.contains(np)) continue;
                queue.offer(new Node(np, node.distance + 1));
                visited.add(np);
                answer += node.distance + 1;
                K--;
                if (K == 0) break;
            }
        }
        System.out.println(answer);
    }

    private static boolean isInRange(int p) {
        return p >= -100000000 && p <= 100000000;
    }
    // 집 지을 수 있는 범위를 지정하지 않아도 됨
    // https://www.acmicpc.net/board/view/56145


    private static class Node {
        int position, distance;

        public Node(int position, int distance) {
            this.position = position;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_18513().solution();
    }
}
