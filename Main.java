public class Main {
    public static void main(String[] args) throws Exception {
        UI.print("welcom to hell");
        GameMaster game = GameMaster.getInstance("./config/config.txt", "./log/log.txt");
    }
}
