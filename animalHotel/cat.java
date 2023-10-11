package animalHotel;

class cat extends animal {
    public cat(String name){
        super(name);
    }
    // Animal 클래스의 getName 메소드를 오버라이드
    @Override
    public String getName() {
        return super.getName(); // 동물의 이름 반환
    }
    public void meow() {
        System.out.println("야옹");
    }
}