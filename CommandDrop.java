public class CommandDrop implements Command {
    Player p;


    CommandDrop(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("drop ");
       try{
        //p.drop(inputs[1]);
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