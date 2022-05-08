public class CommandInteract implements Command{


    Player p;

    CommandInteract(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
        String[] inputs= input.split(" ");
        try{
            p.interact(inputs[1]);
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