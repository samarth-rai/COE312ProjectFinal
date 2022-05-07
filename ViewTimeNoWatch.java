public class ViewTimeNoWatch extends ViewTimeBehavior {
    Clock c = Clock.getInstance();
    public void ViewTime(){
        c.state.printStatus(c);
    }
}
