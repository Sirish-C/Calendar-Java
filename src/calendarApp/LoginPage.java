package calendarApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class LoginPage extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    ArrayList<User> userEvents;
    WeekCalendar cal ;
    public LoginPage() {
        this.userEvents = initializeData();
        this.cal = new WeekCalendar(userEvents);

        setTitle("Calendar Login ");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 30, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 80, 25);
        loginButton.addActionListener(this);
        add(loginButton);


        int screenWidth = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int frameWidth = getSize().width;
        int frameHeight = getSize().height;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        // Set the location of the fra  me to the center of the screen
        setLocation(x, y);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if(username.equals(Config.admin)){
            if (password.equals("password")) {
                WeekCalendarTest.getCalendar(username, cal, userEvents);

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            for (String name : Config.names) {
                if (name.equals(username)) {
                    if (password.equals("password")) {
                        WeekCalendarTest.getCalendar(username, cal, userEvents);

                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        // Dummy check for username and passwor
    }

    public static ArrayList<User> initializeData(){
        String path = "./datafiles/data.csv";
        ArrayList<User> data = new ArrayList<>();
        ArrayList<User> userEvents = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(String name : Config.names){
                userEvents.add(new User(name));
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowData = line.split(",");
                int userID = Integer.parseInt(rowData[1]);
                LocalDate date = LocalDate.of(
                        Integer.parseInt(rowData[2].split("-")[0]),
                        Integer.parseInt(rowData[2].split("-")[1]),
                        Integer.parseInt(rowData[2].split("-")[2])
                );
                LocalTime startTime = LocalTime.of(
                        Integer.parseInt(rowData[3].split(":")[0]),
                        Integer.parseInt(rowData[3].split(":")[1])
                );

                LocalTime endTime = LocalTime.of(
                        Integer.parseInt(rowData[4].split(":")[0]),
                        Integer.parseInt(rowData[4].split(":")[1])
                );
                String title = rowData[5];
                String text = rowData[6];
                int hostID = Integer.parseInt(rowData[7]);
                ArrayList<String> participants = new ArrayList<>();
                if(rowData.length>8 && !rowData[8].isEmpty()){
                    String[] names = rowData[8].split(":");
                    participants.addAll(Arrays.asList(names));
                }


                userEvents.get(userID).events.add(
                        new CalendarEvent(
                                title,
                                date,
                                startTime,
                                endTime,
                                text,
                                Config.names[hostID],
                                participants,
                                Config.colors[hostID]));
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return userEvents;
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
