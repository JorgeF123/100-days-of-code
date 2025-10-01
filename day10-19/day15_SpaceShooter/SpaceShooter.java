package day15_SpaceShooter;

import javax.swing.*;


public class SpaceShooter {
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create game window
            JFrame frame = new JFrame("Space Shooter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Add custom game panel (where all the game logic runs)
            GamePanel gamePanel = new GamePanel();
            frame.setContentPane(gamePanel);

            // Size window to fit panel
            frame.pack();;
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
            
            // Locks window 
            frame.setResizable(false);
            
            // Make sure game panel has keyboard focus
            gamePanel.requestFocusInWindow();
        });
    }
}

