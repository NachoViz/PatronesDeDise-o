package weather;

public interface Subject {
    // Método para registrar un nuevo observador
    public void registerObserver(ObserverInterface o);
    
    // Método para eliminar un observador existente
    public void removeObserver(ObserverInterface o);
    
    // Método para notificar a todos los observadores registrados
    public void notifyObservers();
}