package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/*
    단어 정렬
    https://www.acmicpc.net/problem/1181
*/
public class Boj_1181 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> tree = new TreeSet<>((o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });

//        Set<String> set = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() == o2.length()) return o1.compareTo(o2);
//                else return o1.length() - o2.length();
//            }
//        });
//
        for (int i = 0; i < N; i++) {
            tree.add(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        for (String str : tree) sb.append(str).append("\n");

        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
