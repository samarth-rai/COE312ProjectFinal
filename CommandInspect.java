public class CommandInspect implements Command {
    Player p;


    CommandInspect(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
        String[] inputs= input.split("inspect ");
        try{
            p.inspect(inputs[1]);
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