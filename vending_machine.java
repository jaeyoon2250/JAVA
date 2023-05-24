import java.util.Scanner;

public class vending_machine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] menupan = { "콜라", "사이다", "환타", "펩시" };
        int[][] state = {
                { 500, 200, 800, 1000 },
                { 15, 10, 2, 1 }
        };
        int input = 0, menu = 0;

        do {
            System.out.print("넣으실 금액을 입력해 주세요: ");
            input += scan.nextInt();
            System.out.printf("현재 넣으신 돈은 %d 입니다.\n\n", input);
            System.out.println("메뉴를 선택해 주십시오.");
            System.out.printf("1. %s / %d원 / %d개 보유\n", menupan[0], state[0][0], state[1][0]);
            System.out.printf("2. %s / %d원 / %d개 보유\n", menupan[1], state[0][1], state[1][1]);
            System.out.printf("3. %s / %d원 / %d개 보유\n", menupan[2], state[0][2], state[1][2]);
            System.out.printf("4. %s / %d원 / %d개 보유\n", menupan[3], state[0][3], state[1][3]);
            System.out.println("숫자 5를 입력하시면 자판기가 종료됩니다.");
            System.out.printf("현재 보유중인 돈은 %d원 입니다.\n", input);
            System.out.print("메뉴를 선택해 주십시오: ");
            menu = scan.nextInt();
            System.out.print("\n");

            if (menu == 1) {
                if (input < state[0][0]) {
                    fail(input);
                } else {
                    success(input, state, menu);
                    state[1][0] -= 1;
                    input -= state[0][0];
                }
            } else if (menu == 2) {
                if (input < state[0][1]) {
                    fail(input);
                } else {
                    success(input, state, menu);
                    state[1][1] -= 1;
                    input -= state[0][1];
                }
            } else if (menu == 3) {
                if (input < state[0][2]) {
                    fail(input);
                } else {
                    success(input, state, menu);
                    state[1][2] -= 1;
                    input -= state[0][2];
                }
            } else if (menu == 4) {
                if (input < state[0][3]) {
                    fail(input);
                } else {
                    success(input, state, menu);
                    state[1][3] -= 1;
                    input -= state[0][3];
                }
            } else if (menu == 5) {
                break;
            } else {
                success(input, state, menu);
            }

        } while (menu != 5);
        System.out.println("자판기를 종료합니다.");
        scan.close();
    }

    public static void success(int input, int[][] state, int menu) {
        String[] menupan = { "콜라", "사이다", "환타", "펩시" };
        if (menu == 1) {
            System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[0], state[0][0]);
            input -= state[0][0];
            System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
        } else if (menu == 2) {
            System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[1], state[0][1]);
            input -= state[0][1];
            System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
        } else if (menu == 3) {
            System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[2], state[0][2]);
            input -= state[0][2];
            System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
        } else if (menu == 4) {
            System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[3], state[0][3]);
            input -= state[0][3];
            System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다.");
        }
    }

    public static void fail(int input) {
        System.out.printf("금액이 부족합니다, 현재 넣으신 금액은 %d원 입니다.\n", input);
    }
}