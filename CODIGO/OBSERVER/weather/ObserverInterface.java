package weather;

/**
 * Interfaz Observer que define el contrato para objetos que deben ser notificados
 * cuando cambian los datos meteorológicos en un Subject (Observable).
 */
public interface ObserverInterface {
    /**
     * Método llamado cuando el Subject actualiza sus datos
     * @param temp Temperatura actual en grados Fahrenheit
     * @param humidity Humedad relativa en porcentaje (0-100)
     * @param pressure Presión atmosférica en milibares
     */
    public void update(float temp, float humidity, float pressure);
}