public class vendingmachine_home {
        String[] menupan = { "콜라", "사이다", "환타", "펩시" };
        int[][] state = {
                        { 500, 200, 800, 1000 },
                        { 15, 10, 2, 1 }
        };
        int money = 0;

        public void getMoney(int money) {
                System.out.printf("현재 보유중인 금액은 %d원입니다\n", money);
        }

        public void giveMoney(int money) {
                System.out.printf("잔돈 %d원이 반환됩니다.\n", money);
		System.out.println("자판기를 종료합니다.");
        }

        public void menu() {
                System.out.println("-----menu -----");
                for (int i = 0; i < 4; i++) {
                        System.out.printf("%d / %s / %d원 / %d개보유\n", i + 1, menupan[i], state[0][i],
                                        state[1][i]);
                }
                System.out.println("-------------");
                System.out.println("숫자5를 입력하시면 자판기가 종료됩니다.");
                System.out.println("재고가 없을 시 판매가 중단됩니다.");
        }

        public int giveMenu(int money, int menu) {
                if (menu < 5) {
                        if (money >= state[0][menu - 1]) {
                                if (state[1][menu - 1] > 0) {
                                        System.out.printf("%s가 나왔습니다, %d원 입니다.\n", menupan[menu - 1], state[0][menu - 1]);
                                        money -= state[0][menu - 1];
                                        System.out.printf("현재 남은 잔돈은 %d원입니다.\n\n", money);
                                        state[1][menu - 1] -= 1;
                                } else {
                                        need(menu);
                                }
                        } else {
                                fail(money);
                        }
                } else {
                        System.out.println("잘못된 숫자를 입력하셨습니다.");
                }
                return money;
        }

        public void need(int menu) {
                System.out.printf("%s의 재고가 떨어졌습니다, 다른 메뉴를 선택해주세요.\n", menupan[menu - 1]);
        }

        public void fail(int money) {
                System.out.printf("금액이 부족합니다, 현재 넣으신 금액은 %d원입니다.\n", money);
        }
}