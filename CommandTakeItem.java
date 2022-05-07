public class CommandTakeItem implements Command {
    Player p;


    CommandTakeItem(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        p.takeItem(x);
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}