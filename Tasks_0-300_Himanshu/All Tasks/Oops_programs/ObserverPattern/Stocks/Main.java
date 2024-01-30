package Stocks;

public class Main {
    public static void main(String[] args) {
        StockMarket s1 = new StockMarket();
        Subscriber sub1 = new Subscriber("Ved");
        Subscriber sub2 = new Subscriber("Apurva");
        Subscriber sub3 = new Subscriber("Mitul");

        s1.subscribe(sub1);
        s1.subscribe(sub2);
        s1.subscribe(sub3);

        sub1.StockMarketregister(s1);
        sub2.StockMarketregister(s1);
        sub3.StockMarketregister(s1);

        s1.objupdate(20);
    }
}
