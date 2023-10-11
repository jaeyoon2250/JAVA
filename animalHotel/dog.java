package animalHotel;

class dog extends animal {
    public dog(String name){
        super(name);
    }
    // Animal 클래스의 getName 메소드를 오버라이드
    @Override
    public String getName() {
        return super.getName(); // 동물의 이름 반환
    }
    public void bark() {
        System.out.println("왈왈");
    }
}