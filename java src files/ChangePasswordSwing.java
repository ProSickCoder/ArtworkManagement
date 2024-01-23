import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChangePasswordSwing {

    private JFrame frame;
    private JTextField emailField;
    private JPasswordField oldPwdField;
    private JPasswordField newPwdField;
    private JPasswordField cnfrmPwdField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangePasswordSwing window = new ChangePasswordSwing();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChangePasswordSwing() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Change Password");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEmail.setBounds(25, 25, 89, 29);
        frame.getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(124, 25, 261, 29);
        frame.getContentPane().add(emailField);
        emailField.setColumns(10);

        JLabel lblOldPassword = new JLabel("Old Password:");
        lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblOldPassword.setBounds(25, 75, 134, 29);
        frame.getContentPane().add(lblOldPassword);

        oldPwdField = new JPasswordField();
        oldPwdField.setBounds(124, 75, 261, 29);
        frame.getContentPane().add(oldPwdField);

        JLabel lblNewPassword = new JLabel("New Password:");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewPassword.setBounds(25, 125, 134, 29);
        frame.getContentPane().add(lblNewPassword);

        newPwdField = new JPasswordField();
        newPwdField.setBounds(124, 125, 261, 29);
        frame.getContentPane().add(newPwdField);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblConfirmPassword.setBounds(25, 175, 184, 29);
        frame.getContentPane().add(lblConfirmPassword);

        cnfrmPwdField = new JPasswordField();
        cnfrmPwdField.setBounds(124, 175, 261, 29);
        frame.getContentPane().add(cnfrmPwdField);

        JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String oldPwd = new String(oldPwdField.getPassword());
                String newPwd = new String(newPwdField.getPassword());
                String cnfrmPwd = new String(cnfrmPwdField.getPassword());

                if (newPwd.equals("") || cnfrmPwd.equals("")) {
                    JOptionPane.showMessageDialog(frame, "New Password and Confirm password, both are required.");
                    return;
                } else if (!newPwd.equals(cnfrmPwd)) {
                    JOptionPane.showMessageDialog(frame, "New Password and Confirm Password do not match.");
                    return;
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/art_gallery", "root", "rutuja8079");
                    PreparedStatement ps = conn.prepareStatement("update login set password= ? where email=? and password= ?");
                    ps.setString(1, newPwd);
                    ps.setString(2, email);
                    ps.setString(3, oldPwd);

                    int i = ps.executeUpdate();
                    if (i == 0) {
                        JOptionPane.showMessageDialog(frame, "Password does not change. Try again.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Password changed successfully.");
                    }

                    conn.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }}