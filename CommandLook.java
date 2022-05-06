public class CommandLook implements Command {
    Player p;

    CommandLook(Player pl) {
        this.p = pl;
    }

    public void execute(String x) {
        p.look(p.currentLocation);
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}