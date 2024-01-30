package Oops_programs.Constructor_Overloading;

public class Person {
    public String name;
    public int age;
    public String gender;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static void main(String[] args) {
        Person person1 = new Person("Himanshu");
        Person person2 = new Person("Kevin", 21);
        Person person3 = new Person("Aditya", 21, "male");

        System.out.println("person1 name is " + person1.getName());
        System.out.println("person2 name is " + person2.getName() + " and age is : " + person2.getAge());
        System.out.println("person3 name is " + person3.getName() + " ,age and gender are : " + person3.getAge()
                + "," + person3.getGender());
    }
}
