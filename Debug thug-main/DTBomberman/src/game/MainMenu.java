package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JLabel bombermanLabel;
    private int animationIndex = 0;
    private Timer animationTimer;

    public MainMenu() {
        // Set up the main menu JFrame
        setTitle("Bomberman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel for the menu components
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Create a gradient background
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, Color.YELLOW,
                        getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add the Bomberman title with animation
        bombermanLabel = new JLabel("Bomberman");
        bombermanLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        bombermanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(bombermanLabel);

        // Create and add a "Start" button with a larger font
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 32));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the game when the button is clicked
                startGame();
            }
        });
        menuPanel.add(startButton);

        // Create and add a "How to Play" button with a larger font
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setFont(new Font("Arial", Font.BOLD, 32));
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        howToPlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display game instructions when the button is clicked
                showInstructions();
            }
        });
        menuPanel.add(howToPlayButton);

        // Add the menu panel to the frame
        add(menuPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Start the animation timer
        animationTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateBomberman();
            }
        });
        animationTimer.start();
    }

    private void animateBomberman() {
        String[] animationFrames = {"Bomberman", "Bomberman.", "Bomberman..", "Bomberman..."};
        bombermanLabel.setText(animationFrames[animationIndex]);
        animationIndex = (animationIndex + 1) % animationFrames.length;
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
                        "4. DO NOT LET THE RED ENEMY TOUCH BOMBERMAN or the game will restart.",
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
