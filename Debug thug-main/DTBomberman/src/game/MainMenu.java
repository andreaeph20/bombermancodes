package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainMenu extends JFrame {
    private Clip backgroundMusic;

    public MainMenu() {
        // Set up the main menu JFrame
        setTitle("Bomberman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        try {
            Image backgroundImage = ImageIO.read(new File("background.png"));
            setContentPane(new ImagePanel(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a JPanel for the menu components
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add the Bomberman title
        JLabel titleLabel = new JLabel("Bomberman");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        // Add the menu panel to the frame
        add(menuPanel);

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

    private void playBackgroundMusic(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.start();
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
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

    class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
