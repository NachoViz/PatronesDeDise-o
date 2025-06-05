// Powerup.java
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Powerup {
    private int x, y;
    private int size;
    private boolean active;
    private PowerupEffect effect;
    private Color color;
    private BufferedImage image;
    private String type;

    public Powerup(int x, int y, String type, PowerupEffect effect, Color color) {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.active = true;
        this.effect = effect;
        this.color = color;
        this.type = type;
        
        try {
            this.image = ImageIO.read(new File("assets/" + type + ".png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen para " + type + ", usando forma geométrica");
            this.image = null;
        }
    }

    public void applyEffect(Personaje personaje) {
        if (active) {
            effect.applyEffect(personaje);
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public boolean checkCollision(int personajeX, int personajeY, int personajeSize) {
        if (!active) return false;
        
        int distanceX = Math.abs(x - personajeX);
        int distanceY = Math.abs(y - personajeY);
        int minDistance = (size + personajeSize) / 2;
        
        return distanceX < minDistance && distanceY < minDistance;
    }

    public void draw(Graphics g) {
        if (!active) return;
        
        if (image != null) {
            g.drawImage(image, x - size/2, y - size/2, size, size, null);
        } else {
            g.setColor(color);
            g.fillOval(x - size/2, y - size/2, size, size);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public String getType() { return type; }
}