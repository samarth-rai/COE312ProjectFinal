public class ViewTimeWatch extends ViewTimeBehavior {
    Clock c = Clock.getInstance();
    public void ViewTime(){
        UI.print(c.returnTime());
    }
}
