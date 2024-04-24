package calendarApp;

import java.util.ArrayList;

public class User {
    private String name;
    private boolean selected; // Add a boolean field to store the selection state
    public ArrayList<CalendarEvent> events;

    public User(String name) {
        this.name = name;
        this.selected = false; // Initialize selected as false by default
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<CalendarEvent> getEvents() {
        return events;
    }

    public void addEvent(CalendarEvent event) {
        events.add(event);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
