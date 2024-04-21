package com.davidmoodie.SwingCalendar;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarEvent {

    private static final Color DEFAULT_COLOR = Color.PINK;

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String text;
    private String host;
    private ArrayList<String> participants ;
    private Color color;


    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, String host , ArrayList<String> participants ) {
        this(date, start, end, text,host , participants ,DEFAULT_COLOR);
    }

    public CalendarEvent(LocalDate date, LocalTime start, LocalTime end, String text, String host , ArrayList<String>participants , Color color) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this. host = host;
        this.participants = participants;
        this.color = color;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHost(){return host;}
    public void setHost(String host ){ this.host = host ;}


    public ArrayList<String> getParticipants(){return participants;}

    public void addParticipants(String name){
        this.participants.add(name);
    }
    public void removeParticipants(String name){
        this.participants.remove(name);
    }

    public String toString() {
        return getDate() + " " + getStart() + "-" + getEnd() + ". " + getText();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (!date.equals(that.date)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
