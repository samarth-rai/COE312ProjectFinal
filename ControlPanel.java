public class ControlPanel {
    Command [] slots;
    Integer prevIndex = -1;
    public ControlPanel(Command [] slots) {
        this.slots = slots;
    }
    public void buttonWasPressed(int index, String c){
        slots[index].execute(c);

    }
    public void undo(){
        if(prevIndex == -1){
            UI.print("No commands executed!");
            return;
        }
        slots[prevIndex].undo();
    } 
}
    