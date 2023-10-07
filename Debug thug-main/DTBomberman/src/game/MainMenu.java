package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Bomberman Start Menu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("Bombingman.png"); 
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Create a transparent panel to overlay on top of the background for UI elements
        JPanel overlayPanel = new JPanel();
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(new GridLayout(3, 1));

        // Create a label for the game title
        JLabel titleLabel = new JLabel("Bomberman", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Create buttons for "Start Game" and "How to Play"
        JButton startButton = new JButton("Start Game");
        JButton howToPlayButton = new JButton("How to Play");

        // Add action listeners for the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the Bomberman game
                startBombermanGame();
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display instructions on how to play
                JOptionPane.showMessageDialog(null, "Use the arrow keys to move Bomberman.\nPress Enter to place a bomb.\nBlow up enemies to win.", "How to Play", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add components to the overlay panel
        overlayPanel.add(titleLabel);
        overlayPanel.add(startButton);
        overlayPanel.add(howToPlayButton);

        // Add the overlay panel to the menu panel
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(overlayPanel, BorderLayout.CENTER);

        // Add the menu panel to the frame
        add(menuPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    private void startGame() {
    	Main game = new Main();
         game.Start();
    }

    private void showInstructions() {
        // Display game instructions in a dialog box
        JOptionPane.showMessageDialog(this,
                "How to Play Bomberman:\n" +
                "1. Use the arrow keys to move Bomberman.\n" +
                "2. Press Enter to place a bomb.\n" +
                "3. The goal is to blow up enemies and walls.\n" +
                "4.DO NOT LET THE RED ENEMY TO TOUCH BOMBERMAN or the game will restart.",
                "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }
}
