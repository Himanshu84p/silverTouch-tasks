package Weatherstation_prac89;
import java.util.ArrayList;
import java.util.List;

class WeatherData {
    public long timestamp;
    public double temperature;
    public double humidity;
    public double windSpeed;

    public WeatherData(double temperature, double humidity, double windSpeed) {
        this.timestamp = System.currentTimeMillis() / 1000L;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }
}

class Forecast {
    public double predictedTemperature;
    public double predictedHumidity;
    public double predictedWindSpeed;

    public Forecast(double temp, double humidity, double windSpeed) {
        this.predictedTemperature = temp;
        this.predictedHumidity = humidity;
        this.predictedWindSpeed = windSpeed;
    }
}

class WeatherStation {
    public String name;
    public List<WeatherData> weatherHistory;

    public WeatherStation(String name) {
        this.name = name;
        this.weatherHistory = new ArrayList<>();
    }

    public void collectWeatherData(double temperature, double humidity, double windSpeed) {
        WeatherData data = new WeatherData(temperature, humidity, windSpeed);
        weatherHistory.add(data);
    }

    public Forecast generateForecast() {
        // Simple forecast: use the average of historical data
        if (weatherHistory.isEmpty()) {
            return new Forecast(0.0, 0.0, 0.0);
        }

        double avgTemp = 0.0, avgHumidity = 0.0, avgWindSpeed = 0.0;
        for (WeatherData data : weatherHistory) {
            avgTemp += data.temperature;
            avgHumidity += data.humidity;
            avgWindSpeed += data.windSpeed;
        }

        avgTemp /= weatherHistory.size();
        avgHumidity /= weatherHistory.size();
        avgWindSpeed /= weatherHistory.size();

        return new Forecast(avgTemp, avgHumidity, avgWindSpeed);
    }
}

public class Prac89_WeatherStationApp {
    public static void main(String[] args) {
        WeatherStation station1 = new WeatherStation("Station 1");
        WeatherStation station2 = new WeatherStation("Station 2");

        // Collect data from stations
        station1.collectWeatherData(25.0, 60.0, 10.0);
        station1.collectWeatherData(28.0, 55.0, 12.0);
        station2.collectWeatherData(22.0, 70.0, 8.0);
        station2.collectWeatherData(20.0, 75.0, 9.0);

        // Generate forecasts
        Forecast forecast1 = station1.generateForecast();
        Forecast forecast2 = station2.generateForecast();

        // Display forecasts
        System.out.println("Forecast for " + station1.name + ":");
        System.out.println("Temperature: " + forecast1.predictedTemperature + "°C, Humidity: " + forecast1.predictedHumidity + "%, Wind Speed: " + forecast1.predictedWindSpeed + " m/s");

        System.out.println("\nForecast for " + station2.name + ":");
        System.out.println("Temperature: " + forecast2.predictedTemperature + "°C, Humidity: " + forecast2.predictedHumidity + "%, Wind Speed: " + forecast2.predictedWindSpeed + " m/s");
    }
}
