package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    피아의 아틀리에 ~신비한 대회의 연금술사~
    https://www.acmicpc.net/problem/15898
*/
public class Boj_15898_Re {
    static int N, max;
    static Ingredient[] ingredients;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        ingredients = new Ingredient[N];
        for (int i = 0; i < N; i++) {
            Ingredient ingredient = new Ingredient();
            for (int j = 0; j < 4; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    ingredient.info[0][0][j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for (int j = 0; j < 4; j++) {
                String str = br.readLine();
                for (int k = 0, idx = 0; k < 4; k++, idx += 2) {
                    switch (str.charAt(idx)) {
                        case 'R':
                            ingredient.info[1][0][j][k] = 7;
                            break;
                        case 'B':
                            ingredient.info[1][0][j][k] = 5;
                            break;
                        case 'G':
                            ingredient.info[1][0][j][k] = 3;
                            break;
                        case 'Y':
                            ingredient.info[1][0][j][k] = 2;
                            break;
                    }
                }
            }
            ingredients[i] = ingredient;
        }
        rotateIngredient();
        perm(new int[2][5][5], new boolean[N], 0);
        System.out.println(max);
    }

    private static void rotateIngredient() {
        for (Ingredient ingredient : ingredients) {
            for (int d = 1; d < 4; d++) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        ingredient.info[0][d][i][j] = ingredient.info[0][d - 1][3 - j][i];
                        ingredient.info[1][d][i][j] = ingredient.info[1][d - 1][3 - j][i];
                    }
                }
            }
        }
    }

    private static void perm(int[][][] gama, boolean[] selected, int cnt) {
        if (cnt == 3) {
            max = Math.max(max, totalGama(gama));
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                for (int r = 0; r < 2; r++) {
                    for (int c = 0; c < 2; c++) {
                        for (int d = 0; d < 4; d++) {
                            int[][][] temp = setGama(i, r, c, d, gama);
                            perm(temp, selected, cnt + 1);
                        }
                    }
                }
                selected[i] = false;
            }
        }
    }

    private static int totalGama(int[][][] gama) {
        int total = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                total += gama[0][r][c] * gama[1][r][c];
            }
        }
        return total;
    }

    private static int[][][] setGama(int idx, int r, int c, int d, int[][][] gama) {
        int[][][] temp = new int[2][5][5];
        Ingredient ingredient = ingredients[idx];
        for (int e = 0; e < 2; e++) {
            for (int i = 0; i < 5; i++) {
                System.arraycopy(gama[e][i], 0, temp[e][i], 0, 5);
            }
        }
        for (int i = r; i < r + 4; i++) {
            for (int j = c; j < c + 4; j++) {
                temp[0][i][j] = sumEfficacy(temp[0][i][j], ingredient.info[0][d][i - r][j - c]);
                if (ingredient.info[1][d][i - r][j - c] == 0) continue;
                temp[1][i][j] = ingredient.info[1][d][i - r][j - c];
            }
        }
        return temp;
    }

    private static void printIngredients() {
        StringBuilder sb = new StringBuilder();
        for (Ingredient i : ingredients) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static int sumEfficacy(int a, int b) {
        if (a + b < 0) return 0;
        return Math.min(a + b, 9);
    }

    private static class Ingredient {
        int[][][][] info; // int[0?효능:원소][방향][행][열]

        public Ingredient() {
            this.info = new int[2][4][4][4];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                for (int d = 0; d < 4; d++) {
                    for (int j = 0; j < 4; j++) {
                        if (info[0][d][i][j] >= 0) sb.append(" ");
                        sb.append(info[0][d][i][j]).append(info[1][d][i][j]).append(" ");
                    }
                    sb.append(" || ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15898_Re().solution();
    }
}
