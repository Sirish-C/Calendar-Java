import javax.swing.*;

public class UserPage extends JFrame {
    public UserPage(String username) {
        setTitle("Welcome, " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
