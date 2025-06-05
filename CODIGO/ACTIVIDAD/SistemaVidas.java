public class SistemaVidas {
    private int vidas;

    public SistemaVidas(int vidasIniciales) {
        this.vidas = vidasIniciales;
    }

    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public void ganarVida() {
        vidas++;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean estaVivo() {
        return vidas > 0;
    }
}