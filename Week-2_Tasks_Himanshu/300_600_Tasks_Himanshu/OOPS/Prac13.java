
// Strategy interface
interface Strategy {
    void execute();
}

// Concrete strategies
class ConcreteStrategyA implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing Strategy A");
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void execute() {
        System.out.println("Executing Strategy B");
    }
}

// Context that uses a strategy
class Context {
    private Strategy strategy;

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    void executeStrategy() {
        if (strategy != null) {
            strategy.execute();
        } else {
            System.out.println("No strategy set");
        }
    }
}


public class Prac13 {
    public static void main(String[] args) {
        Context context = new Context();

        // Using Strategy A
        context.setStrategy(new ConcreteStrategyA());
        context.executeStrategy();

        // Using Strategy B
        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy();
    }
}
