// Visitor can visit 2 elements A 7 B
interface Visitor {
    void visitA(ElementA elementA);
    void visitB(ElementB elementB);
}

// Element A can accept different visitor and after that visitor can perform operation
class ElementA {
    public void accept(Visitor visitor){
        visitor.visitA(this);
    }
    public void operationA() {
        System.out.println("Performing operation A on ElementA");
    }
}
// Element B can accept different visitor and after that visitor can perform operation
class ElementB {
    public void accept(Visitor visitor){
        visitor.visitB(this);
    }
    public void operationB() {
        System.out.println("Performing operation B on ElementB");
    }
}

//VisitorConcrete class handles that which visitor should visit which element
class VisitorConcrete implements Visitor{
    @Override
    public void visitA(ElementA elementA) {
        System.out.println("Visiting ElementA");
        elementA.operationA();
    }
    @Override
    public void visitB(ElementB elementB) {
        System.out.println("Visiting ElementB");
        elementB.operationB();
    }
}


public class Prac8 {
    public static void main(String[] args) {
        // Create elements of the two types
        ElementA elementA = new ElementA();
        ElementB elementB = new ElementB();

        Visitor visitor = new VisitorConcrete();

        elementA.accept(visitor);
        elementB.accept(visitor);
    }
}
