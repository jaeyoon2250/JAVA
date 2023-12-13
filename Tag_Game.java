import java.util.Random;
import java.util.Scanner;

interface Interface {
    void attack(Character Monster);
}

class Character {
    protected String name;
    protected int hp;
    protected String HowAtk;
    protected int atk;
    protected int def;

    public Character(String name, int hp, String HowAtk, int atk, int def) {
        this.name = name;
        this.hp = hp;
        this.HowAtk = HowAtk;
        this.atk = atk;
        this.def = def;
    }

    public int getHp() {
        return hp;
    }

    public int minusHp(int hp) {
        this.hp = hp;
        return this.hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public boolean survive(int hp) {
        boolean survive;
        if (hp > 0) {
            survive = false;
        } else {
            survive = true;
        }
        return survive;
    }
}

class Wizard extends Character implements Interface {
    public Wizard() {
        super("마법사", 50, "마법", 10, 7);
    }

    @Override
    public void attack(Character Enemy) {
        int damage = this.atk - Enemy.def;
        int elseHp = Enemy.minusHp(Enemy.hp - damage);
        if (elseHp > 0) {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
            System.out.println(Enemy.name + "의 남은 체력은 " + elseHp + "입니다.");
        } else {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
        }
    }
}

class Warrior extends Character implements Interface {
    public Warrior() {
        super("전사", 50, "칼", 1, 9);
    }

    @Override
    public void attack(Character Enemy) {
        int damage = this.atk - Enemy.def;
        int elseHp = Enemy.minusHp(Enemy.hp - damage);
        if (elseHp > 0) {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
            System.out.println(Enemy.name + "의 남은 체력은 " + elseHp + "입니다.");
        } else {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
        }
    }
}

class Monster extends Character implements Interface {
    Random rd = new Random();

    public Monster() {
        super("몬스터", 100, "반격", 0, 0);
    }

    @Override
    public void attack(Character Enemy) {
        int damage = (rd.nextInt(6) + 5) - Enemy.def;
        damage = Math.max(0, damage);
        int elseHp = Enemy.minusHp(Enemy.hp - damage);
        if (elseHp > 0) {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
            System.out.println(Enemy.name + "의 남은 체력은 " + elseHp + "입니다.");
        } else {
            System.out.println(this.name + " 가 " + Enemy.name + "에게 " + this.HowAtk + "(으)로 " + damage + "의 피해를 입혔습니다.");
        }
    }
}

public class Tag_Game {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Tag_Game PlayGame = new Tag_Game();
        PlayGame.welcome();
    }

    Wizard wizard = new Wizard();
    Warrior warrior = new Warrior();
    Monster monster = new Monster();

    Character FieldPlayer = warrior;
    Character Boss = monster;

    public void welcome() {
        String play = "P";
        String end = "E";
        while (true) {
            System.out.println("태그게임에 오신걸 환영합니다.");
            System.out.print("게임을 플레이 하시려면 P(p)를, 종료하시려면 E(e)를 입력해주세요: ");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase(play)) {
                System.out.println("게임을 시작합니다.");
                Tag_Game_BackEnd();
                break;
            } else if (input.equalsIgnoreCase(end)) {
                System.out.println("게임을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해주세요.");
                continue;
            }
        }
    }

    public void Tag_Game_BackEnd() {
        while (true) {
            System.out.println("ㅡㅡㅡㅡ상태창ㅡㅡㅡㅡ");
            System.out.println(warrior.name + " HP: " + warrior.getHp() + " 방어력: " + warrior.getDef());
            System.out.println(wizard.name + " HP: " + wizard.getHp() + " 방어력: " + wizard.getDef());
            System.out.println(monster.name + " HP: " + monster.getHp());
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.print("공격하시려면 1을, 태그하시려면 2를 입력해주세요: ");
            int action = scan.nextInt();
            if (action == 1) {
                if (FieldPlayer.equals(warrior)) {
                    warrior.attack(monster);
                    monster.attack(FieldPlayer);
                    if (Boss.survive(monster.hp) == true) {
                        Boss_Down();
                        break;
                    } else if (FieldPlayer.survive(FieldPlayer.hp) == true) {
                        Player_Down();
                        break;
                    } else {
                        continue;
                    }
                } else {
                    wizard.attack(monster);
                    monster.attack(FieldPlayer);
                    if (Boss.survive(monster.hp) == true) {
                        Boss_Down();
                        break;
                    } else if (FieldPlayer.survive(FieldPlayer.hp) == true) {
                        Player_Down();
                        break;
                    } else {
                        continue;
                    }
                }
            } else if (action == 2) {
                if (FieldPlayer == warrior) {
                    FieldPlayer = wizard;
                    tagging();
                    continue;
                } else {
                    FieldPlayer = warrior;
                    tagging();
                    continue;
                }
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해주세요.");
                continue;
            }
        }

    }

    public void Boss_Down() {
        System.out.println(monster.name + "가 쓰러졌습니다!");
        System.err.println("게임이 종료됩니다.");
    }

    public void Player_Down() {
        System.err.println(FieldPlayer.name + "가 쓰러졌습니다!");
        System.err.println("게임이 종료됩니다.");
    }

    public void tagging() {
        System.err.println(FieldPlayer.name + "로 태그되었습니다!");
    }
}