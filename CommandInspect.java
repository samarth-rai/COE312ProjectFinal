public class CommandInspect implements Command {
    Player p;


    CommandInspect(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        switch(x){
            case "fox":
             //p.inspect(p);
             break;

            default:
            UI.print("Unknown command");
        }
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}