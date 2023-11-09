package Tag_Game;

import java.util.Scanner;

public class Tag_game {
    public static void main(String[] args) {
        welcome();
    }

    public static void welcome() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String p = "Play";
            String e = "End";
            System.out.println("태그게임에 오신걸 환영합니다.");
            System.out.print("게임을 진행하시려면 Play, 종료하시려면 End를 입력해 주세요: ");
            String input = scan.nextLine();
            if (input.equals(p)) {
                System.out.println("태그게임을 시작합니다.");
                Tag_Game();
            } else if (input.equals(e)) {
                System.out.println("태그게임을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해 주십시오.");
                continue;
            }
        }
        scan.close();
    }

    public static void Tag_Game() {
        Wizard wizard = new Wizard("마법사", 9, "마법", 7, 50);
        Warrior warrior = new Warrior("전사", 1, "검", 9, 50);
        Monster monster = new Monster("몬스터", "반격", 0, 100);
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.printf("%5s %5s %5s %5s","캐릭터명", "체력", "공격력", "방어력");
        System.out.println("\t" + wizard.name + "\t" + wizard.hp + "\t" + wizard.atk + "\t" + wizard.def);
        System.out.println("\t" + warrior.name + "\t" + warrior.hp  + "\t" + warrior.atk + "\t" + warrior.def);
        System.out.println("\t" + monster.name + "\t" + monster.hp + "\t" + monster.atk + "\t" + monster.def);
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }
}
