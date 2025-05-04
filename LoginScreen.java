import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }
}

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn, clearBtn;
    private JLabel messageLabel;
    private int attempts = 3;

    public LoginScreen() {
        setTitle("Login Screen");
        setSize(400, 200);
        setLayout(new GridLayout(5, 2, 5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginBtn = new JButton("Login");
        clearBtn = new JButton("Clear");
        messageLabel = new JLabel("");

        
        add(userLabel); add(usernameField);
        add(passLabel); add(passwordField);
        add(loginBtn); add(clearBtn);
        add(new JLabel("")); add(messageLabel);

       
        loginBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                if (username.equals(password)) {
                    messageLabel.setText("Login successful!");
                } else {
                    attempts--;
                    throw new LoginException("Invalid login! Attempts left: " + attempts);
                }
            } catch (LoginException le) {
                messageLabel.setText(le.getMessage());
                if (attempts == 0) {
                    loginBtn.setEnabled(false);
                    messageLabel.setText("Login failed. No attempts left.");
                }
            }
        } else if (ae.getSource() == clearBtn) {
            usernameField.setText("");
            passwordField.setText("");
            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}
