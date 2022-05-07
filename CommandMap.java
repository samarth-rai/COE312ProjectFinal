//TODO-this command does not work yet.

public class CommandMap implements Command {
    Player p;


    CommandMap(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("eat ");
       try{
        p.Map();
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