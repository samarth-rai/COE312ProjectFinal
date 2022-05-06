public class CommandAcquire implements Command {
    Player p;


    CommandAcquire(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        p.inspect(x);
        
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}