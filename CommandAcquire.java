public class CommandAcquire implements Command {
    Player p;

    CommandAcquire(Player pl) {
        this.p = pl;
    }

    public void execute(String input) {

        String[] inputs = input.split("collect ");
        try {
            p.acquire(inputs[1]);
        } catch (IndexOutOfBoundsException e) {
            UI.print("Invalid command syntax!");
        }
    }

    public void undo() {
        UI.print("This cannot be undone");
    }
}