public class CommandHealth implements Command {
    Player p;

    CommandHealth(Player pl) {
        this.p = pl;
    }

    public void execute(String x) {
        p.viewHealth();
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}