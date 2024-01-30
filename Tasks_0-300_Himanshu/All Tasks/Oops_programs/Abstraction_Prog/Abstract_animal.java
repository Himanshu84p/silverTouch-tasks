package Oops_programs.Abstraction_Prog;

abstract class Animal {
    public abstract void eat();

    public abstract void sound();
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog is eating....");
    }

    @Override
    public void sound() {
        System.out.println("Dog Barks!!!");
    }
}

class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("Cat is eating....");
    }

    @Override
    public void sound() {
        System.out.println("Cat Meows!!!");
    }
}

public class Abstract_animal {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        Cat cat1 = new Cat();

        dog1.eat();
        dog1.sound();

        cat1.eat();
        cat1.sound();
    }
}
