package calendarApp;

import java.util.ArrayList;

public class User {
    public String name ;
    public String password;
  
    public ArrayList<CalendarEvent> events;

    public User(String name) {
        this.name = name;
        this.selected = false; // Initialize selected as false by default
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
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
