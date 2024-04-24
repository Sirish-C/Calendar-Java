package calendarApp;

import java.util.ArrayList;

public class User {
    public String name ;
    public String password;
    public ArrayList<CalendarEvent> events;

    User(String name){
        this.name = name;
        events = new ArrayList<>();

    }

    public String getName(){return this.name;}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }


    public ArrayList<CalendarEvent> getEvents() {
        return this.events;
    }

    public void addEvent(CalendarEvent event ){
        this.events.add(event);
    }

}
