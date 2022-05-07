public class CommandTime implements Command{
    Player p;


    CommandTime(Player pl) {
        this.p = pl;
    }
    public void execute(String input) {
      // try{
        p.performViewTime();
      // }
      // catch(IndexOutOfBoundsException e)
      // {
       //    UI.print("Invalid command syntax!");
      // }
    }
    public void undo() {
       UI.print("This command cannot be undone!");
    }
}
