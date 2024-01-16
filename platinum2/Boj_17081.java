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
    static int N, M;
    static char[][] map;
    static HashMap<String, Monster> monsters;
    static HashMap<String, Item> items;
    static Hero hero;
    static String killer;
    static int isFinished;

    public void solution() throws Exception {
        String command = input();
        killer = "SPIKE TRAP";
        int length = command.length();
        int t = 0;
        for (int i = 0; i < length; i++) {
            int dir = -1;
            switch (command.charAt(i)) {
                case 'L':
                    dir = 0;
                    break;
                case 'R':
                    dir = 1;
                    break;
                case 'U':
                    dir = 2;
                    break;
                case 'D':
                    dir = 3;
                    break;
            }
            move(dir);
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
        if (isOutOfRange(nr, nc) || map[nr][nc] == '#') action(hero.r, hero.c);
        else action(nr, nc);
    }

    private static void action(int r, int c) {
        switch (map[r][c]) {
            // 아이템 상자
            case 'B':
                Item item = items.get(r + "_" + c);
                switch (item.t) {
                    case 'W':
                        hero.setWeapon(item.n);
                        break;
                    case 'A':
                        hero.setArmor(item.n);
                        break;
                    case 'O':
                        hero.setAcc(item.s);
                        break;
                }
                map[r][c] = '.';
                break;
            // 가시 함정
            case '^':
                int damage = hero.isOwned("DX") ? 1 : 5;
                hero.setHp(Math.max(0, hero.getHp() - damage));
                if (hero.getHp() <= 0) {
                    // 죽으면 이동하지 않음
                    if (hero.dead()) isFinished = 2;
                    return;
                }
                break;
            // 몬스터
            case '&':
            case 'M':
                if (fight(r, c)) break;
                else return;
        }
        hero.setLoc(r, c);
    }

    private static boolean fight(int r, int c) {
        Monster monster = monsters.get(r + "_" + c);
        boolean boss = map[r][c] == 'M';
        boolean first = true;
        // [HU] 보스 몬스터와 전투에 돌입하는 순간 체력을 최대치까지 회복
        if (boss && hero.isOwned("HU")) hero.setHp(hero.getMaxHp());
        while (hero.getHp() > 0) {
            // 주인공 선공
            monster.hp -= hero.getDamage(monster.def, first);
            if (monster.hp <= 0) {
                win(monster);
                return true;
            }
            // 몬스터 후공
            // [HU] 보스 몬스터의 첫 공격에 0의 데미지를 입는다.
            if (first && boss && hero.isOwned("HU")) {
                first = false;
                continue;
            }
            hero.setHp(Math.max(0, hero.getHp() - monster.getDamage(hero.getDef())));
            if (first) first = false;
        }
        lose(monster);
        return false;
    }

    private static void win(Monster monster) {
        monsters.remove(monster.r + "_" + monster.c);
        // 경험치 추가
        hero.setExp(monster.exp);
        // 보스를 이기면 게임 종료
        if (map[monster.r][monster.c] == 'M') isFinished = 1;
        // 몬스터가 사망할 경우, 그 자리는 빈 칸이 된다.
        map[monster.r][monster.c] = '.';
        // [HR] 전투에서 승리할 때마다 체력을 3(최대 체력 수치까지) 회복한다.
        if (hero.isOwned("HR")) hero.setHp(Math.min(hero.getHp() + 3, hero.getMaxHp()));
    }

    private static void lose(Monster monster) {
        monster.hp = monster.maxHp;
        if (hero.dead()) {
            isFinished = 2;
            killer = monster.name;
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String command;
        int K = 0;  // 몬스터 수
        int L = 0;  // 아이템 수
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
                        hero = new Hero(i, j);
                        map[i][j] = '.';
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
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            String s = st.nextToken();
            int w = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            monsters.put(r + "_" + c, new Monster(r, c, s, w, a, h, e));
        }
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            char t = st.nextToken().charAt(0);
            String str = st.nextToken();
            if (t == 'O') items.put(r + "_" + c, new Item(r, c, t, str));
            else items.put(r + "_" + c, new Item(r, c, t, Integer.parseInt(str)));
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
    int r, c, n;
    char t;
    String s;

    public Item(int r, int c, char t, int n) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.n = n;
    }

    public Item(int r, int c, char t, String s) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.s = s;
    }
}

