
//TODO - ingame time to real time conversino
//TODO - add in-game minutes and hours (inGameMin, inGameHours) 
//IMPORTANT - PLEASE USE ABOVE MENTIONED VARIABLE NAMES AS THEY ARE USED ELSEWHERE IN THE GAME

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock extends AbstractObserverSubject implements Runnable, Context{
GregorianCalendar time = new GregorianCalendar(2022, 5, 5, 6, 00); // starting the game on 5 may 2022, at 6am
int minCount=0;
Integer dayCount=0;
Integer inGameHours=0;
Integer inGameMinutes=0;

private static Clock instance;


//19-06 - night
//06-12 - morning
//12-16 - noon
//16-19 - evening
private Clock()
{
    Thread t = new Thread(this);
    t.start();
}

public static synchronized Clock getInstance(){
    if(instance == null){
        instance = new Clock();
    }
    return instance;
}

public String returnTime(){
    String[] splitted= time.getTime().toString().split(" ");
    String x = splitted[3];
    return x;
}
State state = new MorningState();

@Override
public void run()
{
   while (true){
    try {
        Thread.sleep(60000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        if(dayCount==0 && minCount<7){ 
            minCount++;
            time.add(Calendar.HOUR, 3);
            time.add(time.MINUTE, 25); //roll function increments the time by 1
            time.add(time.SECOND, 42); 
            if(minCount==7){
                minCount=0;
                dayCount++;
            } 
        }

        if(dayCount>0 && minCount<5){
            minCount++;
            time.add(time.MINUTE, 288);
            if(minCount==5) {
                minCount=0;
                dayCount++;
            }
        }
        if(time.HOUR>6 || time.HOUR<=12){
            setState(new MorningState());
        }
        if(time.HOUR>12 || time.HOUR<=16){
            setState(new AfternoonState());
        }
        if(time.HOUR>16 || time.HOUR<=19){
            setState(new EveningState());
        }
        if(time.HOUR>19 || time.HOUR<=6){
            setState(new NightState());
        } 
        publishMessage(new Message(this, "time",time.getTime().toString() )); //sample message: "Sun Jun 05 16:01:00 GST 2022"
   } 
}


@Override
public void update(Message m) {
    if(m.topic=="sleep")
    {
        this.nextState();
        printStatus();
        if(state.getClass().getSimpleName().equals("MorningState")){
            time.set(0, 0, 0, 6, 0);
        }
        if(state.getClass().getSimpleName().equals("AfternoonState")){
            time.set(0, 0, 0, 12, 0);
        }
        if(state.getClass().getSimpleName().equals("EveningState")){
            time.set(0, 0, 0, 16, 0);
        }
        if(state.getClass().getSimpleName().equals("NightState")){
            time.set(0, 0, 0, 19, 0);
        }

    }
    
}


@Override
public void previousState() {
    state.prev(this);
}


@Override
public void nextState() {
    // TODO Auto-generated method stub
    state.next(this);
    
}


@Override
public void printStatus() {
    // TODO Auto-generated method stub
    state.printStatus(this);
}


@Override
public void setState(State state) {
    
    this.state = state;
}


}