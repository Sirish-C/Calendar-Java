package calendarApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class WeekCalendarTest {

    private void addToPanel(JPanel panel, GridBagConstraints constraints, Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        panel.add(component, constraints);
    }
    public static void main(String[] args) {
        JFrame frm = new JFrame();


        ArrayList<User> userEvents = new ArrayList<>();

        ImageIcon icon = new ImageIcon("../assets/calendar.png");
        frm.setTitle("Calendar");
        frm.setIconImage(icon.getImage());


        WeekCalendar cal = new WeekCalendar(userEvents);


        for(String name : cal.names){
            userEvents.add(new User(name));
        }
        userEvents.get(0).events.add(new CalendarEvent( "Practice Coding",LocalDate.of(2024,04,23),LocalTime.of(14,0),LocalTime.of(14,20),"The quick brown fox jumps over the lazy dog. Lorem ipsum dolor sit amet, consectetur adipiscing elit. The sun sets in the west, painting the sky with vibrant colors. Etiam euismod tortor id dolor suscipit, eget placerat leo pretium. The waves crashed against the shore, creating a soothing melody","Sirish", new ArrayList<>(Arrays.asList("Sirish", "Jaswanth", "Irfana")),cal.colors[0]));
        userEvents.get(1).events.add(new CalendarEvent( "Gaming Project", LocalDate.of(2024,04,24),LocalTime.of(10,0),LocalTime.of(12,0),"Implementing a new game project.","Jaswanth", new ArrayList<>(),cal.colors[1]));
        userEvents.get(2).events.add(new CalendarEvent( "Database Designing", LocalDate.of(2024,04,25),LocalTime.of(14,0),LocalTime.of(15,0),"Designing database for a project.","Irfana", new ArrayList<>(),cal.colors[2]));

        cal.addCalendarEventClickListener(e -> {
            System.out.println(e.getCalendarEvent());
            // Customizing the appearance of the message panel

            StringBuilder sb = new StringBuilder(e.getCalendarEvent().getText());

            int i = 0;
            int wrapLength = 55 ;
            while (i + wrapLength < sb.length() && (i = sb.lastIndexOf(" ", i + wrapLength)) != -1) {
                sb.replace(i, i + 1, "\n");
            }

            String eventDetails =
                    "Event Details: "+e.getCalendarEvent().getEventTitle() +"\n" +
                            "-------------------------------------------\n" +
                            e.getCalendarEvent().getStart() + " - " + e.getCalendarEvent().getEnd() + "\n" +
                            "Description: \n" + sb + "\n\n" +
                            "Host: " + e.getCalendarEvent().getHost() + "\n";

                            if(!e.getCalendarEvent().getParticipants().isEmpty()){
                                eventDetails=eventDetails+"Participants: " + String.join(", ", e.getCalendarEvent().getParticipants());
                            }


            JOptionPane.showMessageDialog(null, eventDetails, e.getCalendarEvent().getEventTitle(), JOptionPane.INFORMATION_MESSAGE);
//_MESSAGE);
        });
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

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> {
            // Create the dialog
            JDialog dialog = new JDialog(frm, "Add Event", true);
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 columns, 10px horizontal and vertical gaps

            // Create and add form components to the panel
            JLabel eventTitle = new JLabel("Event Title :");
            JTextField eventTitleField = new JTextField();

            JLabel dateLabel = new JLabel("Date:");
            JTextField dateField = new JTextField();
            dateField.setText(LocalDate.now().toString());

            JLabel startTimeLabel = new JLabel("Start Time:");
            JTextField startTimeField = new JTextField();
            JLabel endTimeLabel = new JLabel("End Time:");
            JTextField endTimeField = new JTextField();

            JLabel descriptionLabel = new JLabel("Description:");
            JTextArea descriptionArea = new JTextArea();
            descriptionArea.setRows(4);
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);
            JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

            JLabel hostLabel = new JLabel("Host:");
            JLabel participantsLabel = new JLabel("Participants:");

            // Assuming cal.names is a String array containing participant names
            JCheckBox[] participantCheckBoxes = new JCheckBox[cal.names.length];
            JPanel participantsPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // Single column for participant checkboxes
            for (int i = 0; i < cal.names.length; i++) {
                if(cal.currentUser !=i) {
                    participantCheckBoxes[i] = new JCheckBox(cal.names[i]);
                    participantsPanel.add(participantCheckBoxes[i]);
                }
            }

            // Add padding to the panel
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Add components to the panel

            panel.add(eventTitle);
            panel.add(eventTitleField);

            panel.add(dateLabel);
            panel.add(dateField);

            panel.add(startTimeLabel);
            panel.add(startTimeField);

            panel.add(endTimeLabel);
            panel.add(endTimeField);

            panel.add(descriptionLabel);
            panel.add(descriptionScrollPane);

            panel.add(hostLabel);
            panel.add(new JLabel(cal.names[cal.currentUser])); // Placeholder for host label, as it's a static text

            panel.add(participantsLabel);
            panel.add(participantsPanel);

            // Create and add submit button
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(submitEvent -> {
                // Extract data from the fields
                String title = eventTitleField.getText();
                String date = dateField.getText();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String description = descriptionArea.getText();

                // Display the extracted data (for demonstration purposes)
                System.out.println("Date: " + date);
                System.out.println("Start Time: " + startTime);
                System.out.println("End Time: " + endTime);
                System.out.println("Description: " + description);
                System.out.println("Participants:");
                ArrayList<String> UserList = new ArrayList<>();
                ArrayList<Integer> users = new ArrayList<>();
                users.add(cal.currentUser);
                for(int i =0;i<participantCheckBoxes.length;i++){
                    if(i!= cal.currentUser && participantCheckBoxes[i].isSelected()){
                        System.out.println("- " + participantCheckBoxes[i].getText());
                        UserList.add( participantCheckBoxes[i].getText());
                        users.add(i);

                    }
                }
                cal.addEvent(new CalendarEvent(title,
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
                        cal.names[cal.currentUser],
                        UserList,
                        cal.colors[cal.currentUser]
                ), users);


                // Close the dialog after submission
                dialog.dispose();
            });
            panel.add(submitButton);

            // Add the panel to the dialog content pane
            dialog.getContentPane().add(panel);
            dialog.setSize(500, 400); // Set dialog size
            dialog.setVisible(true); // Make the dialog visible
        });



        JComboBox<String>  userComboBox = new JComboBox<>(cal.names);

        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cal.setCurrentUser(userComboBox.getSelectedIndex());
            }
        });


        JPanel weekControls = new JPanel();
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(addButton);

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
