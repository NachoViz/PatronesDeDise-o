public class Personaje {
    private String nombre;
    private int posX;
    private int posY;
    private SistemaVidas vidas;

    public Personaje(String nombre, int vidasIniciales) {
        this.nombre = nombre;
        this.vidas = new SistemaVidas(vidasIniciales);
        this.posX = 0;
        this.posY = 0;
    }

    public void mover(int deltaX, int deltaY) {
        this.posX += deltaX;
        this.posY += deltaY;
        System.out.println(nombre + " se movió a (" + posX + ", " + posY + ")");
    }

    public void perderVida() {
        vidas.perderVida();
        System.out.println(nombre + " perdió una vida. Vidas restantes: " + vidas.getVidas());
    }

    public void ganarVida() {
        vidas.ganarVida();
        System.out.println(nombre + " ganó una vida. Vidas totales: " + vidas.getVidas());
    }

    public void mostrarEstado() {
        System.out.println(nombre + " - Posición: (" + posX + ", " + posY + ") - Vidas: " + vidas.getVidas());
    }

    public boolean estaVivo() {
        return vidas.estaVivo();
    }
}