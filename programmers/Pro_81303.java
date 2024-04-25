package programmers;

import java.util.ArrayDeque;

/*
    표 편집
    https://school.programmers.co.kr/learn/courses/30/lessons/81303
*/
public class Pro_81303 {
    public String solution(int n, int k, String[] cmd) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int count = n;
        for (String str : cmd) {
            switch (str.charAt(0)) {
                case 'U':
                    k -= Integer.parseInt(str.split(" ")[1]);
                    break;
                case 'D':
                    k += Integer.parseInt(str.split(" ")[1]);
                    break;
                case 'C':
                    stack.offerFirst(k);
                    count--;
                    if (k == count) k--;
                    break;
                case 'Z':
                    count++;
                    if (stack.pollFirst() <= k) k++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && i == stack.peekFirst()) {
                stack.pollFirst();
                sb.append("X");
            } else sb.append("O");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
//        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(new Pro_81303().solution(n, k, cmd));
    }
}
