public class CommandTravel implements Command {
    Player p;


    CommandTravel(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("to ");
        try{p.travelTo(inputs[1]);}
        catch(ArrayIndexOutOfBoundsException e)
        {
            UI.print("Invalid Command Syntax.");
        }
        catch(IndexOutOfBoundsException e)
        {
            UI.print("Invalid command syntax.");
        }
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}