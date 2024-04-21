public class Event {
    int year;
    int month;
    int day ;
    int startTimeH;
    int startTimeM;
    int endTimeH;
    int endTimeM;
    String text ;

    Event(int year, int month, int day , int startTimeH , int startTimeM , int endTimeH , int endTimeM , String text){
        this.year = year;
        this.month = month;
        this.day = day;
        this.startTimeH = startTimeH;
        this.startTimeM = startTimeM;
        this.endTimeH= endTimeH;
        this.endTimeM= endTimeM;
        this.text = text;
    }
}
