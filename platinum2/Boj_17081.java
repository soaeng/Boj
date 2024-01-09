package platinum2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    RPG Extreme
    https://www.acmicpc.net/problem/17081
*/
public class Boj_17081 {
    static int N, M, SR, SC;
    static char[][] map;
    static HashMap<String, Monster> monsters;
    static HashMap<String, Item> items;
    static Hero hero;
    static String killer;
    static int isFinished;

    public void solution() throws Exception {
        String command = input();
        int length = command.length();
        int t = 0;
        for (int i = 0; i < length; i++) {
            switch (command.charAt(i)) {
                case 'L':
                    move(0);
                    break;
                case 'R':
                    move(1);
                    break;
                case 'U':
                    move(2);
                    break;
                case 'D':
                    move(3);
                    break;
            }
            if (isFinished > 0 || i == length - 1) {
                t = i + 1;
                break;
            }
        }
        if (t == length && isFinished == 0) isFinished = 3;
        System.out.println(output(t));
    }

    private static void move(int dir) {
        int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int nr = hero.r + deltas[dir][0];
        int nc = hero.c + deltas[dir][1];
        // 범위를 벗어나거나 벽(#)인 경우 이동할 수 없다.
        if (isOutOfRange(nr, nc) || map[nr][nc] == '#')
            action(hero.r, hero.c);
        else action(nr, nc);
    }

    private static void action(int r, int c) {
        String key = r + "_" + c;
        switch (map[r][c]) {
            // 아이템 상자
            case 'B':
                Item item = items.get(key);
                switch (item.t) {
                    case 'W':
                        hero.weapon = item.s;
                        break;
                    case 'A':
                        hero.armor = item.s;
                        break;
                    case 'O':
                        hero.setAcc(item.s);
                        break;
                }
                map[r][c] = '.';
                break;
            // 가시 함정
            case '^':
                if (hero.acc[5]) hero.hp -= 1;
                else hero.hp = Math.max(0, hero.hp - 5);
                if (hero.hp <= 0) {
                    death("SPIKE TRAP");
                    return;
                }
                break;
            // 몬스터
            case '&':
            case 'M':
                Monster monster = killMonster(r, c);
                // 몬스터 승
                if (monster != null) {
                    death(monster.name);
                    monster.hp = monster.maxHp;
                    return;
                } else {
                    // 주인공 승 && 장신구 "HR"
                    if (hero.acc[1])
                        hero.hp = Math.min(hero.hp + 3, hero.maxHp);
                }
                break;
        }
        hero.r = r;
        hero.c = c;
    }

    private static Monster killMonster(int r, int c) {
        String key = r + "_" + c;
        Monster monster = monsters.get(key);
        boolean boss = map[monster.r][monster.c] == 'M';
        boolean courage = hero.acc[3];
        if (boss && hero.acc[6]) hero.hp = hero.maxHp;
        while (hero.hp > 0) {
            // 주인공 선공
            int damage;
            if (courage) {
                if (hero.acc[5])
                    damage = hero.getAtt() * 3 - monster.def;
                else
                    damage = hero.getAtt() * 2 - monster.def;
                courage = false;
            } else damage = hero.getAtt() - monster.def;
            monster.hp -= Math.max(1, damage);
            if (monster.hp <= 0) {
                monsters.remove(key);
                // 장신구 "EX"
                if (hero.acc[4]) hero.setExp((int) (monster.exp * 1.2));
                else hero.setExp(monster.exp);
                // 경험치 차면 레벨 업
                if (hero.exp >= hero.lv * 5) hero.setLv();
                // 보스면 게임 종료
                if (map[monster.r][monster.c] == 'M') isFinished = 1;
                map[r][c] = '.';
                return null;
            }
            // 몬스터 후공
            if (boss && hero.acc[6]) boss = false;
            else hero.hp = Math.max(0, hero.hp - Math.max(1, monster.atk - hero.getDef()));
        }
        return monster;
    }

