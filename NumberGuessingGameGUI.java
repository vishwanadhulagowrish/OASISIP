import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int attempts;
    private int maxAttempts = 3;
    private int score = 0;
    private JLabel messageLabel;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        messageLabel = new JLabel("Guess the number between 1 and 100:");
        messageLabel.setBounds(50, 50, 300, 30);
        add(messageLabel);

        guessInput = new JTextField();
        guessInput.setBounds(50, 100, 100, 30);
        add(guessInput);

        guessButton = new JButton("Guess");
        guessButton.setBounds(170, 100, 100, 30);
        add(guessButton);

        attemptsLabel = new JLabel("Attempts: 0/" + maxAttempts);
        attemptsLabel.setBounds(50, 150, 200, 30);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(50, 180, 200, 30);
        add(scoreLabel);

        // Generate a random number between 1 and 100
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userGuess;
                try {
                    userGuess = Integer.parseInt(guessInput.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    return;
                }

                attempts++;
                if (userGuess < numberToGuess) {
                    messageLabel.setText("The number is higher. Try again.");
                } else if (userGuess > numberToGuess) {
                    messageLabel.setText("The number is lower. Try again.");
                } else {
                    messageLabel.setText("Congratulations! You've guessed the correct number.");
                    score += (maxAttempts - attempts + 1); // More points for fewer attempts
                    scoreLabel.setText("Score: " + score);
                    resetGame();
                    return;
                }

                attemptsLabel.setText("Attempts: " + attempts + "/" + maxAttempts);

                if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(null, "You've used all your attempts. The correct number was: " + numberToGuess);
                    resetGame();
                }
            }
        });

        setVisible(true);
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        attemptsLabel.setText("Attempts: 0/" + maxAttempts);
        guessInput.setText("");
        messageLabel.setText("Guess the number between 1 and 100:");
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}

