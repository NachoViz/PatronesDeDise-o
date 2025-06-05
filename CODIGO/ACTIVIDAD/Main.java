public class Main {
    public static void main(String[] args) {
        // Crear personaje
        Personaje heroe = new Personaje("Héroe", 3);
        heroe.mostrarEstado();
        
        // Crear mapa
        Mapa mapa = new Mapa("Bosque Encantado", 10);
        mapa.mostrarInfo();
        
        // Mover personaje
        heroe.mover(2, 3);
        
        // Verificar posición en mapa
        if (mapa.esPosicionValida(2, 3)) {
            System.out.println("Posición válida en el mapa");
        }
        
        // Sistema de vidas
        heroe.perderVida();
        heroe.ganarVida();
        heroe.perderVida();
        heroe.perderVida();
        heroe.mostrarEstado();
        
        // Verificar si el personaje sigue vivo
        if (!heroe.estaVivo()) {
            System.out.println("El personaje ha muerto");
        }
    }
}