import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExaminationSystem {

    private static int score = 0;
    private static int questionIndex = 0;
    private static String[] questions = {
            "What is 5 + 3?",
            "What is 10 - 4?",
            "What is 6 * 2?",
            "What is 9 / 3?",
            "What is 15 % 4?"
    };

    private static String[][] options = {
            {"6", "7", "8", "9"},
            {"5", "6", "7", "8"},
            {"11", "12", "13", "14"},
            {"2", "3", "4", "5"},
            {"2", "3", "4", "5"}
    };

    private static int[] correctAnswers = {2, 1, 1, 1, 2}; // Index of the correct answers

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Online Examination System - Math Quiz");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a card layout to switch between different panels
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Create Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(150, 50, 100, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(250, 50, 165, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 100, 100, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(250, 100, 165, 25);
        loginPanel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 150, 80, 25);
        loginPanel.add(loginButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(335, 150, 80, 25);
        loginPanel.add(resetButton);

        // Create Exam Panel
        JPanel examPanel = new JPanel();
        examPanel.setLayout(null);

        JLabel questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 500, 25);
        examPanel.add(questionLabel);

        JRadioButton option1 = new JRadioButton();
        option1.setBounds(50, 80, 100, 25);
        examPanel.add(option1);

        JRadioButton option2 = new JRadioButton();
        option2.setBounds(50, 110, 100, 25);
        examPanel.add(option2);

        JRadioButton option3 = new JRadioButton();
        option3.setBounds(50, 140, 100, 25);
        examPanel.add(option3);

        JRadioButton option4 = new JRadioButton();
        option4.setBounds(50, 170, 100, 25);
        examPanel.add(option4);

        ButtonGroup optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        JLabel timerLabel = new JLabel("Time Left: 10:00");
        timerLabel.setBounds(400, 20, 150, 25);
        examPanel.add(timerLabel);

        JButton nextButton = new JButton("Next");
        nextButton.setBounds(250, 250, 100, 25);
        examPanel.add(nextButton);

        // Create Results Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(null);

        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(200, 150, 200, 25);
        resultPanel.add(resultLabel);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(250, 200, 150, 25);
        resultPanel.add(backButton);

        // Add panels to main panel
        mainPanel.add(loginPanel, "loginPanel");
        mainPanel.add(examPanel, "examPanel");
        mainPanel.add(resultPanel, "resultPanel");

        // Add ActionListeners for navigation
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here you would add logic to validate login credentials
                cardLayout.show(mainPanel, "examPanel");
                loadQuestion(questionLabel, option1, option2, option3, option4);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passwordText.setText("");
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the selected answer
                int selectedAnswer = -1;
                if (option1.isSelected()) {
                    selectedAnswer = 0;
                } else if (option2.isSelected()) {
                    selectedAnswer = 1;
                } else if (option3.isSelected()) {
                    selectedAnswer = 2;
                } else if (option4.isSelected()) {
                    selectedAnswer = 3;
                }

                if (selectedAnswer == correctAnswers[questionIndex]) {
                    score++;
                }

                questionIndex++;

                if (questionIndex < questions.length) {
                    loadQuestion(questionLabel, option1, option2, option3, option4);
                } else {
                    // Show result
                    resultLabel.setText("Your Score: " + score + "/" + questions.length);
                    cardLayout.show(mainPanel, "resultPanel");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = 0;
                questionIndex = 0;
                cardLayout.show(mainPanel, "loginPanel");
            }
        });

        // Add the main panel to the frame
        frame.add(mainPanel);
        cardLayout.show(mainPanel, "loginPanel");
        frame.setVisible(true);
    }

    private static void loadQuestion(JLabel questionLabel, JRadioButton option1, JRadioButton option2, JRadioButton option3, JRadioButton option4) {
        questionLabel.setText(questions[questionIndex]);
        option1.setText(options[questionIndex][0]);
        option2.setText(options[questionIndex][1]);
        option3.setText(options[questionIndex][2]);
        option4.setText(options[questionIndex][3]);
    }
}
