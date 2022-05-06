public class CommandInspect implements Command {
    Player p;


    CommandInspect(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        
        
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}