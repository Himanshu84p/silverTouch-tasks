package Oops_programs.Class_Objects;

//class and objects 1st and 2nd problem
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayDetails() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
    }

    public static void main(String[] args) {

        Person person = new Person("himanshu", 21);

        person.displayDetails();
    }
}
