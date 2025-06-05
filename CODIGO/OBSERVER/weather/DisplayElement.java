package weather;

/**
 * Interfaz que define el contrato para elementos visuales que muestran datos meteorológicos.
 * Parte fundamental del patrón Observer en el sistema de monitoreo del clima.
 */
public interface DisplayElement {
    /**
     * Método que debe implementarse para mostrar la información meteorológica
     * de acuerdo al formato específico de cada clase concreta.
     */
    void display();
}