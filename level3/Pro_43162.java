package level3;

/*
    네트워크
    https://school.programmers.co.kr/learn/courses/30/lessons/43162?language=java
*/
public class Pro_43162 {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(n, computers, visited, i);
                answer++;
            }
        }
        System.out.println(answer);
        return answer;
    }

    static void dfs(int n, int[][] computers, boolean[] visited, int c) {
        visited[c] = true;
        for (int i = 0; i < n; i++) {
            if (computers[c][i] == 1 && !visited[i]) dfs(n, computers, visited, i);
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 3;
//        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        new Pro_43162().solution(n, computers);
    }
}
