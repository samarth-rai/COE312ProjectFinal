public class House extends Craftable
{
    public boolean PlayerUsingStones(){
        return false;
    }
    @Override
    void RubbingStones() {
        // TODO Auto-generated method stub
        //empty
    }
    @Override
    void Blowing() {
        // TODO Auto-generated method stub
        //empty
    }
    void Completed() {
        // TODO Auto-generated method stub
        UI.print("House is built!");
    }
}