    private static void death(String str) {
        // 부활, 장신구 "RE"
        if (hero.acc[2]) {
            hero.acc[2] = false;
            hero.amountOfAcc--;
            hero.hp = hero.maxHp;
            hero.r = SR;
            hero.c = SC;
        } else {
            // 게임 종료
            isFinished = 2;
            killer = str;
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String command;
        int K = 0, L = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        monsters = new HashMap<>();
        items = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                switch (map[i][j]) {
                    case '@':
                        SR = i;
                        SC = j;
                        hero = new Hero(SR, SC);
                        map[SR][SC] = '.';
                        break;
                    case 'M':
                    case '&':
                        K++;
                        break;
                    case 'B':
                        L++;
                        break;
                }
            }
        }
        command = br.readLine();
        for (int i = 0; i < K; i++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            String s = st.nextToken();
            int w = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(r).append("_").append(c);
            monsters.put(sb.toString(), new Monster(r, c, s, w, a, h, e));
        }
        for (int i = 0; i < L; i++) {
            StringBuilder sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            char t = st.nextToken().charAt(0);
            String str = st.nextToken();
            int s;
            switch (str) {
                case "HR":
                    s = 1;
                    break;
                case "RE":
                    s = 2;
                    break;
                case "CO":
                    s = 3;
                    break;
                case "EX":
                    s = 4;
                    break;
                case "DX":
                    s = 5;
                    break;
                case "HU":
                    s = 6;
                    break;
                case "CU":
                    s = 7;
                    break;
                default:
                    s = Integer.parseInt(str);
                    break;
            }
            sb.append(r).append("_").append(c);
            items.put(sb.toString(), new Item(r, c, t, s));
        }
        return command;
    }

    private static String output(int t) {
        StringBuilder sb = new StringBuilder();
        if (isFinished != 2) map[hero.r][hero.c] = '@';
        for (char[] cc : map) {
            for (char c : cc) {
                sb.append(c);
            }
            sb.append("\n");
        }
        sb.append("Passed Turns : ").append(t).append("\n");
        sb.append(hero);
        switch (isFinished) {
            case 1:
                sb.append("YOU WIN!");
                break;
            case 2:
                sb.append("YOU HAVE BEEN KILLED BY ").append(killer).append("..");
                break;
            case 3:
                sb.append("Press any key to continue.");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        new Boj_17081().solution();
    }
}

class Item {
    int r, c, s;
    char t;

    public Item(int r, int c, char t, int s) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.s = s;
    }
}

class Monster {
    String name;
    int r, c, atk, def, hp, maxHp, exp;

    public Monster(int r, int c, String s, int w, int a, int h, int e) {
        this.r = r;
        this.c = c;
        this.name = s;
        this.atk = w;
        this.def = a;
        this.hp = h;
        this.maxHp = hp;
        this.exp = e;
    }
}

class Hero {
    int r, c, lv, hp, maxHp, att, def, exp, weapon, armor;
    boolean[] acc;
    int amountOfAcc;

    public Hero(int r, int c) {
        /*
         * [체력, 공격력, 방어력]
         * - 정수로 표시된다.
         * - 초기 값은 체력 20, 공격력 2, 방어력 2이다.
         */
        this(1, 20, 20, 2, 2, 0);
        this.r = r;
        this.c = c;
        acc = new boolean[8];
        amountOfAcc = 0;
    }

    public Hero(int lv, int hp, int maxHp, int att, int def, int exp) {
        this.lv = lv;
        this.hp = hp;
        this.maxHp = maxHp;
        this.att = att;
        this.def = def;
        this.exp = exp;
    }

    public int getAtt() {
        return att + weapon;
    }

    public int getDef() {
        return def + armor;
    }

    /*
     * 처음엔 레벨 1이며, 레벨 N에서 N+1이 되기 위한 필요 경험치는 5×N이다.
     * 레벨이 오를 경우,
     * 최대 HP가 5, 공격력과 방어력이 2씩 상승한 뒤 HP가 모두 회복된다.
     * 경험치를 얻어 레벨이 오르게 되면, 남는 경험치는 버려진다.
     * 예를 들어, 다음 레벨까지 필요한 경험치가 3인 상태에서 5의 경험치를 얻는다면, 레벨이 오른 다음, 남은 2의 경험치는 버려지고 0의 경험치를 갖고 있는 상태가 된다.
     */
    public void setLv() {
        this.lv++;
        maxHp += 5;
        att += 2;
        def += 2;
        hp = maxHp;
        exp = 0;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }

    public void setAcc(int idx) {
        /*
         * 장신구의 경우엔 착용할 수 있는 칸이 남았을 경우,
         * 그리고 동일한 효과의 장신구를 착용하지 않고 있을 경우에만 얻은 장신구를 착용한다.
         */
        if (amountOfAcc >= 4) return;
        if (acc[idx]) return;
        acc[idx] = true;
        amountOfAcc++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LV : ").append(lv).append("\n");
        sb.append("HP : ").append(hp).append("/").append(maxHp).append("\n");
        sb.append("ATT : ").append(att).append("+").append(weapon).append("\n");
        sb.append("DEF : ").append(def).append("+").append(armor).append("\n");
        sb.append("EXP : ").append(exp).append("/").append(lv * 5).append("\n");
        return sb.toString();
    }
}