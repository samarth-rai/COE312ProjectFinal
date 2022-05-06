public interface Command
{
    void execute(String s);
    void undo();

}