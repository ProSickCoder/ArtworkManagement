
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JoinGallery extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JoinGallery frame = new JoinGallery();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JoinGallery() {
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
                    String url = "jdbc:mysql://localhost:3306/Login";
                    String driver = "com.mysql.jdbc.Driver";
                    String userName = "root";
                    String password = "rutuja8079";

                    Class.forName(driver).newInstance();
                    Connection conn = DriverManager.getConnection(url, userName, password);

                    PreparedStatement pst = conn.prepareStatement("insert into artist (g_id) values(?)");
                    pst.setString(1, "123");

                    pst.executeUpdate();

                    pst.close();
                    conn.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        contentPane.add(btnJoin, BorderLayout.CENTER);
    }

}