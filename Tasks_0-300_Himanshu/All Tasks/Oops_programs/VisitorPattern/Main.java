interface Visitor {
    void visitElementA(ElementA elementA);

    void visitElementB(ElementB elementB);
}

class ElementA {
    public void accept(Visitor visitor) {
        visitor.visitElementA(this);
    }

    public void operationA() {
        System.out.println("Operation A in ElementA");
    }
}

class ElementB {
    public void accept(Visitor visitor) {
        visitor.visitElementB(this);
    }

    public void operationB() {
        System.out.println("Operation B in ElementB");
    }
}

class ConcreteVisitor implements Visitor {
    @Override
    public void visitElementA(ElementA elementA) {
        System.out.println("Visiting ElementA");
        elementA.operationA();
    }

    @Override
    public void visitElementB(ElementB elementB) {
        System.out.println("Visiting ElementB");
        elementB.operationB();
    }
}

public class Main {
    public static void main(String[] args) {

        ElementA elementA = new ElementA();
        ElementB elementB = new ElementB();

        ConcreteVisitor visitor = new ConcreteVisitor();

        elementA.accept(visitor);
        elementB.accept(visitor);
    }
}
