package test;

import java.util.Scanner;

public class work {
	
	public static vendingmachine machine = new vendingmachine();
	
	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.print("넣으실 금액을 입력해 주세요: ");
			int money = scan.nextInt();
			getMoney(money);
			menu();
			int choos = scan.nextInt();
		
			if (machine.menu != 5) {
				if (machine.input < machine.state[0][machine.menu - 1]) {
					fail(machine.input);
				} else {
					machine.input = giveMenu(choos);
				}
			} else {
				giveMoney(input);
				break;
			}
		}
		
		
		scan.close();
	}

	public static void getMoney(int money) {
		System.out.printf("현재 들어있는 금액은 %d원 입니다", money);
	}
	
	public static void giveMoney(int input) {
		System.out.printf("잔돈 %d원이 반환됩니다.", input);
	}
	
	public static void menu() {
		System.out.println("메뉴를 선택해 주십시오.");
		System.out.println("- - - - - menu - - - - -");
		for (int i = 0; i < 4; i++) {
            System.out.printf("%d. %s / %d원 / %d개 보유\n", i + 1, machine.menupan[i], machine.state[0][i], machine.state[1][i]);
		}
		System.out.println("- - - - - - - - - - - - -");
		System.out.println("숫자 5를 입력하시면 자판기가 종료됩니다.");
        System.out.println("재고가 없을 시 판매가 중단됩니다.");
	}
	
	public static int giveMenu(int input) {
		if (machine.menu <= 5) {
            if (machine.state[1][machine.menu - 1] > 0) {
                System.out.printf("%s가 나왔습니다, %d원 입니다.\n", machine.menupan[machine.menu - 1], machine.state[0][machine.menu - 1]);
                input -= machine.state[0][machine.menu - 1];
                System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
                machine.state[1][machine.menu - 1] -= 1;
            } else {
                need(machine.menupan, machine.menu);
            }
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다.");
        }
        return input;
    }
	
	public static void need(String[] menupan, int menu) {
            System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[menu - 1]);
    }
	
	public static void fail(int input) {
        System.out.printf("금액이 부족합니다, 현재 넣으신 금액은 %d원 입니다.\n", input); // 금액이 부족한 경우 현재 보유중인 금액을 알려주며 부족하다는 메세지 실행
    }
}

