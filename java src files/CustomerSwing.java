import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CustomerSwing extends JFrame {

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;

    public CustomerSwing() {
        setLayout(new FlowLayout());

        add(new JLabel("Email"));
        field1 = new JTextField(20);
        add(field1);

        add(new JLabel("Name"));
        field2 = new JTextField(20);
        add(field2);

        add(new JLabel("State"));
        field3 = new JTextField(20);
        add(field3);

        add(new JLabel("City"));
        field4 = new JTextField(20);
        add(field4);

        add(new JLabel("Pincode"));
        field5 = new JTextField(20);
        add(field5);

        add(new JLabel("Phone"));
        field6 = new JTextField(20);
        add(field6);

        add(new JLabel("Additional Information"));
        field7 = new JTextField(20);
        add(field7);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Login", "root", "rutuja8079");
                    PreparedStatement pst = conn.prepareStatement("insert into artist(email, a_name, state, city, pincode, phone) values(?,?,?,?,?,?)");

                    pst.setString(1, field1.getText());
                    pst.setString(2, field2.getText());
                    pst.setString(3, field3.getText());
                    pst.setString(4, field4.getText());
                    pst.setString(5, field5.getText());
                    pst.setString(6, field6.getText());
                    pst.setString(7, field7.getText());

                    int i = pst.executeUpdate();
                    if (i != 0) {
                        JOptionPane.showMessageDialog(null, "Record has been inserted");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to insert the data");
                    }
                    pst.close();
                    conn.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        add(submitButton);

        setTitle("Customer Information");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerSwing().setVisible(true);
            }
        });
    }
}