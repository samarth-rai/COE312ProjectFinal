public class CommandTakeItem implements Command {
    Player p;


    CommandTakeItem(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        switch(x){
            case "fox":
             //p.takeItem(x);
             break;

            default:
            UI.print("Unknown command");
        }
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}