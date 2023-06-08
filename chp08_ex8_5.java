public class chp08_ex8_5 {
    public static void main(String[] args){
        Student park = new Student(2019122104, "Park");
        Student kim = new Student(2019206028, "Kim");
        Student lee = new Student(2019153237, "Lee");
        System.out.printf("Student 객체의 수: %d\n", Student.count);
        System.out.printf("ID: %d, NAME: %s\n", park.id, park.name);
        System.out.printf("ID: %d, NAME: %s\n", kim.id, kim.name);
        System.out.printf("ID: %d, NAME: %s\n", lee.id, lee.name);
    }
}


class Student {
    static int count = 0;

    int id;
    String name;


    Student(int _id, String _name){
        Student.count++; //같은 class 내부이기 때문에 Student. 을 생략해도 된다
        id = _id;
        name = _name;
    }
}