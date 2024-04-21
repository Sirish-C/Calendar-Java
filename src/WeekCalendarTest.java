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

        JButton addEvent = new JButton("Add Event");
        CalendarEvent evnt = new CalendarEvent(LocalDate.of(2024, 04, 17), LocalTime.of(14, 0), LocalTime.of(14, 20), "Test 11/11 14:00-14:20","Sirish", new ArrayList<>(),Color.green);
        addEvent.addActionListener(e -> cal.addEvent(evnt));

        JPanel weekControls = new JPanel();
        weekControls.add(prevWeekBtn);
        weekControls.add(goToTodayBtn);
        weekControls.add(nextWeekBtn);
        weekControls.add(addEvent);

        frm.add(weekControls, BorderLayout.NORTH);

        frm.add(cal, BorderLayout.CENTER);
        frm.setSize(1000, 900);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
