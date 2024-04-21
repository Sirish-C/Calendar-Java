import com.davidmoodie.SwingCalendar.Calendar;
import com.davidmoodie.SwingCalendar.CalendarEvent;
import com.davidmoodie.SwingCalendar.WeekCalendar;

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

        ArrayList<CalendarEvent> events = new ArrayList<>();

        WeekCalendar cal = new WeekCalendar(events);

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

//        JButton addEvent = new JButton("Add Event");
//        CalendarEvent evnt = new CalendarEvent(LocalDate.of(2024, 04, 17), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20","Sirish", new ArrayList<>(),Color.green);
//        addEvent.addActionListener(e -> cal.addEvent(evnt));


        JButton button = new JButton("Add Event");
        button.addActionListener(e -> {
            // Create the dialog
            JDialog dialog = new JDialog(frm, "Event Details", true);
            dialog.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, 10px horizontal and vertical gaps

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

            // Set dialog size and make it visible
            dialog.setSize(500, 500);
            dialog.setVisible(true);
        });



        JPanel weekControls = new JPanel();
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(button);

        frm.add(weekControls, BorderLayout.NORTH);

        frm.add(cal, BorderLayout.CENTER);
        frm.setSize(1000, 900);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
