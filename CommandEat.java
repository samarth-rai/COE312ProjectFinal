public class CommandEat implements Command {
    Player p;


    CommandEat(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("eat ");
       try{
        p.eat(inputs[1]);
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