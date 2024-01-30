package Weather;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature, float humidity);
}

interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void updateData(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        DataChanged();
    }

    private void DataChanged() {
        notifyObservers();
    }
}

class forecaster implements Observer {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public forecaster(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current condition: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

public class Main {
    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();
        forecaster forecast = new forecaster(weatherData);

        weatherData.updateData(80, 40);
        weatherData.updateData(55, 23);
        weatherData.updateData(45, 70);
    }
}
