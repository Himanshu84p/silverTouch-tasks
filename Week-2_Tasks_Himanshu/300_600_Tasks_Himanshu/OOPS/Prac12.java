
import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface Flyweight {
    void operation();
}

// Concrete Flyweight
class ConcreteFlyweight implements Flyweight {
    @Override
    public void operation() {
        System.out.println("Concrete Flyweight operation");
    }
}

// Flyweight Factory
class FlyweightFactory {
    private Map<Integer, Flyweight> flyweights = new HashMap<>();

    Flyweight getFlyweight(int key) {
        return flyweights.computeIfAbsent(key, k -> new ConcreteFlyweight());
    }
}

// Client using Flyweights
public class Prac12 {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight(1);
        Flyweight flyweight2 = factory.getFlyweight(2);

        flyweight1.operation();
        flyweight2.operation();
    }
}

