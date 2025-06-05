package weather;

/**
 * Displays current weather conditions by implementing Observer and DisplayElement interfaces.
 */
public class CurrentConditionsDisplay implements ObserverInterface, DisplayElement {
    // State variables to store weather data
    private float temperature;
    private float humidity;
    
    // Reference to the Subject (WeatherData)
    private Subject weatherData;

    /**
     * Constructor registers this display as an observer
     * @param weatherData The weather data subject to observe
     */
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this); // Self-registration
    }

    /**
     * Called when weather data changes (from Observer interface)
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display(); // Update display automatically
    }

    /**
     * Displays current conditions (from DisplayElement interface)
     */
    @Override
    public void display() {
        System.out.printf("Current conditions: %.1f°F and %.1f%% humidity%n", 
                         temperature, humidity);
    }
}