package level3;

import java.util.Stack;

/*
    표 편집
    https://school.programmers.co.kr/learn/courses/30/lessons/81303
*/
public class Pro_81303 {

    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();
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
                    stack.push(k);
                    count--;
                    if (k == count) k--;
                    break;
                case 'Z':
                    count++;
                    if (stack.pop() <= k) k++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("O".repeat(Math.max(0, count)));
        while (!stack.isEmpty()) {
            sb.insert(stack.pop(), "X");
        }
        System.out.println(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 8, k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        // OOOOXOOO

//        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        // OOXOXOOO
        new Pro_81303().solution(n, k, cmd);
    }
}
