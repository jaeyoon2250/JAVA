package animalHotel;

import java.util.Scanner;

public class animalHotelSystem {
    private Scanner scan = new Scanner(System.in);
    private animal[] animals = new animal[2];
    private int animalCount = 0;

    public void welcome() {
        String in = "체크인";
        String out = "체크아웃";

        while (true) {
            System.out.println("동물호텔에 오신걸 환영합니다.");
            System.out.println("프로그램을 종료하고 싶다면 'stop'을 입력해주세요.");
            System.out.print("체크인과 체크아웃 중 무엇을 도와드릴까요? : ");
            String choice = scan.nextLine();

            if (choice.equals(in)) {
                checkIn();
            } else if (choice.equals(out)) {
                checkOut();
            } else if (choice.equalsIgnoreCase("stop")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        scan.close();
    }

    private void checkIn() {
        if (animalCount >= 2) {
            System.out.println("빈 방이 없습니다.");
            return;
        }

        System.out.println("체크인 화면입니다.");
        System.out.print("동물의 종을 입력해주세요 (고양이 또는 강아지): ");
        String breed = scan.nextLine();

        if (breed.equalsIgnoreCase("고양이") || breed.equalsIgnoreCase("강아지")) {
            String animalName = askName();

            if (breed.equalsIgnoreCase("고양이")) {
                animals[animalCount] = new cat(animalName);
            } else {
                animals[animalCount] = new dog(animalName);
            }

            System.out.println("체크인이 완료되었습니다.");
            animalCount++;
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    private void checkOut() {
        if (animalCount == 0) {
            System.out.println("동물이 체크인되어 있지 않습니다.");
            return;
        }

        System.out.println("체크아웃 화면입니다.");
        System.out.print("동물의 이름을 입력해주세요: ");
        String animalName = scan.nextLine();

        boolean found = false;

        for (int i = 0; i < animals.length; i++) {
            if (animals[i] != null && animals[i].getName().equalsIgnoreCase(animalName)) {
                found = true;
                if (animals[i] instanceof cat) {
                    ((cat) animals[i]).meow();
                } else if (animals[i] instanceof dog) {
                    ((dog) animals[i]).bark();
                }
                animals[i] = null;
                animalCount--;
            }
        }

        if (!found) {
            System.out.println("해당하는 동물이 호텔에 없습니다.");
        } else {
            System.out.println("체크아웃이 완료되었습니다.");
        }
    }

    private String askName() {
        System.out.print("이름을 입력해 주세요: ");
        return scan.nextLine();
    }

    public static void main(String[] args) {
        animalHotelSystem hotelSystem = new animalHotelSystem();
        hotelSystem.welcome();
    }
}