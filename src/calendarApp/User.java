package calendarApp;// Testing ...

import java.util.ArrayList;

public class User {
    public String name ;
    public ArrayList<CalendarEvent> events;

    User(String name){
        this.name = name;
        events = new ArrayList<>();

    }

    public String getName(){return this.name;}


    public ArrayList<CalendarEvent> getEvents() {
        return this.events;
    }

    public void addEvent(CalendarEvent event ){
        this.events.add(event);
    }

}
