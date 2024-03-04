package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    아주 서바이벌
    https://www.acmicpc.net/problem/23293
*/
public class Boj_23293 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        st.nextToken();
        Set<Integer> blackList = new TreeSet<>();
        ArrayList<Integer> logs = new ArrayList<>();
        Map<Integer, Player> map = new HashMap<>();
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int seq = Integer.parseInt(st.nextToken());
            Player player = map.getOrDefault(seq, new Player(1));
            switch (st.nextToken()) {
                case "M":
                    player.position = Integer.parseInt(st.nextToken());
                    break;
                case "F":
                    int item = Integer.parseInt(st.nextToken());
                    if (player.position != item) logs.add(t);
                    player.items.put(item, player.items.getOrDefault(item, 0) + 1);
                    break;
                case "C":
                    int item1 = Integer.parseInt(st.nextToken());
                    int item2 = Integer.parseInt(st.nextToken());
                    if (!player.items.containsKey(item1) || !player.items.containsKey(item2)) {
                        logs.add(t);
                    }
                    player.items.put(item1, player.items.getOrDefault(item1, 1) - 1);
                    player.items.put(item2, player.items.getOrDefault(item2, 1) - 1);
                    if (player.items.get(item1) <= 0) player.items.remove(item1);
                    if (player.items.get(item2) <= 0) player.items.remove(item2);
                    break;
                case "A":
                    int tgtSeq = Integer.parseInt(st.nextToken());
                    Player tgt = map.getOrDefault(tgtSeq, new Player(1));
                    if (player.position != tgt.position) {
                        logs.add(t);
                        blackList.add(seq);
                    }
                    break;
            }
            map.put(seq, player);
        }
        StringBuilder sb = new StringBuilder();
        if (logs.isEmpty()) sb.append("0");
        else {
            sb.append(logs.size()).append("\n");
            for (int l : logs) sb.append(l).append(" ");
        }
        sb.append("\n");
        if (blackList.isEmpty()) sb.append("0");
        else {
            sb.append(blackList.size()).append("\n");
            for (int b : blackList) sb.append(b).append(" ");
        }
        System.out.println(sb);
    }

    private static class Player {
        int position;
        Map<Integer, Integer> items = new HashMap<>();

        public Player(int position) {
            this.position = position;
        }

    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
