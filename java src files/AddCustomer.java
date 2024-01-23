import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2;
    JLabel l1, l2, l3, l4, l5;

    AddCustomer() {
        setLayout(null);

        l1 = new JLabel("ID");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(200, 50, 100, 30);
        add(t1);

        l2 = new JLabel("Name");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(200, 100, 100, 30);
        add(t2);

        l3 = new JLabel("Address");
        l3.setBounds(50, 150, 100, 30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(200, 150, 100, 30);
        add(t3);

        l4 = new JLabel("Phone");
        l4.setBounds(50, 200, 100, 30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(200, 200, 100, 30);
        add(t4);

        l5 = new JLabel("Title");
        l5.setBounds(50, 250, 100, 30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(200, 250, 100, 30);
        add(t5);

        b1 = new JButton("Submit");
        b1.setBounds(50, 350, 100, 30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBounds(200, 350, 100, 30);
        b2.addActionListener(this);
        add(b2);

        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/art_gallery", "root", "rutuja8079");

                String c_id = t1.getText();
                String c_name = t2.getText();
                String address = t3.getText();
                String pno = t4.getText();
                String title = t5.getText();

                String query = "select * from painting where title='" + title + "' ";

                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(query);
                rs.next();
                String imageurl = rs.getString("url");

                PreparedStatement pst = c.prepareStatement("insert into customer (c_id, c_name, address, phone) values(?,?,?,?)");
                PreparedStatement pst1 = c.prepareStatement("insert into bought_by(c_id, url, cost ) values(?, ?, ?)");
                PreparedStatement pst2 = c.prepareStatement("delete from painting where url = ? ");

                pst.setString(1, c_id);
                pst.setString(2, c_name);
                pst.setString(3, address);
                pst.setString(4, pno);

                pst1.setString(1, c_id);
                pst1.setString(2, imageurl);

                pst2.setString(1, imageurl);

                pst.executeUpdate();
                pst1.executeUpdate();
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Record has been inserted");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            new Admin();
            dispose();
        }
    }
}