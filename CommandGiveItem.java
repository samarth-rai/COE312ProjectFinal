public class CommandGiveItem implements Command {
    Player p;


    CommandGiveItem(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        p.giveItem(x);
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}