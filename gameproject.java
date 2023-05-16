import java.util.Scanner;
public class gameproject {
    public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String start;
		String a = "y", b = "Y";
		String c = "n", d = "N";

		do{
			start = "";
			System.out.print("게임을 시작하시려면 y, 아니라면 n을 입력해 주세요: ");
			start = scan.nextLine();
			if(a.equals(start) || b.equals(start)) {
				System.out.println("게임을 시작합니다.");
				scan.close();
				game();
				break;
			}
			else if(c.equals(start) || d.equals(start)) {
				System.out.println("게임을 종료합니다.");
				scan.close();
				break;
			}
			else {
				System.out.println("잘못된 값을 입력하셨습니다.");
				scan.close();
			}
			
		} while(!c.equals(start) || !d.equals(start) || !a.equals(start) || !b.equals(start));
		
		System.out.println("몬스터의 체력이 0 이 되었습니다.");
		System.out.println("몬스터를 처치했습니다!");
		System.out.println("게임이 종료됩니다.");
}

	public static void game() {
		
		Scanner input = new Scanner(System.in);
        int mhp = 10, ap = 3, ad = 1, healing = 5, turn = 1;
		String action;
		String a = "마법", b = "마법공격";
		String c = "찌르기", d = "찌르기공격";

		System.out.printf("몬스터의 HP는 %d 입니다, 몬스터는 5턴마다 HP를 %d 씩 회복합니다.\n", mhp, healing);
        System.out.printf("플레이어가 취할수 있는 액션은 공격력 %d 의 마법 공격과 공격력 %d 의 찌르기 공격이 있습니다.\n", ap, ad);
		do{
			System.out.printf("몬스터의 현재 체력은 %d 입니다.\n", mhp);
			action = "";
			System.out.print("어떤 동작을 취하시겠습니까?: ");
			action = input.nextLine();
			if(action.equals(a) || action.equals(b)){
				System.out.printf("몬스터에게 %d 의 마법 피해를 입혔습니다", ap);
				System.out.printf("%d 턴 지났습니다", turn);
				turn++;
				mhp -= ap;
				if(turn % 5 == 0){
					mhp += healing;
				}
			}
			else if(action.equals(c) || action.equals(d)){
				System.out.printf("몬스터에게 %d 의 찌르기 피해를 입혔습니다", ad);
				System.out.printf("%d 턴 지났습니다", turn);
				turn++;
				mhp -= ad;
				if(turn % 5 == 0){
					mhp += healing;
				}
			}
			else{
				System.out.println("잘못된 동작을 입력하셨습니다.");
			}
		} while(mhp >= 0);
		input.close();
	}
}