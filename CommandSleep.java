
public class CommandSleep implements Command {
    Player p;


    CommandSleep(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
       try{
        p.sleep();
       }
       catch(IndexOutOfBoundsException e)
       {
           UI.print("Invalid command syntax!");
       }
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}