import java.util.Random;
import java.util.Scanner;

public class test {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num, sum;

        while (true) {
            Random rd = new Random();
            num = rd.nextInt(6) + 5;
            sum = scan.nextInt();
            if (sum == 1) {
                System.out.println(num);
            } else {
                break;
            }
        }
    scan.close();
    }
}