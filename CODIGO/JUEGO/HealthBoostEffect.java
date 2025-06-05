// HealthBoostEffect.java
public class HealthBoostEffect implements PowerupEffect {
    @Override
    public void applyEffect(Personaje personaje) {
        personaje.setHealth(personaje.getHealth() + 20);
        System.out.println("¡Salud aumentada!");
    }
}