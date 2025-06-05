package weather;
import java.util.ArrayList;

public class WeatherData implements Subject {
    // Lista para almacenar los observadores
    private ArrayList<ObserverInterface> observers;
    
    // Datos meteorológicos que serán observados
    private float temperature;
    private float humidity;
    private float pressure;

    // Constructor que inicializa la lista de observadores
    public WeatherData() {
        observers = new ArrayList<ObserverInterface>();
    }

    // Método para registrar un nuevo observador
    public void registerObserver(ObserverInterface o) {
        observers.add(o);
    }

    // Método para eliminar un observador
    public void removeObserver(ObserverInterface o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    // Método para notificar a todos los observadores
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            ObserverInterface observer = (ObserverInterface)observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    // Método que se llama cuando hay cambios en las mediciones
    public void measurementsChanged() {
        notifyObservers();
    }

    // Método para actualizar las mediciones meteorológicas y notificar a los observadores
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged(); // Dispara la notificación a los observers
    }

    // Método para obtener la temperatura actual
    public float getTemperature() {
        return temperature;
    }

    // Método para obtener la humedad actual
    public float getHumidity() {
        return humidity;
    }

    // Método para obtener la presión atmosférica actual
    public float getPressure() {
        return pressure;
    }
}