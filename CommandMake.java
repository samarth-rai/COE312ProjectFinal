public class CommandMake implements Command{
    Player p;
    
    CommandMake(Player pl) {
        this.p = pl;
    }
    @Override
    public void execute(String input) {
        String[] inputs= input.split(" ");
        switch(inputs[1]){
            case "house": 
                try{p.buildHouse();}
                catch(ArrayIndexOutOfBoundsException e)
                {
                    UI.print("Invalid command");
                }
                break;
            case "fire":
                try{p.makeFire();}
                catch(ArrayIndexOutOfBoundsException e)
                {
                    UI.print("Invalid command");
                }
                break;

        }
       
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
}
