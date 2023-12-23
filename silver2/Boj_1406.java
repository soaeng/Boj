package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

/*
    에디터
    https://www.acmicpc.net/problem/1406
*/
public class Boj_1406 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        LinkedList<Character> list = new LinkedList<>();
        ListIterator<Character> iter = list.listIterator();
        for (int i = 0; i < str.length(); i++) {
            iter.add(str.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
                case 'L':
                    if (iter.previousIndex() == -1) break;
                    iter.previous();
                    break;
                case 'D':
                    if (iter.nextIndex() == list.size()) break;
                    iter.next();
                    break;
                case 'B':
                    if (iter.previousIndex() == -1) break;
                    iter.previous();
                    iter.remove();
                    break;
                case 'P':
                    iter.add(command.charAt(2));
                    break;
            }
        }
        for (char ch : list) {
            sb.append(ch);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1406().solution();
    }
}
