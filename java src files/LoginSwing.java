import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginSwing {
    private JFrame frame;
    private JTextField emailField;
    private JTextField userTypeField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginSwing window = new LoginSwing();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginSwing() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel emailLabel = new JLabel("Email:");
        frame.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setColumns(10);
        frame.getContentPane().add(emailField);

        JLabel userTypeLabel = new JLabel("User Type:");
        frame.getContentPane().add(userTypeLabel);

        userTypeField = new JTextField();
        userTypeField.setColumns(10);
        frame.getContentPane().add(userTypeField);

        JLabel passwordLabel = new JLabel("Password:");
        frame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        frame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = emailField.getText();
                String user = userTypeField.getText();
                String pass = new String(passwordField.getPassword());

                try {
                    if (LoginSwing.validate(name, user, pass)) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid credentials.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });
        frame.getContentPane().add(loginButton);
    }

    private static boolean validate(String name, String user, String pass) {
    }
}