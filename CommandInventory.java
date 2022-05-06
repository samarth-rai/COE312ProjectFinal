public class CommandInventory implements Command{
    
        Player p;
    
        CommandInventory(Player pl) {
            this.p = pl;
        }
    
        public void execute(String x) {
            p.checkInventory();
        }
        public void undo() {
            UI.print("This cannot be undone");
        }
    }



