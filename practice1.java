public class practice1 {

    public static void main(String[] args) {
    double _valueOfSupply = AccountingApp.valueOfSupply = 10000.0;
        System.out.println("Value of supply : " + _valueOfSupply);
        System.out.println("VAT : " + AccountingApp.getVAT());
        System.out.println("Total : " + AccountingApp.getTotal());

    }
}

class AccountingApp {
    // 공급가액
    public static double valueOfSupply;
    // 부가가치세율
    public static double vatRate = 0.1;

    public static double getVAT() {
        return valueOfSupply * vatRate;
    }

    public static double getTotal() {
        return valueOfSupply + getVAT();
    }
}
