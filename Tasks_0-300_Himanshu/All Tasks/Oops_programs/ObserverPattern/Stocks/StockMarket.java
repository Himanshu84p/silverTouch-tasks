package Stocks;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private List<Subscriber> subscriber = new ArrayList<>();
    private int Price;

    public void subscribe(Subscriber sub) {
        subscriber.add(sub);
    }

    public void unsubscribe(Subscriber sub) {
        subscriber.remove(sub);
    }

    public void notifysub() {
        for (Subscriber sub : subscriber) {
            sub.update();
        }
    }

    public void objupdate(int Price) {
        this.Price += Price;
        notifysub();
    }
}
