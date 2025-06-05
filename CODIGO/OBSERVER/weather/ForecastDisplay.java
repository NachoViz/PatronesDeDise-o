package weather;

/**
 * Muestra pronósticos del clima basados en cambios en la presión atmosférica.
 * Implementa Observer para recibir actualizaciones y DisplayElement para mostrar el pronóstico.
 */
public class ForecastDisplay implements ObserverInterface, DisplayElement {
    private float currentPressure = 29.92f;  // presión estándar
    private float lastPressure;
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.print("Pronóstico: ");
        if (currentPressure > lastPressure) {
            System.out.println("Mejorando el tiempo - ¡Más soleado!");
        } else if (currentPressure == lastPressure) {
            System.out.println("Manteniéndose igual");
        } else if (currentPressure < lastPressure) {
            System.out.println("Cuidado con el clima más frío y lluvioso");
        }
        
        System.out.printf("(Presión actual: %.2f mb, Anterior: %.2f mb)%n%n", 
                         currentPressure, lastPressure);
    }
}