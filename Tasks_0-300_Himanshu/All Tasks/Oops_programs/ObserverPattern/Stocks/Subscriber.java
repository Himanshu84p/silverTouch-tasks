package Stocks;

public class Subscriber {
    private String name;
    private StockMarket s1 = new StockMarket();

    public Subscriber(String name) {
        super();
        this.name = name;
    }

    public void update() {
        System.out.println("Price Changed!!!");
    }

    public void StockMarketregister(StockMarket smobj) {
        s1 = smobj;
    }
}