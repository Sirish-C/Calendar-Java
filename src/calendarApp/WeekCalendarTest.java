package calendarApp;

import calendarApp.Calendar;
import calendarApp.CalendarEvent;
import calendarApp.WeekCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekCalendarTest {


    public static void main(String[] args) {
        JFrame frm = new JFrame();

        ArrayList<User> userEvents = new ArrayList<>();
        userEvents.add(new User("Sirish"));
        userEvents.add(new User("Jaswanth"));
        userEvents.add(new User("Irfana"));


        userEvents.get(0).events.add(new CalendarEvent( LocalDate.of(2024,04,17),LocalTime.of(14,0),LocalTime.of(14,20),"Testing","Sirish", new ArrayList<>()));
        userEvents.get(1).events.add(new CalendarEvent( LocalDate.of(2024,04,16),LocalTime.of(10,0),LocalTime.of(12,0),"Testing","Sirish", new ArrayList<>()));
        userEvents.get(2).events.add(new CalendarEvent( LocalDate.of(2024,04,18),LocalTime.of(14,0),LocalTime.of(15,0),"Testing","Sirish", new ArrayList<>()));



        WeekCalendar cal = new WeekCalendar(userEvents);

        cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
        cal.addCalendarEmptyClickListener(e -> {
            System.out.println(e.getDateTime());
            System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
        });

        JButton goToTodayBtn = new JButton("Today");
        goToTodayBtn.addActionListener(e -> cal.goToToday());

        JButton nextWeekBtn = new JButton(">");
        nextWeekBtn.addActionListener(e -> cal.nextWeek());

        JButton prevWeekBtn = new JButton("<");
        prevWeekBtn.addActionListener(e -> cal.prevWeek());


        JButton button = new JButton("Add Event");
        button.addActionListener(e -> {
            // Create the dialog
            JDialog dialog = new JDialog(frm, "Event Details", true);
            dialog.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, 10px horizontal and vertical gaps


//            // Create a panel with FlowLayout to add padding
//            JPanel contentPanel = new JPanel();
//            contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

            // Create and add form components to the dialog
            JLabel dateLabel = new JLabel("Date:");
            JTextField dateField = new JTextField();
            dateField.setText(LocalDate.now().toString());

            JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
            JTextField startTimeField = new JTextField();

            JLabel endTimeLabel = new JLabel("End Time (HH:MM):");
            JTextField endTimeField = new JTextField();

            JLabel descriptionLabel = new JLabel("Description:");
            JTextField descriptionField = new JTextField();

            JLabel hostLabel = new JLabel("Host Name:");
            JTextField hostField = new JTextField();

            JLabel usersLabel = new JLabel("Users (Comma-separated):");
            JTextField usersField = new JTextField();

            JLabel colorCodeLabel = new JLabel("Color Code ");
            JTextField colorCodeField = new JTextField();


            // Add components to the dialog
            dialog.add(dateLabel);
            dialog.add(dateField);

            dialog.add(startTimeLabel);
            dialog.add(startTimeField);

            dialog.add(endTimeLabel);
            dialog.add(endTimeField);

            dialog.add(descriptionLabel);
            dialog.add(descriptionField);

            dialog.add(hostLabel);
            dialog.add(hostField);

            dialog.add(usersLabel);
            dialog.add(usersField);

            dialog.add(colorCodeLabel);
            dialog.add(colorCodeField);

            // Create and add submit button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(submitEvent -> {
                // Extract data from the fields
                String date = dateField.getText();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String description = descriptionField.getText();
                String host = hostField.getText();
                String[] users = usersField.getText().split(","); // Split comma-separated names into an array

                String colorCode = colorCodeField.getText();

                // Display the extracted data (for demonstration purposes)
                System.out.println("Date: " + date);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Description: " + description);
                System.out.println("Host: " + host);
                System.out.println("Users:");
                Color color = Color.CYAN ;
                if (colorCode.equals("green"))
                    color = Color.green;


                ArrayList<String> usersList = new ArrayList<>();
                for (String user : users) {
                    System.out.println("- " + user.trim());
                    usersList.add(user.trim());
                }


                cal.addEvent(new CalendarEvent(
                        LocalDate.of(
                                Integer.parseInt(date.split("-")[0]),
                                Integer.parseInt(date.split("-")[1]),
                                Integer.parseInt(date.split("-")[2])),
                        LocalTime.of(
                                Integer.parseInt(startTime.split(":")[0]),
                                Integer.parseInt(startTime.split(":")[1])),
                        LocalTime.of(
                                Integer.parseInt(endTime.split(":")[0]),
                                Integer.parseInt(endTime.split(":")[1])),
                        description,
                        host,
                        usersList, color
                ));
                // Close the dialog after submission
                dialog.dispose();
            });
            dialog.add(submitButton);
//            dialog.setContentPane(contentPanel);
            // Set dialog size and make it visible
            dialog.setSize(500, 500);
            dialog.setVisible(true);
        });

        String[] users ={"sirish" , "jaswanth" , "irfana"};
        JComboBox<String>  userComboBox = new JComboBox<>(users);

        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = (String) userComboBox.getSelectedItem();

                switch (selectedUser){
                    case "sirish": cal.setCurrentUser(0);
                           break;
                    case "jaswanth": cal.setCurrentUser(1);
                        break;
                    case "irfana": cal.setCurrentUser(2);
                        break;
                }
            }
        });


        JPanel weekControls = new JPanel();
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(button);

        JPanel userControls = new JPanel();
        userControls.add(userComboBox);

        frm.add(weekControls, BorderLayout.NORTH);
        frm.add(userControls, BorderLayout.SOUTH);
        frm.add(cal, BorderLayout.CENTER);
        frm.setSize(1000, 900);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
