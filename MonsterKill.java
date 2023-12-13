import java.util.Scanner;

abstract class Game {
    public abstract int attack(int a);
    public abstract void tag();
}

interface Taggable {
    void tag();
}

class Character {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;

    public Character(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void attack(Character enemy) {
        int damage = Math.max(0, this.attack - enemy.getDefense());
        enemy.setHp(enemy.getHp() - damage);
        System.out.println(this.name + "이(가) " + enemy.name + "에게 " + damage + "의 데미지를 입혔습니다.");

        if (enemy instanceof Boss) {
            Boss boss = (Boss) enemy;
            boss.counterAttack(this);
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }
}

class Warrior extends Character implements Taggable {
    public Warrior() {
        super("전사", 50, 1, 9);
    }

    @Override
    public void tag() {
        System.out.println("전사가 태그를 걸었습니다.");
    }
}

class Magician extends Character implements Taggable {
    public Magician() {
        super("마법사", 50, 10, 7);
    }

    @Override
    public void tag() {
        System.out.println("마법사가 태그를 걸었습니다.");
    }
}

class Boss extends Character {
    public Boss() {
        super("보스", 100, 0, 0);
    }

    public void counterAttack(Character character) {
        int damage = (int) (Math.random() * 6) + 5;
        damage -= character.getDefense();
        damage = Math.max(0, damage);
        character.setHp(character.getHp() - damage);
        System.out.println(this.name + "이(가) " + character.name + "에게 반격하여 " + damage + "의 데미지를 입혔습니다.");
    }
}

public class MonsterKill  {
    public static void main(String[] args) {
        MonsterKill  monsterKill = new MonsterKill ();
        monsterKill.startGame();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        Warrior warrior = new Warrior();
        Magician magician = new Magician();
        Boss boss = new Boss();

        Character currentPlayer = warrior;
        Character opponent = boss;

        while (true) {
            System.out.println("[현재 상태]");
            System.out.println(warrior.name + " HP: " + warrior.getHp() + " 방어력: " + warrior.getDefense());
            System.out.println(magician.name + " HP: " + magician.getHp() + " 방어력: " + magician.getDefense());
            System.out.println(boss.name + " HP: " + boss.getHp());

            System.out.println("[턴 " + (boss.isDead() ? "승리" : "") + "]");
            System.out.println(currentPlayer.name + "의 차례입니다.");
            System.out.println("1. 공격하기  2. 태그하기");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (choice == 1) {
                currentPlayer.attack(opponent);
            } else if (choice == 2 && currentPlayer instanceof Taggable) {
                ((Taggable) currentPlayer).tag();
                // 플레이어 변경
                if (currentPlayer == warrior)
                    currentPlayer = magician;
                else
                    currentPlayer = warrior;
            } else {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                continue;
            }

            if (opponent.isDead()) {
                System.out.println(opponent.name + "을(를) 처치하였습니다. 게임 종료!");
                break;
            }
        }

        scanner.close();
    }
}