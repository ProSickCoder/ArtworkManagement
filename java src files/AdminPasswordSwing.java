import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminPasswordSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new AdminPasswordForm().setVisible(true);
        });
    }
}

class AdminPasswordForm extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField oldPwdField;
    private JPasswordField newPwdField;
    private JPasswordField cnfrmPwdField;
    private JButton changeButton;

    public AdminPasswordForm() {
        setTitle("Change Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Old Password:"));
        oldPwdField = new JPasswordField();
        add(oldPwdField);

        add(new JLabel("New Password:"));
        newPwdField = new JPasswordField();
        add(newPwdField);

        add(new JLabel("Confirm Password:"));
        cnfrmPwdField = new JPasswordField();
        add(cnfrmPwdField);

        changeButton = new JButton("Change Password");
        changeButton.addActionListener(this);
        add(changeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeButton) {
            String email = emailField.getText();
            String oldPwd = new String(oldPwdField.getPassword());
            String newPwd = new String(newPwdField.getPassword());
            String cnfrmPwd = new String(cnfrmPwdField.getPassword());

            if (newPwd.equals("") || cnfrmPwd.equals("")) {
                JOptionPane.showMessageDialog(this, "New Password and Confirm Password, both are required.");
                return;
            } else if (!newPwd.equals(cnfrmPwd)) {
                JOptionPane.showMessageDialog(this, "New Password and Confirm Password do not match.");
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/art_gallery", "root", "rutuja8079")) {
                PreparedStatement ps = conn.prepareStatement("update login set password = ? where email = ? and password = ?");
                ps.setString(1, newPwd);
                ps.setString(2, email);
                ps.setString(3, oldPwd);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Password changed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to change password.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}