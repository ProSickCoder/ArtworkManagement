import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EditFrame extends JDialog {

    private JTextField field11;
    private JTextField field22;
    private JTextField field33;
    private JTextField field44;
    private JTextField field55;
    private JTextField field66;
    private JTextField field77;
    private JTextField field88;

    public EditFrame(Frame parent, boolean modal) {
        super(parent, modal);
        setLayout(new FlowLayout());

        add(new JLabel("ID"));
        field11 = new JTextField(20);
        add(field11);

        add(new JLabel("Email"));
        field22 = new JTextField(20);
        add(field22);

        add(new JLabel("Name"));
        field33 = new JTextField(20);
        add(field33);

        add(new JLabel("State"));
        field44 = new JTextField(20);
        add(field44);

        add(new JLabel("City"));
        field55 = new JTextField(20);
        add(field55);

        add(new JLabel("Pincode"));
        field66 = new JTextField(20);
        add(field66);

        add(new JLabel("Phone"));
        field77 = new JTextField(20);
        add(field77);

        add(new JLabel("G ID"));
        field88 = new JTextField(20);
        add(field88);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://localhost:3306/art_gallery";
                    String driver = "com.mysql.jdbc.Driver";
                    String userName = "root";
                    String password = "rutuja8079";

                    Class.forName(driver).newInstance();
                    Connection conn = DriverManager.getConnection(url, userName, password);

                    String id = field11.getText();
                    String email = field22.getText();
                    String name = field33.getText();
                    String state = field44.getText();
                    String city = field55.getText();
                    String pincode = field66.getText();
                    String phone = field77.getText();
                    String g_id = field88.getText();

                    PreparedStatement pst = conn.prepareStatement("update artist set email=?, a_name=?, state=?, city=?, pincode=?, phone=? where a_id=?");

                    pst.setString(1, email);
                    pst.setString(2, name);
                    pst.setString(3, state);
                    pst.setString(4, city);
                    pst.setString(5, pincode);
                    pst.setString(6, phone);
                    pst.setString(7, id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successful...");

                    pst.close();
                    conn.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        add(submitButton);

        setTitle("Edit Artist");
        setSize(500, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditFrame(new JFrame(), true).setVisible(true);
            }
        });
    }
}