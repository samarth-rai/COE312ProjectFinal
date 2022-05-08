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
                p.buildHouse();
                break;
            case "fire":
                p.makeFire();
                break;

        }
       
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
}
