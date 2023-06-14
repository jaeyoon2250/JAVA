import java.util.Scanner;

public class DogHotel {
    public static void main(String[] args) {
        Hotel();
    }

    public static void Hotel() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("어서오세요 강아지 호텔입니다.");
            System.out.println("1. 체크인\t 2. 체크아웃\t 3. 호텔 서비스 종료");
            System.out.print("원하시는 작업을 입력해 주십시요: ");
            int choose = scan.nextInt();
            System.out.print("\n");
            if (choose == 1) {
                check_in();
                continue;
            } else if (choose == 2) {
                if (HotelSystem.room == 1) {
                    check_out();
                    continue;
                } else {
                    System.out.println("현재 체크인 되어있는 강아지가 없습니다, 다시 선택해 주십시요.");
                    continue;
                }
            } else if (choose == 3) {
                System.out.println("이용해주셔서 감사합니다, 안녕히 돌아가십시요.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해 주세요.");
                continue;
            }
        }
        scan.close();
    }

    public static void check_in() {
        Scanner scan = new Scanner(System.in);

        String name;
        String breeds;

        while (true) {
            System.out.print("체크인 하시겠습니까?(Y/y or N/n): ");
            String typing = scan.nextLine();
            System.out.print("\n");
            if (typing.equals("Y") || typing.equals("y")) {
                if (HotelSystem.room == 1) {
                    System.out.println("죄송합니다, 현재 비어있는 방이 없습니다\n");
                    break;
                } else {
                    System.out.println("비어있는방이 1개 있습니다.");
                    System.out.println("강아지의 이름과 종을 입력해주세요.");
                    System.out.print("이름: ");
                    name = scan.nextLine();
                    System.out.print("종: ");
                    breeds = scan.nextLine();
                    System.out.print("\n");
                    Dog dog_info = new Dog(name, breeds);
                    System.out.printf("%d 번째 생성된 이름: '%s', 종: '%s' 인 강아지의 체크인이 완료되었습니다.\n\n", Dog.count, Dog.name,
                            Dog.breeds);
                    HotelSystem.room = 1;
                    break;
                }
            } else if (typing.equals("n") || typing.equals("N")) {
                System.out.println("안녕히 돌아가십시요.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해 주십시요.");
                break;
            }
        }
    }

    public static void check_out() {
        Scanner scan = new Scanner(System.in);
        String out_name;
        String out_breeds;

        while (true) {
            System.out.print("체크아웃 하시겠습니까?(Y/y or N/n): ");
            String typing = scan.nextLine();
            System.out.print("\n");
            if (typing.equals("Y") || typing.equals("y")) {
                System.out.println("맡기신 강아지의 이름과 종을 입력해주세요.");
                System.out.print("이름: ");
                out_name = scan.nextLine();
                System.out.print("종: ");
                out_breeds = scan.nextLine();
                System.out.print("\n");
                if (out_name.equals(Dog.name) && out_breeds.equals(Dog.breeds)) {
                    System.out.printf("%d 번째 생성된 이름: '%s', 종: '%s' 인 강아지의 체크아웃이 완료되었습니다.\n\n", Dog.count, Dog.name,
                            Dog.breeds);
                    HotelSystem.room = 0;
                    break;
                } else {
                    System.out.println("잘못된 강아지 정보를 입력하셨습니다, 다시 입력해 주십시요.");
                    continue;
                }
            } else if (typing.equals("n") || typing.equals("N")) {
                System.out.println("안녕히 돌아가십시요.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해 주십시요.");
                break;
            }
        }
    }
}

class Dog {
    static String name;
    static String breeds;
    static int count = 0;

    Dog(String _name, String _breeds) {
        count++;
        name = _name;
        breeds = _breeds;
    }
}

class HotelSystem {
    static int room = 0;
}