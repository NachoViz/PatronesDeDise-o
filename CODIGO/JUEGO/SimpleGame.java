import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SimpleGame extends JPanel implements KeyListener, Runnable {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    private static final long NANOS_PER_SECOND = 1_000_000_000L;
    private static final double TARGET_FPS = 60.0;
    private static final double TARGET_UPDATE_TIME = NANOS_PER_SECOND / TARGET_FPS;

    private Personaje personaje;
    private ArrayList<Powerup> powerups;
    private Random random;
    
    // Variables para el control de movimiento
    private int inputX = 0;
    private int inputY = 0;
    private boolean running = true;

    public SimpleGame() {
        personaje = new Personaje(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        powerups = new ArrayList<>();
        random = new Random();
        
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        
        // Iniciar el hilo del juego
        new Thread(this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        personaje.draw(g);
        
        for (Powerup powerup : powerups) {
            powerup.draw(g);
        }
        
        // Dibujar instrucciones
        g.setColor(Color.WHITE);
        g.drawString("Controles: WASD para mover, 1-3 para crear powerups", 10, 20);
        g.drawString("1: Speed Boost, 2: Size Increase, 3: Health Boost", 10, 40);
        
        // Mostrar FPS (opcional)
        g.drawString(String.format("FPS: %.1f", getFPS()), SCREEN_WIDTH - 100, 20);
    }

    private double fps = 0;
    private double getFPS() {
        return fps;
    }

    public void update(double deltaTime) {
        personaje.move(inputX, inputY, deltaTime);
        personaje.update(deltaTime);
        
        for (int i = 0; i < powerups.size(); i++) {
            Powerup powerup = powerups.get(i);
            if (powerup.checkCollision(personaje.getX(), personaje.getY(), personaje.getSize())) {
                powerup.applyEffect(personaje);
                powerups.remove(i);
                i--;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_W:
                inputY = -1;
                break;
            case KeyEvent.VK_S:
                inputY = 1;
                break;
            case KeyEvent.VK_A:
                inputX = -1;
                break;
            case KeyEvent.VK_D:
                inputX = 1;
                break;
            case KeyEvent.VK_1:
                createPowerup("speed", new SpeedBoostEffect(), Color.YELLOW);
                break;
            case KeyEvent.VK_2:
                createPowerup("size", new SizeIncreaseEffect(), Color.GREEN);
                break;
            case KeyEvent.VK_3:
                createPowerup("health", new HealthBoostEffect(), Color.RED);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_W:
                if (inputY == -1) inputY = 0;
                break;
            case KeyEvent.VK_S:
                if (inputY == 1) inputY = 0;
                break;
            case KeyEvent.VK_A:
                if (inputX == -1) inputX = 0;
                break;
            case KeyEvent.VK_D:
                if (inputX == 1) inputX = 0;
                break;
        }
    }

    private void createPowerup(String type, PowerupEffect effect, Color defaultColor) {
        int x = random.nextInt(SCREEN_WIDTH - 40) + 20;
        int y = random.nextInt(SCREEN_HEIGHT - 40) + 20;
        powerups.add(new Powerup(x, y, type, effect, defaultColor));
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        int frames = 0;
        int updates = 0;
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / TARGET_UPDATE_TIME;
            lastTime = now;
            
            while (delta >= 1) {
                update(1.0); // Actualizar con delta time fijo para física consistente
                updates++;
                delta--;
            }
            
            repaint();
            frames++;
            
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                frames = 0;
                updates = 0;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Game con Powerups - Movimiento Fluido");
            SimpleGame game = new SimpleGame();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}