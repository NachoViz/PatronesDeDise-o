package weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Muestra estadísticas meteorológicas (promedios, máximos y mínimos)
 */
public class StatisticsDisplay implements ObserverInterface, DisplayElement {
    private List<Float> temperatureReadings = new ArrayList<>();
    private List<Float> humidityReadings = new ArrayList<>();
    private List<Float> pressureReadings = new ArrayList<>();
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        // Almacenar nuevas lecturas
        temperatureReadings.add(temperature);
        humidityReadings.add(humidity);
        pressureReadings.add(pressure);
        
        display();
    }

    @Override
    public void display() {
        System.out.println("\n--- Estadísticas Meteorológicas ---");
        
        if (!temperatureReadings.isEmpty()) {
            System.out.printf("Temperatura: Avg=%.1f°F, Max=%.1f°F, Min=%.1f°F%n",
                calculateAverage(temperatureReadings),
                calculateMax(temperatureReadings),
                calculateMin(temperatureReadings));
        }
        
        if (!humidityReadings.isEmpty()) {
            System.out.printf("Humedad: Avg=%.1f%%, Max=%.1f%%, Min=%.1f%%%n",
                calculateAverage(humidityReadings),
                calculateMax(humidityReadings),
                calculateMin(humidityReadings));
        }
        
        if (!pressureReadings.isEmpty()) {
            System.out.printf("Presión: Avg=%.1f mb, Max=%.1f mb, Min=%.1f mb%n",
                calculateAverage(pressureReadings),
                calculateMax(pressureReadings),
                calculateMin(pressureReadings));
        }
    }

    // Métodos auxiliares para cálculos estadísticos
    private float calculateAverage(List<Float> values) {
        return (float) values.stream()
                .mapToDouble(Float::floatValue)
                .average()
                .orElse(0.0);
    }

    private float calculateMax(List<Float> values) {
        return (float) values.stream()
                .mapToDouble(Float::floatValue)
                .max()
                .orElse(0.0);
    }

    private float calculateMin(List<Float> values) {
        return (float) values.stream()
                .mapToDouble(Float::floatValue)
                .min()
                .orElse(0.0);
    }
}