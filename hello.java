import java.util.Scanner;

public class hello {
    public static Scanner scan;
    public static int stage01() {
        int nextState = 0;
        //Scanner scan = new Scanner(System.in);
        System.out.println("안녕하세요");
        String answer = "";
        answer = scan.next();
        if (answer.equals("안녕")) {
            nextState = 1;
        } else {
            nextState = 2;
        }
        //scan.close();
        return nextState;
    }

    public static int stage02() {
        System.out.println("잘 지내시고 계시죠?");
        return 3;
    }

    public static int stage03() {
        System.out.println("무슨 일 있으신가요?");
        return 3;
    }

    public static int stage04() {
        //Scanner scan = new Scanner(System.in);
        scan.next();
        System.out.println("그런일이 있으셨군요, 다음에 봐요");
        //scan.close();
        return 0;
    }

    public static void main(String[] args) {
        int state = 0;
        scan = new Scanner(System.in);

        while (true) {
            switch (state) {
                case 0:
                    state = stage01();
                    break;
                case 1:
                    state = stage02();
                    break;
                case 2:
                    state = stage03();
                    break;
                case 3:
                    state = stage04();
                    break;
                default:
                    state = 0;
            }
        }
    }
}
