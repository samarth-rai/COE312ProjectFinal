public class CommandHelp implements Command {
    Player p;


    CommandHelp(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        p.help();
    }
    public void undo() {
       
    }
}