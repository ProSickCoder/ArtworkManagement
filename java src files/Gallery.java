
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Gallery extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gallery frame = new Gallery();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Gallery() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JButton btnJoin = new JButton("JOIN");
        btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://localhost:3306/art_gallery";
                    String driver = "com.mysql.jdbc.Driver";
                    String userName = "root";
                    String password = "rutuja8079";

                    Class.forName(driver).newInstance();
                    Connection conn = DriverManager.getConnection(url, userName, password);

                    String id = "some id";
                    String name = "some name";
                    String state = "some state";
                    String city = "some city";
                    String pincode = "some pincode";
                    String phone = "some phone";
                    String email = "some email";

                    PreparedStatement pst = conn.prepareStatement("insert into artist(a_id, a_name, state, city, pincode, phone, email) values(?,?,?,?,?,?,?)");

                    pst.setString(1, id);
                    pst.setString(2, name);
                    pst.setString(3, state);
                    pst.setString(4, city);
                    pst.setString(5, pincode);
                    pst.setString(6, phone);
                    pst.setString(7, email);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successful...");

                    pst.close();
                    conn.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }
        });
        contentPane.add(btnJoin, BorderLayout.CENTER);
    }

}