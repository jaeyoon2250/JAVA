import java.util.Scanner;
import java.util.Vector;

public class DogHotel_vol2 {
    public static void main(String[] args) {
        DogHotel_workspace DogHotel = new DogHotel_workspace();
        DogHotel.welcome();
    }
}

class newDog {
    String name;
    String breeds;

    public newDog(String name, String breeds) {
        this.name = name;
        this.breeds = breeds;
    }

    public String get_info() {
        return "강아지의 이름은 " + this.name + "이고, 강아지의 종은 " + this.breeds + "입니다.";
    }
}

class DogHotel_workspace {
    Scanner scan = new Scanner(System.in);

    newDog dog1 = new newDog("춘식일", "시고르자브종");
    newDog dog2 = new newDog("춘식이", "골든리트리버");
    newDog dog3 = new newDog("춘식삼", "닥스훈트");
    newDog dog4 = new newDog("춘식사", "몰티즈");
    newDog dog5 = new newDog("춘식오", "보더콜리");

    Vector<String> Dogs = new Vector<String>();
    Vector<Integer> Room = new Vector<>(2);

    public DogHotel_workspace() {
        Room.add(null); // Index 0
        Room.add(null); // Index 1
    }

    public void welcome() {
        while (true) {
            System.out.println("강아지호텔에 오신것을 환영합니다.");
            System.out.println("1. 체크인 2. 체크아웃 3. 체크인된 강아지 목록 확인 4. 프로그램 종료");
            System.out.print("어느것을 도와드릴까요?: ");
            int input = scan.nextInt();
            scan.nextLine();

            if (input == 1) {
                check_in();
            } else if (input == 2) {
                check_out();
            } else if (input == 3) {
                printCheckedInDogs();
            } else if (input == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다, 다시 입력해주세요.");
            }
        }
    }

    public void check_in() {
        Dogs.add(dog1.name);
        Dogs.add(dog2.name);
        Dogs.add(dog3.name);
        Dogs.add(dog4.name);
        Dogs.add(dog5.name);

        System.out.println("체크인 화면입니다.");
        System.out.println("현재 남아있는 방의 갯수는 " + getNullCount(Room) + "개 입니다.");
        if (getNullCount(Room) > 0) {
            System.out.print("어떤 강아지를 체크인 하시겠습니까?: ");
            String take_dog = scan.nextLine();
            if (take_dog.equals(getName(take_dog)) && !Room.contains(getIndex(take_dog))) {
                System.out.println("강아지를 체크인 했습니다, " + getDogInfo(getIndex(take_dog)));
                Room.set(getNullCount(Room) - 1, getIndex(take_dog));
            } else {
                System.out.println("해당 강아지가 존재하지 않거나 이미 체크인된 상태입니다. 다시 시도해 주세요.");
            }
        } else {
            System.out.println("호텔이 가득 찼습니다.");
        }
    }

    public void printCheckedInDogs() {
        System.out.println("체크인된 강아지 목록:");
        for (Integer roomIndex : Room) {
            if (roomIndex != null) {
                int dogIndex = roomIndex;
                System.out.println(getDogInfo(dogIndex));
            }
        }
    }

    public String getDogInfo(int DogNum) {
        if (DogNum == 0) {
            return dog1.get_info();
        } else if (DogNum == 1) {
            return dog2.get_info();
        } else if (DogNum == 2) {
            return dog3.get_info();
        } else if (DogNum == 3) {
            return dog4.get_info();
        } else {
            return dog5.get_info();
        }
    }

    public String getName(String take_dog) {
        String name;
        int index = Dogs.indexOf(take_dog);

        if (index != -1) {
            name = Dogs.get(index);
            return name;
        } else {
            return null;
        }
    }

    public int getIndex(String take_dog) {
        int index = Dogs.indexOf(take_dog);
        return index;
    }

    public void check_out() {
        System.out.println("체크아웃 화면입니다.");
        System.out.println("현재 차있는 방은 " + (Room.size() - getNullCount(Room)) + "개 입니다.");

        if (getNullCount(Room) < 2) {
            System.out.print("어떤 강아지를 체크아웃 하시겠습니까?: ");
            String taked_dog = scan.nextLine();
            int taked_dog_index = getIndex(taked_dog);

            // 해당 강아지의 인덱스가 존재하면 체크아웃
            if (Room.contains(taked_dog_index)) {
                System.out.println("강아지를 체크아웃 했습니다, " + getDogInfo(taked_dog_index));
                Room.set(Room.indexOf(taked_dog_index), null);
            } else {
                System.out.println("해당 강아지가 존재하지 않거나 체크인되지 않은 상태입니다. 다시 시도해 주세요.");
            }
        } else {
            System.out.println("호텔이 비어있습니다.");
        }
    }

    public int getNullCount(Vector<Integer> vector) {
        int count = 0;
        for (Integer value : vector) {
            if (value == null) {
                count++;
            }
        }
        return count;
    }
}
