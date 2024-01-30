import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class WeatherData {
    private double temperature;
    private double humidity;
    private double pressure;
    private long timestamp;

    public WeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.timestamp = System.currentTimeMillis();
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

class Sensor {
    private String type;

    public Sensor(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public WeatherData collectData() {
        // Simulate collecting data from the sensor
        Random rand = new Random();
        double temperature = 20 + rand.nextDouble() * 15;  // Range: 20-35°C
        double humidity = 40 + rand.nextDouble() * 30;     // Range: 40-70%
        double pressure = 980 + rand.nextDouble() * 40;    // Range: 980-1020 hPa

        return new WeatherData(temperature, humidity, pressure);
    }
}

class WeatherStation {
    private List<Sensor> sensors;
    private List<WeatherData> weatherDataList;

    public WeatherStation() {
        this.sensors = new ArrayList<>();
        this.weatherDataList = new ArrayList<>();
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void collectDataFromSensors() {
        for (Sensor sensor : sensors) {
            WeatherData data = sensor.collectData();
            weatherDataList.add(data);
        }
    }

    public List<WeatherData> getWeatherData() {
        return weatherDataList;
    }

    public void analyzeWeatherTrends() {
        // Analyzing weather trends based on temperature
        List<WeatherData> weatherDataList = getWeatherData();

        if (weatherDataList.size() < 2) {
            System.out.println("Insufficient data for trend analysis.");
            return;
        }

        weatherDataList.sort(Comparator.comparing(WeatherData::getTimestamp));

        WeatherData latestData = weatherDataList.get(weatherDataList.size() - 1);
        WeatherData previousData = weatherDataList.get(weatherDataList.size() - 2);

        if (latestData.getTemperature() > previousData.getTemperature()) {
            System.out.println("Temperature is increasing.");
        } else if (latestData.getTemperature() < previousData.getTemperature()) {
            System.out.println("Temperature is decreasing.");
        } else {
            System.out.println("Temperature remains constant.");
        }
    }

    public void provideWeatherForecast() {
        // Providing a simple weather forecast based on humidity
        List<WeatherData> weatherDataList = getWeatherData();

        if (weatherDataList.isEmpty()) {
            System.out.println("No data available for forecasting.");
            return;
        }

        WeatherData latestData = weatherDataList.get(weatherDataList.size() - 1);

        if (latestData.getHumidity() > 70) {
            System.out.println("High humidity expected. Chance of rain.");
        } else if (latestData.getHumidity() < 30) {
            System.out.println("Low humidity expected. Dry weather.");
        } else {
            System.out.println("Moderate humidity expected. Normal weather conditions.");
        }
    }
}

public class Prac75_WeatherStationDataAnalysis {
    public static void main(String[] args) {
        // Create a weather station
        WeatherStation weatherStation = new WeatherStation();

        // Add sensors to the weather station
        Sensor temperatureSensor = new Sensor("Temperature");
        Sensor humiditySensor = new Sensor("Humidity");
        Sensor pressureSensor = new Sensor("Pressure");

        weatherStation.addSensor(temperatureSensor);
        weatherStation.addSensor(humiditySensor);
        weatherStation.addSensor(pressureSensor);

        // Collect data from sensors
        weatherStation.collectDataFromSensors();

        // Display collected weather data
        System.out.println("Collected Weather Data:");
        List<WeatherData> weatherDataList = weatherStation.getWeatherData();
        for (WeatherData data : weatherDataList) {
            System.out.println("Timestamp: " + data.getTimestamp() +
                    ", Temperature: " + data.getTemperature() + "°C" +
                    ", Humidity: " + data.getHumidity() + "%" +
                    ", Pressure: " + data.getPressure() + " hPa");
        }

        // Analyze weather trends
        weatherStation.analyzeWeatherTrends();

        // Provide weather forecast
        weatherStation.provideWeatherForecast();
    }
}

