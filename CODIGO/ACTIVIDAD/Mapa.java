public class Mapa {
    private String nombre;
    private int tamanio;
    private static Mapa instancia;

    private Mapa(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public static Mapa getInstance(String nombre, int tamanio){
        if(instancia == null){
            instancia = new Mapa(nombre, tamanio);
        }
        return instancia;
    }

    public void mostrarInfo() {
        System.out.println("Mapa: " + nombre + " - Tamaño: " + tamanio + "x" + tamaño);
    }

    public boolean esPosicionValida(int x, int y) {
        return x >= 0 && x < tamanio && y >= 0 && y < tamanio;
    }
}