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
            System.out.print("넣으실 금액을 입력해 주세요: "); // 처음 금액을 입력받음
            input += scan.nextInt();
            System.out.printf("현재 넣으신 돈은 %d 입니다.\n\n", input); // 입력받은 금액을 알려줌
            System.out.println("메뉴를 선택해 주십시오.");
            // System.out.printf("1. %s / %d원 / %d개 보유\n", menupan[0], state[0][0], state[1][0]);
            // System.out.printf("2. %s / %d원 / %d개 보유\n", menupan[1], state[0][1], state[1][1]);
            // System.out.printf("3. %s / %d원 / %d개 보유\n", menupan[2], state[0][2], state[1][2]);
            // System.out.printf("4. %s / %d원 / %d개 보유\n", menupan[3], state[0][3], state[1][3]);
            for(int i = 0; i < 4; i++){
                System.out.printf("%d. %s / %d원 / %d개 보유\n", i+1, menupan[i], state[i-i][i], state[i-i+1][i]);
            }
            System.out.println("숫자 5를 입력하시면 자판기가 종료됩니다.");
            System.out.println("재고가 없을 시 판매가 중단됩니다.");
            System.out.printf("현재 보유중인 돈은 %d원 입니다.\n", input); // 현재 보유중인 돈을 알려줌
            System.out.print("메뉴를 선택해 주십시오: ");
            menu = scan.nextInt();
            System.out.print("\n");

            // 메뉴 변수로 입력받은 값에 따라 입력받은 금액이 가격보다 높다면 success메소드 실행, 낮다면 fail메소드 실행
            if (menu == 1) {
                if (input < state[0][0]) {
                    fail(input);
                } else {
                    input = success(input, state, menu);
                }
            } else if (menu == 2) {
                if (input < state[0][1]) {
                    fail(input);
                } else {
                    input = success(input, state, menu);
                }
            } else if (menu == 3) {
                if (input < state[0][2]) {
                    fail(input);
                } else {
                    input = success(input, state, menu);
                }
            } else if (menu == 4) {
                if (input < state[0][3]) {
                    fail(input);
                } else {
                    input = success(input, state, menu);
                }
            } else if (menu == 5) {
                break;
            } else {
                success(input, state, menu);
            }

        } while (menu != 5);
        System.out.println("자판기를 종료합니다."); // 메뉴 변수에 5 값을 입력받으면 while문 정지와 함께 프로그램 종료
        System.out.printf("잔돈 %d원이 반환됩니다.", input);
        scan.close();
    }

    public static int success(int input, int[][] state, int menu) {
        String[] menupan = { "콜라", "사이다", "환타", "펩시" };
        // 입력받은 메뉴 변수에 값에 따라 메뉴와 가격, 잔돈을 알려줌
        if (menu == 1) {
            if (state[1][0] > 0) {
                System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[0], state[0][0]);
                input -= state[0][0];
                System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
                state[1][0] -= 1;
            } else {
                need(menupan, menu);
            }
        } else if (menu == 2) {
            if (state[1][1] > 0) {
                System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[1], state[0][1]);
                input -= state[0][1];
                System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
                state[1][1] -= 1;
            } else {
                need(menupan, menu);
            }
        } else if (menu == 3) {
            if (state[1][2] > 0) {
                System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[2], state[0][2]);
                input -= state[0][2];
                System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
                state[1][2] -= 1;
            } else {
                need(menupan, menu);
            }
        } else if (menu == 4) {
            if (state[1][3] > 0) {
                System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[3], state[0][3]);
                input -= state[0][3];
                System.out.printf("현재 남은 잔돈은 %d원 입니다.\n", input);
                state[1][3] -= 1;
            } else {
                need(menupan, menu);
            }
        } else {
            System.out.println("잘못된 숫자를 입력하셨습니다."); // 1~5 사이의 숫자가 아닐시 처음으로 돌아가서 다시 실행
        }
        return input;
    }

    public static void fail(int input) {
        System.out.printf("금액이 부족합니다, 현재 넣으신 금액은 %d원 입니다.\n", input); // 금액이 부족한 경우 현재 보유중인 금액을 알려주며 부족하다는 메세지 실행
    }

    public static void need(String[] menupan, int menu) {
        // 메뉴 변수에 입력받은 값에 따라 메뉴로 이동, 만약 그 메뉴의 재고가 부족하면 재고가 떨어졌다는 메세지와 함께 처음으로 돌아가서 다시 실행
        if (menu == 1)
            System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[0]);
        else if (menu == 2)
            System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[1]);
        else if (menu == 3)
            System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[2]);
        else if (menu == 4)
            System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[3]);
    }
}