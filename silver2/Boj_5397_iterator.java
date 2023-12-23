package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

/*
    키로거
    https://www.acmicpc.net/problem/5397
*/
public class Boj_5397_iterator {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String input = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iter = list.listIterator();
            for (int i = 0; i < input.length(); i++) {
                switch (input.charAt(i)) {
                    case '<':
                        if (iter.previousIndex() == -1) break;
                        iter.previous();
                        break;
                    case '>':
                        if (iter.nextIndex() == list.size()) break;
                        iter.next();
                        break;
                    case '-':
                        if (iter.previousIndex() == -1) break;
                        iter.previous();
                        iter.remove();
                        break;
                    default:
                        iter.add(input.charAt(i));
                }
            }
            for (Character ch : list) {
                sb.append(ch);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_5397_iterator().solution();
    }
}
