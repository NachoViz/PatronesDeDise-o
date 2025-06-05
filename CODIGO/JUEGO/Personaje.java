import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Personaje {
    private double x, y; // Cambiado a double para mayor precisión
    private int size;
    private double speed;
    private double maxSpeed = 5.0;
    private double acceleration = 0.2;
    private double deceleration = 0.15;
    private double dx, dy; // Velocidad actual en cada eje
    private int health;
    private BufferedImage image;
    private String direction;

    public Personaje(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 40;
        this.speed = maxSpeed;
        this.health = 100;
        this.direction = "right";
        this.dx = 0;
        this.dy = 0;
        
        try {
            this.image = ImageIO.read(new File("assets/character.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen del personaje, usando forma geométrica");
            this.image = null;
        }
    }

    public void update(double deltaTime) {
        // Aplicar deceleración
        if (dx != 0) {
            dx = applyDeceleration(dx, deltaTime);
        }
        if (dy != 0) {
            dy = applyDeceleration(dy, deltaTime);
        }

        // Actualizar posición
        x += dx * deltaTime;
        y += dy * deltaTime;

        // Limitar a los bordes de la pantalla
        x = Math.max(size/2, Math.min(SimpleGame.SCREEN_WIDTH - size/2, x));
        y = Math.max(size/2, Math.min(SimpleGame.SCREEN_HEIGHT - size/2, y));
    }

    private double applyDeceleration(double velocity, double deltaTime) {
        if (velocity > 0) {
            velocity = Math.max(0, velocity - deceleration * deltaTime);
        } else if (velocity < 0) {
            velocity = Math.min(0, velocity + deceleration * deltaTime);
        }
        return velocity;
    }

    public void move(int inputX, int inputY, double deltaTime) {
        // Aplicar aceleración basada en input
        if (inputX != 0) {
            dx += inputX * acceleration * deltaTime;
            dx = Math.max(-maxSpeed, Math.min(maxSpeed, dx));
            
            if (inputX > 0) direction = "right";
            if (inputX < 0) direction = "left";
        }
        
        if (inputY != 0) {
            dy += inputY * acceleration * deltaTime;
            dy = Math.max(-maxSpeed, Math.min(maxSpeed, dy));
            
            if (inputY > 0) direction = "down";
            if (inputY < 0) direction = "up";
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            if (direction.equals("left")) {
                g.drawImage(image, (int)x + size/2, (int)y - size/2, -size, size, null);
            } else {
                g.drawImage(image, (int)x - size/2, (int)y - size/2, size, size, null);
            }
        } else {
            g.setColor(Color.BLUE);
            g.fillRect((int)x - size/2, (int)y - size/2, size, size);
        }
        
        // Dibujar barra de salud
        g.setColor(Color.RED);
        g.fillRect((int)x - size/2, (int)y - size/2 - 10, size, 5);
        g.setColor(Color.GREEN);
        g.fillRect((int)x - size/2, (int)y - size/2 - 10, (int)(size * (health / 100.0)), 5);
    }

    // Getters ahora devuelven las posiciones como enteros para compatibilidad
    public int getX() { return (int)x; }
    public int getY() { return (int)y; }
    public int getSize() { return size; }
    public double getSpeed() { return speed; }
    public int getHealth() { return health; }
    public void setSpeed(double speed) { this.maxSpeed = speed; }
    public void setSize(int size) { this.size = size; }
    public void setHealth(int health) { this.health = Math.min(health, 100); }
}