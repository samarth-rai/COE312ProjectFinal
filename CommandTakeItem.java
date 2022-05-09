public class CommandTakeItem implements Command {
    Player p;


    CommandTakeItem(Player pl) {
        this.p = pl;
    }
    public void execute(String x) {
        try{p.takeItem(x);}
        catch(ArrayIndexOutOfBoundsException e)
                {
                    UI.print("Invalid command");
                }
    }
    public void undo() {
        UI.print("This cannot be undone");
    }
}