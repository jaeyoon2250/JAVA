import java.util.Scanner;

public class parking_lot_tower {
    private String[] car_num = new String[8];
    private Scanner scan = new Scanner(System.in);

    // 현재 주차된 차량의 수를 반환하는 메서드
    public int car_num_sum() {
        int arraySize = 0;
        for (int i = 0; i < car_num.length; i++) {
            if (car_num[i] != null) {
                arraySize++;
            }
        }
        return arraySize;
    }

    // 주차장 관리 프로그램 시작 메서드
    public void wellcome() {
        String in = "입고";
        String out = "출고";
        String done = "종료";
        String choose = "";
        while (true) {
            System.out.println("폴리텍주차장에 어서오십시오.");
            System.out.println("주차 프로그램을 종료하려면 '종료' 를 입력해 주십시오.");
            System.out.print("입고와 출고중 선택해주십시오: ");
            choose = scan.nextLine();
            if (choose.equals(in)) {
                Incoming(); // 입고 처리 메서드 호출
                continue;
            } else if (choose.equals(out)) {
                outcoming(); // 출고 처리 메서드 호출
                continue;
            } else if (choose.equals(done)) {
                System.out.println("이용해주셔서 감사합니다.");
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
                continue;
            }
        }
        scan.close();
    }

    // 차량 입고 처리 메서드
    public void Incoming() {
        while (true) {
            int car_count = car_num_sum(); // 현재 주차된 차량 수 확인
            String win = "";
            System.out.println("입고 화면입니다.");
            System.out.printf("총 입고가능한 차량수는 %d대 입니다.", car_num.length);
            System.out.println(
                    "현재 입고되어있는 차량수는 " + car_count + " 대 이고, 입고 가능한 차량수는 " + (car_num.length - car_count)
                            + " 대 입니다.");
            if (car_count < 8) {
                System.out.print("입고하고자 하는 차량의 번호를 입력해주세요: ");
                win = scan.nextLine();
                boolean exists = false;
                for (int i = 0; i < car_count; i++) {
                    if (win.equals(car_num[i])) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    car_num[car_count] = win; // 차량 주차 처리
                    System.out.println("차량번호 " + win + "번이 주차되었습니다.");
                } else {
                    System.out.println("같은 번호의 차량이 이미 주차되어 있습니다.");
                }
            } else {
                System.out.println("현재 주차 가능한 자리가 없습니다.");
            }
            System.out.println("안녕히 가십시오.");
            break;
        }
    }

    // 차량 출고 처리 메서드
    public void outcoming() {
        while (true) {
            int overlap = 0;
            int car_count = car_num_sum(); // 현재 주차된 차량 수 확인
            System.out.println("출고 화면입니다.");
            System.out.println(
                    "현재 입고되어있는 차량수는 " + car_count + " 대 입니다.");
            System.out.print("출고하실 차량의 번호를 입력해주세요: ");
            String wout = scan.nextLine();
            for (int i = 0; i < car_count; i++) {
                if (wout.equals(car_num[i])) {
                    overlap = 1;
                    car_num[i] = null; // 출고된 차량을 null로 표시
                    break;
                }
            }
            if (overlap == 1) {
                System.out.println("차량번호 " + wout + "번이 출고되었습니다.");
            } else {
                System.out.println("해당 번호의 차량이 주차되어 있지 않습니다.");
            }
            System.out.println("안녕히 가십시오.");
            break;
        }
    }
}