public class CommandTravel implements Command {
    Player p;


    CommandTravel(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("to");
       if(inputs[1]==null)
       {
           UI.print("The command is invalid.");
       }
       else
        p.travelTo(inputs[1]);
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}