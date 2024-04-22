package calendarApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class EventForm extends JFrame implements ActionListener  {
    private final JTextField dateField;
    private final JTextField startTimeField;
    private final JTextField endTimeField;
    private final JTextField hostNameField;
    private final JComboBox<String> usersDropdown;
    public CalendarEvent event;

    public EventForm() {
        setTitle("New Event");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        dateField = new JTextField(10);
        startTimeField = new JTextField(10);
        endTimeField = new JTextField(10);
        hostNameField = new JTextField(20);
        usersDropdown = new JComboBox<>(new String[]{"Irfana", "Sirish ", "Sony"});

        // Create labels
        JLabel dateLabel = new JLabel("Date:");
        JLabel startTimeLabel = new JLabel("Start Time:");
        JLabel endTimeLabel = new JLabel("End Time:");
        JLabel hostNameLabel = new JLabel("Host Name:");
        JLabel usersLabel = new JLabel("Select User:");

        // Create button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Create panel for form components
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(startTimeLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(startTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(endTimeLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(endTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(hostNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(hostNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(usersLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usersDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(submitButton, gbc);

        // Add form panel to the frame
        getContentPane().add(formPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle submit button action (you can add your logic here)
        String date = dateField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String hostName = hostNameField.getText();
        String selectedUser = (String) usersDropdown.getSelectedItem();


        CalendarEvent event = new CalendarEvent(
                LocalDate.of(Integer.parseInt(date.substring(6)), Integer.parseInt(date.substring(0,2)), Integer.parseInt(date.substring(3,5))),
                LocalTime.of(Integer.parseInt(startTime.split(":")[0]),Integer.parseInt(startTime.split(":")[1])),
                LocalTime.of(Integer.parseInt(endTime.split(":")[0]),Integer.parseInt(endTime.split(":")[1])),
                "This is a test",hostName,
                new ArrayList<>()
                );
       this.event = event;

    }
    public CalendarEvent submit(){
        return this.event;
    }
}