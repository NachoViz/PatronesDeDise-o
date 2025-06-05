// SizeIncreaseEffect.java
public class SizeIncreaseEffect implements PowerupEffect {
    @Override
    public void applyEffect(Personaje personaje) {
        personaje.setSize(personaje.getSize() + 10);
        System.out.println("¡Tamaño aumentado!");
    }
}