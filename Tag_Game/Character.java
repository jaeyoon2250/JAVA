package Tag_Game;

import java.util.Random;

abstract class Character {
    String name;
    int atk;
    String howatk;
    int def;
    int hp;
}

class Wizard extends Character implements Tagging {
    Wizard (String name, int atk, String howatk, int def, int hp) {
        this.name = name;
        this.atk = atk;
        this.howatk = howatk;
        this.def = def;
        this.hp = hp;
    }

    public void attack() {
        System.out.printf(name + "가 " + howatk + "으로 공격하여 " + atk + "의 데미지를 입혔습니다.");
    }

    public void tag() {
        System.out.println("마법사로 교체합니다.");
    }
}

class Warrior extends Character implements Tagging {
    Warrior (String name, int atk, String howatk, int def, int hp) {
        this.name = name;
        this.atk = atk;
        this.howatk = howatk;
        this.def = def;
        this.hp = hp;
    }

    public void attack() {
        System.out.printf(name + "가 " + howatk + "으로 공격하여 " + atk + "의 데미지를 입혔습니다.");
    }

    public void tag() {
        System.out.println("전사로 교체합니다.");
    }
}

class Monster extends Character implements Tagging {
    int react;
    Monster (String name, String howatk, int def, int hp) {
        this.name = name;
        this.atk = react;
        this.howatk = howatk;
        this.def = def;
        this.hp = hp;
    }

    public void attack() {
        Random rd = new Random();
        react = rd.nextInt(6) + 5;
        System.out.printf(name + "가 " + howatk + "으로 공격하여 " + atk + "의 데미지를 입혔습니다.");
    }
}