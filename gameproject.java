import java.util.Scanner;
public class gameproject {
    public static void main(String[] args) {
		
		System.out.print("게임을 시작하시려면 y, 아니라면 n을 입력해 주세요: ");
		Scanner scan = new Scanner(System.in);
		String start = scan.next();
		if(start == "y" || start == "Y") {
			System.out.print("게임을 시작합니다.");
			game();
		}
		else if(start == "n" || start == "N") {
			System.out.print("게임을 종료합니다.");
		}
		else {
			System.out.print("값을 다시 입력해 주십시오.");
		}
		scan.close();
	}
	public static void game() {
        int mhp = 10, ap = 3, ad = 1, healing = 5;
		System.out.printf("몬스터의 HP는 %d 입니다, 몬스터는 5턴마다 HP를 %d 씩 회복합니다.", mhp, healing);
        System.out.printf("플레이어가 취할수 있는 액션은 공격력 %d 의 마법공격과 공격력 %d 의 찌르기 공격이 있습니다.", ap, ad);
        
	}
}