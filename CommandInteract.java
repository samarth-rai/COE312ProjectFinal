public class CommandInteract implements Command{


    Player p;
    
    CommandInteract(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
        String[] inputs= input.split(" ");
       if(inputs[1]==null)
       {
           UI.print("The command is invalid.");
       }
       else
        p.interact(inputs[1]);
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}