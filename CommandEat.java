public class CommandEat implements Command {
    Player p;


    CommandEat(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("eat");
       if(inputs[1]==null)
       {
           UI.print("The command is invalid.");
       }
       else
        p.eat(inputs[1]);
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}