class Monster {
    String name;
    int r, c, att, def, hp, exp;
    final int maxHp;

    public Monster(int r, int c, String s, int w, int a, int h, int e) {
        this.r = r;
        this.c = c;
        name = s;
        att = w;
        def = a;
        hp = h;
        maxHp = hp;
        exp = e;
    }

    public int getDamage(int def) {
        return Math.max(1, att - def);
    }
}

class Hero {
    int r, c;
    private int lv, hp, att, def, exp, weapon, armor;
    Set<String> acc;
    final int SR, SC;

    public Hero(int r, int c) {
        /*
         * [체력, 공격력, 방어력]
         * - 정수로 표시된다.
         * - 초기 값은 체력 20, 공격력 2, 방어력 2이다.
         */
        this(1, 20, 2, 2, 0, r, c);
        this.r = r;
        this.c = c;
        acc = new HashSet<>();
    }

    public Hero(int lv, int hp, int att, int def, int exp, int sr, int sc) {
        this.lv = lv;
        this.hp = hp;
        this.att = att;
        this.def = def;
        this.exp = exp;
        SR = sr;
        SC = sc;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return 20 + ((lv - 1) * 5);
    }

    public int getAtt() {
        return att + weapon;
    }

    public int getDef() {
        return def + armor;
    }

    public boolean isOwned(String acc) {
        return this.acc.contains(acc);
    }

    public int getDamage(int def, boolean first) {
        int att = getAtt();
        // [CO] 모든 전투의 첫 번째 공격에서 주인공의 공격력이 두 배로 적용된다.
        if (first && isOwned("CO")) {
            // [DX] Courage 장신구와 함께 착용할 경우, 공격력 효과가 세 배로 적용된다.
            att = isOwned("DX") ? getAtt() * 3 : getAtt() * 2;
        }
        return Math.max(1, att - def);
    }

    public void setLoc(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setWeapon(int n) {
        this.weapon = n;
    }

    public void setArmor(int n) {
        this.armor = n;
    }

    public void setExp(int exp) {
        // [EX] 얻는 경험치가 1.2배가 된다. 소수점 아래는 버린다.
        this.exp += acc.contains("EX") ? (int) (exp * 1.2) : exp;
        // 레벨 N에서 N+1이 되기 위한 필요 경험치는 5×N이다.
        if (this.exp >= lv * 5) levelUp();
    }

    public void setAcc(String name) {
        /*
         * 장신구의 경우엔 착용할 수 있는 칸이 남았을 경우,
         * 그리고 동일한 효과의 장신구를 착용하지 않고 있을 경우에만 얻은 장신구를 착용한다.
         */
        if (acc.size() >= 4) return;
        if (acc.contains(name)) return;
        acc.add(name);
    }

    public boolean dead() {
        if (isOwned("RE")) {
            resurrect();
            return false;
        }
        return true;
    }

    public void resurrect() {
        acc.remove("RE");
        setHp(getMaxHp());
        setLoc(SR, SC);
    }

    /*
     * 레벨이 오를 경우,
     * 최대 HP가 5, 공격력과 방어력이 2씩 상승한 뒤 HP가 모두 회복된다.
     * 경험치를 얻어 레벨이 오르게 되면, 남는 경험치는 버려진다.
     * 예를 들어, 다음 레벨까지 필요한 경험치가 3인 상태에서 5의 경험치를 얻는다면, 레벨이 오른 다음, 남은 2의 경험치는 버려지고 0의 경험치를 갖고 있는 상태가 된다.
     */
    public void levelUp() {
        lv++;
        att += 2;
        def += 2;
        setHp(getMaxHp());
        exp = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LV : ").append(lv).append("\n");
        sb.append("HP : ").append(hp).append("/").append(getMaxHp()).append("\n");
        sb.append("ATT : ").append(att).append("+").append(weapon).append("\n");
        sb.append("DEF : ").append(def).append("+").append(armor).append("\n");
        sb.append("EXP : ").append(exp).append("/").append(lv * 5).append("\n");
        return sb.toString();
    }
}