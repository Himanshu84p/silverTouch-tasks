abstract class AbstractFactory {
    public abstract void productA();

    public abstract void productB();
}

class ConcreteFactory1 extends AbstractFactory {
    @Override
    public void productA() {
        System.out.println("Product A from Factory 1!!");
    }

    @Override
    public void productB() {
        System.out.println("Product B from Factory 1!!");
    }
}

class ConcreteFactory2 extends AbstractFactory {
    @Override
    public void productA() {
        System.out.println("Product A from factory 2");
    }

    @Override
    public void productB() {
        System.out.println("Product B from factory 2");
    }
}

public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        AbstractFactory factory2 = new ConcreteFactory2();

        factory1.productA();
        factory1.productB();
        factory2.productA();
        factory2.productB();
    }
}