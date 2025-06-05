package weather;
import java.util.*;

public class WeatherStation {

    public static void main(String[] args) {
        // 1. Crear el objeto observable (Subject)
        WeatherData weatherData = new WeatherData();

        // 2. Crear displays (Observers) y registrarlos automáticamente
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        // 3. Simular cambios en las mediciones meteorológicas
        weatherData.setMeasurements(80, 65, 30.4f);  // Primera actualización
        weatherData.setMeasurements(82, 70, 29.2f);  // Segunda actualización
        weatherData.setMeasurements(78, 90, 29.2f);  // Tercera actualización
        
        // Put more here measurements here...
    }
}