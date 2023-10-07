package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class MainMenu extends JFrame {
    private Clip clip; // Sound clip

    public MainMenu() {
        // Set up the main menu JFrame
        setTitle("Bomberman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and set the background image
        ImageIcon backgroundImage = new ImageIcon("background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Create a JPanel for the menu components
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false); // Make the panel transparent
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add the Bomberman title
        JLabel titleLabel = new JLabel("Bomberman");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE); // Set text color
        menuPanel.add(titleLabel);

        // Create and add a "Start" button
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the game when the button is clicked
                startGame();
            }
        });
        menuPanel.add(startButton);

        // Create and add a "How to Play" button
        JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        howToPlayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display game instructions when the button is clicked
                showInstructions();
            }
        });
        menuPanel.add(howToPlayButton);

        // Add the menu panel to the content pane
        backgroundLabel.add(menuPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Play background music
        playBackgroundMusic("background.wav");
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

    private void playBackgroundMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
                clip.start(); // Start playing the music
            } else {
                System.err.println("Music file not found: " + musicFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

