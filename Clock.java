
//TODO - ingame time to real time conversino
//TODO - add in-game minutes and hours (inGameMin, inGameHours) 
//IMPORTANT - PLEASE USE ABOVE MENTIONED VARIABLE NAMES AS THEY ARE USED ELSEWHERE IN THE GAME

import java.util.GregorianCalendar;

public class Clock extends AbstractObserverSubject implements Runnable, Context{
GregorianCalendar time = new GregorianCalendar(2022, 5, 5, 16, 00); // starting the game on 5 may 2022, at 4pm
int minCount=0;
Integer dayCount=0;
Integer inGameHours=0;
Integer inGameMinutes=0;
public Clock()
{
    Thread t = new Thread(this);
    t.start();
}

State state = new MorningState();

@Override
public void run()
{
   while (true){
      
       if(dayCount==0 && minCount==6){ //for day 0: it is 7 mins long
            minCount=0; //reset min counter
            dayCount++;
            publishMessage(new Message(this, "day",dayCount.toString()));
             //1 earth min = 3 hours 25 min and 42sec on day 0
            time.add(time.HOUR, 3);
            time.add(time.MINUTE, 25); //roll function increments the time by 1
            time.add(time.SECOND, 42);    
       }
       if(dayCount>0 && minCount==4){ //for the rest of the days: 5mins per day 
           minCount=0;
           dayCount++;
           publishMessage(new Message(this, "day",dayCount.toString())); //publishes the day count after a new day aka if we start at 4pm, then it will say new day 24 hours later
           // after day 0: 1 earth min = 288 game min
            time.add(time.MINUTE, 288);
       }
        publishMessage(new Message(this, "time",time.getTime().toString() )); //sample message: "Sun Jun 05 16:01:00 GST 2022"
       try {
        Thread.sleep(60000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    minCount++;
   } 
}


@Override
public void update(Message m) {

    if(m.topic=="sleep")
    {
        this.nextState();
        if(this.state.getClass().getSimpleName().equals("MorningState"))
        {
            
        }
    }
    
}


@Override
public void previousState() {
    
}


@Override
public void nextState() {
    // TODO Auto-generated method stub
    
}


@Override
public void printStatus() {
    // TODO Auto-generated method stub
    
}


@Override
public void setState(State state) {
    // TODO Auto-generated method stub
    
}


}