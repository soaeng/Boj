package level3;

import java.util.ArrayList;
import java.util.Stack;

/*
    표 편집
    https://school.programmers.co.kr/learn/courses/30/lessons/81303
*/
public class Pro_81303_re {

    public String solution(int n, int k, String[] cmd) {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
            if (i == 0) continue;
            nodes.get(i - 1).next = nodes.get(i);
            nodes.get(i).prev = nodes.get(i - 1);
        }
        Node current = nodes.get(k);
        for (String str : cmd) {
            switch (str.charAt(0)) {
                case 'U':
                    current = current.moveUp(Integer.parseInt(str.split(" ")[1]));
                    break;
                case 'D':
                    current = current.moveDown(Integer.parseInt(str.split(" ")[1]));
                    break;
                case 'C':
                    stack.push(current);
                    current = current.delete();
                    break;
                case 'Z':
                    stack.pop().restore();
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!nodes.get(i).removed) sb.append("O");
            else sb.append("X");
        }
        System.out.println(sb);
        return sb.toString();
    }

    private static class Node {
        int data;
        Node prev, next;
        boolean removed;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
            this.removed = false;
        }

        public Node moveUp(int r) {
            Node node = this;
            while (r-- > 0) {
                node = node.prev;
            }
            return node;
        }

        public Node moveDown(int r) {
            Node node = this;
            while (r-- > 0) {
                node = node.next;
            }
            return node;
        }

        public Node delete() {
            Node prev = this.prev;
            Node next = this.next;
            this.removed = true;
            if (prev != null) prev.next = next;
            if (next != null) next.prev = prev;
            return next != null ? next : prev;
        }

        public void restore() {
            Node prev = this.prev;
            Node next = this.next;
            this.removed = false;
            if (prev != null) prev.next = this;
            if (next != null) next.prev = this;
        }

        @Override
        public String toString() {
            return this.data + "(" + (this.removed ? "X" : "O") + ")";
        }
    }

    public static void main(String[] args) {
        int n = 8, k = 2;
//        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        // OOOOXOOO

        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        // OOXOXOOO
        new Pro_81303_re().solution(n, k, cmd);
    }
}
