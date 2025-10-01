package day15_SpaceShooter;

import javax.swing.*;
import java.awt.*;

// Simple class for enemy UFOs
class Enemy {   

    private int x, w, h;
    private double y;   // use double for smooth falling
    private Image img;

    Enemy(int x, int y, int w, int h, String path) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = new ImageIcon(getClass().getResource(path)).getImage();
    }

    // Move down the screen by dy pixels
    void update(double dy) {
        y += dy; 
    }
    // Draw enemy sprite
    void draw(Graphics g, JPanel panel) {
        g.drawImage(img, x, (int)Math.round(y), w, h, panel);
    }
    // Rectangle for collision detection
    Rectangle getBounds() { 
        return new Rectangle(x, (int)Math.round(y), w, h); 
    }

    // getters if used inside GamePanel
    int getX() { return x; }
    int getY() { return (int)Math.round(y); }
    int getW() { return w; }
    int getH() { return h; }
}
