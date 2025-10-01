package day15_SpaceShooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Random rng = new Random();
    // Screen
    private final int PANEL_W = 700, PANEL_H = 700;
    private final Timer timer;

    // Player
    private int playerX = 325, playerY = 600;
    private int playerSpeed = 10;
    private Image playerImage;
    private Image backgroundImage;

    // Input states
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean shootPressed = false;

    // Bullets
    private final List<Point> bullets = new ArrayList<>();
    private final int BULLET_W = 20, BULLET_H = 20;
    private final int BULLET_SPEED = 14;
    private final int SHOT_COOLDOWN_TICKS = 10; // ~0.16s @60fps
    private int shotCooldown = 0;
    private Image bulletImage;


    // Enemies
    private static final int ENEMY_W = 45, ENEMY_H = 45;
    private List<Enemy> enemies = new ArrayList<>();
    private double enemySpeed = 1.0;   // start speed
    private final String[] enemySprites = {
        "Enemies/GreenEnemyUFO.png",
        "Enemies/PinkEnemyUFO.png",
        "Enemies/LightBlueEnemyUFO.png",
        "Enemies/BlueEnemyUFO.png",
        "Enemies/OrangeEnemyUFO.png",
        "Enemies/RedEnemyUFO.png"
    };

    // Spawning & state
    private int enemySpawnCounter = 0;   // counts frames
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(PANEL_W, PANEL_H));
        setFocusable(true);
        addKeyListener(this);

        // Load Images
        backgroundImage = new ImageIcon(getClass().getResource("Background/SpaceBackGround.png")).getImage();
        playerImage = new ImageIcon(getClass().getResource("Ship/UserRocket.png")).getImage();
        bulletImage = new ImageIcon(getClass().getResource("Ship/Bullet.png")).getImage();


        // Game loop: runs actionPerformed() 60 times per second
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background first 
        g.drawImage(backgroundImage, 0, 0, PANEL_W, PANEL_H, this);

        // player rocket
        g.drawImage(playerImage, playerX, playerY, ENEMY_W, ENEMY_H, this);

        // Draw bullets 
        for (Point p : bullets) {
            g.drawImage(bulletImage, p.x, p.y, BULLET_W, BULLET_H, this);
        }
        // All enemies
        for (Enemy e : enemies) {
            e.draw(g, this);
        }
        // show game over
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(Font.BOLD, 32f));
            g.drawString("GAME OVER", PANEL_W/2 - 100, PANEL_H/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            repaint();
            return;
        }
        // Player movement 
        if (leftPressed && playerX > 10) {
            playerX -= playerSpeed;
        } 
        if (rightPressed && playerX < PANEL_W - 60) {
            playerX += playerSpeed;
        }
         // Enemy movement 
        for (Enemy en : enemies) {
            en.update(enemySpeed);
        }

        // Spawn enemies 
        enemySpawnCounter++;
        if (enemySpawnCounter > 30) {
            int x = rng.nextInt(PANEL_W - 45);
            int randIndex = rng.nextInt(enemySprites.length);

            String spritePath = enemySprites[randIndex];
            enemies.add(new Enemy(x, -45, 45, 45, spritePath)); // start just above the screen
            enemySpawnCounter = 0;
        }
            
        // Shooting 
        if (shotCooldown > 0){
            shotCooldown--;
        }
        if (shootPressed && shotCooldown == 0) {
            int bulletX = playerX + 25 - BULLET_W / 2; // center on 50px rocket
            int bulletY = playerY - BULLET_H;          // just above the rocket
            bullets.add(new Point(bulletX, bulletY));
            shotCooldown = SHOT_COOLDOWN_TICKS;
        }

        //  Move bullets upward and remove off screen 
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Point p = bullets.get(i);
            p.y -= BULLET_SPEED;           // fly up
            if (p.y + BULLET_H < 0) {      // off-screen top
                bullets.remove(i);
            }
        }

        // Collision with enemy and bullets
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy en = enemies.get(i);
            Rectangle enemyRect = en.getBounds();

            for (int j = bullets.size() - 1; j >= 0; j--) {
                Point p = bullets.get(j);
                Rectangle bulletRect = new Rectangle(p.x, p.y, BULLET_W, BULLET_H);

                if (bulletRect.intersects(enemyRect)) {
                    // remove enemy and bullet
                    enemies.remove(i);
                    bullets.remove(j);
                    break; // enemy destroyed, stop checking more bullets
                }
            }
        }
        // Collision with enemy and player
        Rectangle playerRect = new Rectangle(playerX, playerY, 50, 50);
        for (Enemy en : enemies) {
            if (playerRect.intersects(en.getBounds())) {
                gameOver = true;
                break;
            }
        }

        // Collision when enemy reaches bottom 
        for (Enemy en : enemies) {
            if (en.getY() + en.getH() >= PANEL_H) {
                gameOver = true;
                break;
            }
        }
        // Difficulty ramp 
        if (enemySpeed < 10.0) {        
            enemySpeed += 0.001;       // tiny smooth increase
        }

        repaint();  // redraw everything
    }

    // Keyboard input 
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) leftPressed = true;
        if (key == KeyEvent.VK_RIGHT) rightPressed = true;
        if (key == KeyEvent.VK_SPACE) shootPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) leftPressed = false;
        if (key == KeyEvent.VK_RIGHT) rightPressed = false;
        if (key == KeyEvent.VK_SPACE) shootPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
