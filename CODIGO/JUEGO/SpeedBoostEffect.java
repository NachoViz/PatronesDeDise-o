// SpeedBoostEffect.java
public class SpeedBoostEffect implements PowerupEffect {
    @Override
    public void applyEffect(Personaje personaje) {
        personaje.setSpeed(personaje.getSpeed() * 1.5);
        System.out.println("¡Velocidad aumentada!");
    }
}