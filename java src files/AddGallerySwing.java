import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddGallerySwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            new AddGalleryForm().setVisible(true);
        });
    }
}

class AddGalleryForm extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField nameField;
    private JTextField locField;
    private JTextField cityField;
    private JTextField urlField;
    private JButton addButton;

    public AddGalleryForm() {
        setTitle("Add Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Landmark:"));
        locField = new JTextField();
        add(locField);

        add(new JLabel("City:"));
        cityField = new JTextField();
        add(cityField);

        add(new JLabel("URL:"));
        urlField = new JTextField();
        add(urlField);

        addButton = new JButton("Add Gallery");
        addButton.addActionListener(this);
        add(addButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String id = idField.getText();
            String name = nameField.getText();
            String loc = locField.getText();
            String city = cityField.getText();
            String url = urlField.getText();

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/art_gallery", "root", "rutuja8079")) {
                PreparedStatement pst = conn.prepareStatement("insert into gallery (g_id, g_name, landmark, city, url) values(?,?,?,?,?)");
                pst.setString(1, id);
                pst.setString(2, name);
                pst.setString(3, loc);
                pst.setString(4, city);
                pst.setString(5, url);

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Gallery added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add gallery.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}