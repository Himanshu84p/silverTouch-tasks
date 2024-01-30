package Decorator_Prac4;

interface Component {
    void operation();
}

class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("Concrete Component operation");
    }
}

class Decorator implements Component {
    protected Component component;

    public Decorator(Component comp) {
        this.component = comp;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component comp) {
        super(comp);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Concrete Decorator A operation");
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component comp) {
        super(comp);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Concrete Decorator B operation");
    }
}

public class Prac4 {
    public static void main(String[] args) {
        Component comp = new ConcreteComponent();
        ConcreteDecoratorA decoA = new ConcreteDecoratorA(comp);
        ConcreteDecoratorB decoB = new ConcreteDecoratorB(decoA);

        decoB.operation();
    }
}
