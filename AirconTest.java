public class AirconTest {
    public static void main(String[] args){
        Aircon A1 = new Aircon(26);
        Aircon A2 = new Aircon(18);
        Aircon A3 = new Aircon(24);

        System.out.printf("에어컨의 요금: %d\n", Aircon.pay);
        System.out.printf("에어컨 A1의 온도: %d\n", A1.tem);
        System.out.printf("에어컨 A2의 온도: %d\n", A2.tem);
        System.out.printf("에어컨 A3의 온도: %d\n", A3.tem);
    }
}

class Aircon {
    static int pay = 0;
    int tem = 26;


    Aircon(int _tem) {
        tem = _tem;
        if(tem > 26){
            pay += 2;
        } else if(tem < 26) {
            pay += 3;
        } else {
            pay += 1;
        }
    }
}
