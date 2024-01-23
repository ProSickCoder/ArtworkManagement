import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddExhibition extends JFrame implements ActionListener {

    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField typeTextField;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JTextField galleryNameTextField;
    private JButton submitButton;

    public AddExhibition() {
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        idTextField = new JTextField(10);
        add(idTextField);

        add(new JLabel("Name:"));
        nameTextField = new JTextField(10);
        add(nameTextField);

        add(new JLabel("Type:"));
        typeTextField = new JTextField(10);
        add(typeTextField);

        add(new JLabel("Start Date:"));
        startDateTextField = new JTextField(10);
        add(startDateTextField);

        add(new JLabel("End Date:"));
        endDateTextField = new JTextField(10);
        add(endDateTextField);

        add(new JLabel("Gallery Name:"));
        galleryNameTextField = new JTextField(10);
        add(galleryNameTextField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddExhibition();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            String type = typeTextField.getText();
            String startDate = startDateTextField.getText();
            String endDate = endDateTextField.getText();
            String galleryName = galleryNameTextField.getText();

            // Call the database logic here.
            // You may want to use a separate class or method for database interaction.
        }
    }
}