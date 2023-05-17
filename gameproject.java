import java.util.Scanner;

public class gameproject {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String start;
		String[] typing = new String[4];
		typing[0] = "y";
		typing[1] = "Y";
		typing[2] = "n";
		typing[3] = "N";
		// 스캐너 선언과 값을 입력받을 start 변수, start변수와 equals함수로 비교할 typing 배열 선언과 초기화
		do {
			start = "";
			System.out.print("게임을 시작하시려면 y, 아니라면 n을 입력해 주세요: ");
			start = scan.nextLine();
			// 사용자가 y 또는 Y를 입력한 경우
			if (typing[0].equals(start) || typing[1].equals(start)) {
				System.out.println("게임을 시작합니다.");
				game(); // 게임을 실행
				System.out.println("몬스터의 체력이 0 이 되었습니다.");
				System.out.println("몬스터를 처치했습니다!");
				System.out.println("게임이 종료됩니다.");
				break;
				// 몬스터의 체력이 0이 되면 game 메소드 탈출 후 게임종료 메세지와 함께 while 문 탈출
			}
			// 사용자가 n 또는 N를 입력한 경우
			else if (typing[2].equals(start) || typing[3].equals(start)) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			// 사용자가 잘못된 값을 입력한 경우
			else {
				System.out.println("잘못된 값을 입력하셨습니다.");
			}

		} while (!typing[0].equals(start) || !typing[1].equals(start) || !typing[2].equals(start)
				|| !typing[3].equals(start)); // y 또는 Y 와 n 또는 N 이 아니라면 while 문을 반복 실행
		scan.close();
	}

	public static void game() {
		// 게임 진행 메소드

		Scanner input = new Scanner(System.in);
		int mhp = 10, ap = 3, ad = 1, healing = 2, turn = 0;
		String action;
		String[] typing = new String[4];
		typing[0] = "마법";
		typing[1] = "마법공격";
		typing[2] = "찌르기";
		typing[3] = "찌르기공격";
		// 스캐너 선언과 값을 입력받을 action 변수, action변수와 equals함수로 비교할 typing 배열 선언과 초기화
		System.out.printf("몬스터의 HP는 %d 입니다, 몬스터는 5턴마다 HP를 %d 씩 회복합니다.\n", mhp, healing);
		System.out.printf("플레이어가 취할수 있는 동작은 공격력 %d 의 마법 공격과 공격력 %d 의 찌르기 공격이 있습니다.\n", ap, ad);
		// 룰 설명
		do {
			System.out.printf("몬스터의 현재 체력은 %d 입니다.\n", mhp); // 몬스터의 체력 상황을 알려줌
			action = "";
			System.out.print("어떤 동작을 취하시겠습니까?: ");
			action = input.nextLine(); // 취할 동작을 입력받는다
			if (action.equals(typing[0]) || action.equals(typing[1])) {
				// 몬스터에게 피해를 입히고 몇턴이 지났는지를 알려줌
				// 마법피해를 줄 경우
				System.out.printf("몬스터에게 %d 의 마법 피해를 입혔습니다\n", ap);
				turn++;
				System.out.printf("%d 턴 지났습니다\n", turn);
				mhp -= ap;
				// 5턴마다 몬스터가 체력을 2만큼 회복한다
				if (turn % 5 == 0) {
					System.out.printf("%d 턴이 지나 몬스터가 체력을 %d 회복합니다.\n", turn, healing);
					mhp += healing;
				}
			}
			// 찌르기 피해를 줄 경우
			else if (action.equals(typing[2]) || action.equals(typing[3])) {
				System.out.printf("몬스터에게 %d 의 찌르기 피해를 입혔습니다\n", ad);
				turn++;
				System.out.printf("%d 턴 지났습니다\n", turn);
				mhp -= ad;
				if (turn % 5 == 0) {
					System.out.printf("%d 턴이 지나 몬스터가 체력을 %d 회복합니다.\n", turn, healing);
					mhp += healing;
				}
			}
			// 마법 혹은 찌르기 피해가 아닌 잘못된 문자를 입력한 경우
			else {
				System.out.println("잘못된 동작을 입력하셨습니다.");
			}
		} while (mhp > 0); // 몬스터의 체력이 0이 되기 전까지 while 문 반복 실행
		input.close();
	}
}