public class CommandBattle implements Command{

    Player p;

    public CommandBattle(Player pl)
    {
        p = pl;
    }


    @Override
    public void execute(String input) {
        String[] inputs = input.split("battle ");
        try{
            p.battle(inputs[1]);
           }
           catch(IndexOutOfBoundsException e)
           {
               UI.print("Invalid command syntax!");
           }
        }
        

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }




}