import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Options frame = new Options();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Options() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnJoin = new JButton("Join");
        btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String url = "jdbc:mysql://localhost:3306/art_gallery";
                    String driver = "com.mysql.jdbc.Driver";
                    String userName = "root";
                    String password = "rutuja8079";

                    Class.forName(driver).newInstance();
                    Connection conn = DriverManager.getConnection(url, userName, password);

                    PreparedStatement pst1 = conn.prepareStatement("update artist set g_id=? where a_id=?");
                    pst1.setString(1, "some_gallery_id");
                    pst1.setString(2, "some_artist_id");

                    pst1.executeUpdate();

                    pst1.close();
                    conn.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        btnJoin.setBounds(156, 115, 117, 25);
        contentPane.add(btnJoin);
    }
}