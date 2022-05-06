public class CommandAcquire implements Command {
    Player p;


    CommandAcquire(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      
        String[] inputs= input.split("acquire ");
       if(inputs[1]==null)
       {
           UI.print("The command is invalid.");
       }
       else
        p.acquire(inputs[1]);
    }
  
    public void undo() {
        UI.print("This cannot be undone");
    }
}