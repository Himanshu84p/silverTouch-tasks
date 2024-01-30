interface coffee {
    int cost();
}

class BasicCoffee implements coffee {
    @Override
    public int cost(){
        return 5;
    }
}

class coffeeDecorator implements coffee {

    protected coffee decCoffee;

    coffeeDecorator(coffee decCoffee){
        this.decCoffee = decCoffee;
    }

    @Override
    public int cost(){
        return decCoffee.cost();
    }

}

class MilkDecorator extends coffeeDecorator{

    MilkDecorator(coffee decCoffee){
        super(decCoffee);
    }

    @Override
    public int cost(){
        return decCoffee.cost()+2;
    }

}

class SugarDecorator extends coffeeDecorator {
     SugarDecorator(coffee decCoffee){
        super(decCoffee);
    }

    @Override
    public int cost(){
        return decCoffee.cost()+1;
    }
}

public class Main {

    public static void main(String[] args) {
        coffee normalCoffee = new BasicCoffee();

        coffeeDecorator CoffeewithMilk = new MilkDecorator(normalCoffee);
        coffeeDecorator CoffeewithMilkAndSugar = new SugarDecorator(CoffeewithMilk);

        System.out.println("Cost of basic coffee: " + normalCoffee.cost());
        System.out.println("Cost of coffee with milk: " + CoffeewithMilk.cost());
        System.out.println("Cost of coffee with milk and sugar: " + CoffeewithMilkAndSugar.cost());
    }
    
}