public class House extends Craftable
{
    
    void Completed() {
        // TODO Auto-generated method stub
        UI.print("House is built!");
    }

    @Override
    void placeMaterials() {
        UI.print("Wood is placed");
        UI.print("Leaves are placed");
        
    }

    @Override
    void performAction() {
        UI.print("Tying the wood together with leaves.");
    }

    @Override
    public void update(Message m) {
       
        
    }
}