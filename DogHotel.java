import java.util.Scanner;

public class DogHotel {
    public static void main(String[] args) {

    }


    public static void Hotel() {
        System.out.println("어서오세요 강아지 호텔입니다.");
        System.out.print("강아지를 맡기시겠습니까?: ");
        Scanner scan = new Scanner(System.in);
        String typing = scan.nextLine();
        if (typing.equals("Y") || typing.equals("y")) {
            if (HotelSystem.room == 1) {
                System.out.println("죄송합니다, 현재 비어있는 방이 없습니다");
            } else {
                
            }
        }
        scan.close();
    }

}
    class Dog {
        String name;
        String breeds;
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