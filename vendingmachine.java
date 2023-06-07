import java.util.Scanner;

public class vendingmachine {
	public static vendingmachine_home machine = new vendingmachine_home();

	public static void main(String[] args) 
	{
		Scanner scan= new Scanner(System.in);
		while(true) {
			System.out.print("넣으실 금액을 입력해 주세요: ");
			machine.money+=scan.nextInt();
			machine.getMoney(machine.money);
			machine.menu();
			System.out.print("메뉴를 선택해주세요: ");
			int menu=scan.nextInt();
			if(menu==5) {
				machine.giveMoney(machine.money);
				break;
			} else{
				machine.money = machine.giveMenu(machine.money, menu);
			}
		}
		scan.close();
	}
}