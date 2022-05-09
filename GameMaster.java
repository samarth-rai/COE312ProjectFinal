import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Scanner;

//Controls the gameplay behavior of the class
//Runnable as this thread will control the game progression
public class GameMaster extends AbstractObserverSubject implements Runnable {
    
    private static GameMaster instance;
    static String IP = "";
    static Integer Port = 0;
    static TCP_Client t;
    private GameMaster(String config, String log) {
        Thread th = new Thread(this);
        th.start();
    }

    public synchronized static GameMaster getInstance(String config, String log) {
        if (instance == null) {
            instance = new GameMaster(config, log);
        }
        return instance;
    }
    FileInputStream configFile;
    FileInputStream logFile;

    public String introduction = "In 1995, Chuck Noland, a systems analyst executive," +
            "travels the world resolving productivity problems at FedEx depots. " +
            "He lives with his girlfriend Kelly Frears in Memphis, Tennessee," +
            "but his workaholism interferes with their relationship. " +
            "During a family Christmas dinner," +
            "Chuck is summoned to resolve a work problem in Malaysia. " +
            "The FedEx cargo plane he is on gets caught in a storm and" +
            "crashes into the Pacific Ocean. Chuck is the only survivor and" +
            "escapes with an inflatable life raft. The next day+," +
            "he washes up on an uninhabited island.\n\nNow you, Chuck Noland, must survive."+
            "\nThe island you are in have a few inhabitants. They are the people from the Oonga Boonga Tribe." +
            "These tribals sleep all the time and cannot be interacted with."+ 
            "However, it is said that there are some moments they wake up.. "+
            "but no one has survived long enough to know what happens next.."+
            "\nSome other things you can find on the island are wild wolves.. stay cautious, they may attack..."+
            "\n\nType '?' for help";
            
    
   
    // Creating all locations
    static IslandNorth islandNorth = new IslandNorth("Island North", "The north of the island");
    static IslandSouth islandSouth = new IslandSouth("Island South", "The south of the island");
    static IslandEast islandEast = new IslandEast("Island East", "The east of the island");
    static IslandWest islandWest = new IslandWest("Island West", "The west of the island");
    
    static Location[] locationList = { islandNorth,islandSouth,islandEast,islandWest };

    //Mandatory Instances
    Character[] characterList;
    Objectives objectives = new Objectives();
    static Clock clock = Clock.getInstance();
    Map map = new Map();
    
    //Variables that store gamedata.
    ArrayList<Boolean> VisitedLocations = new ArrayList<Boolean>();
   
    //Auxilary Variables
    static Subject[] stdSubjects = { clock }; // standard subjects that all objects and characters observe
    static Player player = Player.getInstance(stdSubjects,"Chuck Noland",islandNorth,100,3);
    
    //making Vikram Kumar and his items
    Undead FedExPilot = new Undead("Vikram Kumar",islandNorth,0,4,"The corpse of the pilot flying the plane. He looks dead.. but not really.. His body is actively turning into what a zombie would look like. You must battle him before he turns into a zombie and kills you!");
    Objects idCard = new Objects(stdSubjects, "IDCard",
            "A pilot license that belongs to Vikram Kumar.");
    Objects wallet = new Objects(stdSubjects, "Wallet",
            "A wallet that belongs to vikram kumar, unfortunately the money in it is useless here.");
    Objects watch = new Objects(stdSubjects, "Watch",
            "A geo-tracking timepiece that changes time according to location. It must be shockproof, hence surviving the crash. Gives you the ability to tell the exact time.");
    Objects goldBracelet = new Objects(stdSubjects, "Bracelet",
            "An expensive gold bracelet, may or may not come in handy later");

    //Creating Tribals
    //commented because creating their instance is 
    Tribals tribal1 = new Tribals("Mr. Oonga Tribal",islandNorth, 100, 3, "A tribal habitant of the island. Mr. Oonga is the leader of the Oonga Boonga tribe which rules the island.", "Oonga Boongas");
    Tribals tribal2 = new Tribals("Loola Tribal",islandNorth, 100, 3, "A tribal habitant of the island","Oonga Boongas");
    Tribals tribal3 = new Tribals("Pooma Tribal",islandSouth, 100, 3,"A tribal habitant of the island", "Oonga Boongas");
    Tribals tribal4 = new Tribals("Qxkdjfh Tribal",islandSouth, 100, 3, "A tribal habitant of the island","Oonga Boongas");
    Tribals tribal5 = new Tribals("Csdjfh",islandEast, 100, 3, "A tribal habitant of the island","Oonga Boongas");
    Tribals tribal6 = new Tribals("Lsjdhfg",islandEast, 100, 3, "A tribal habitant of the island","Oonga Boongas");
    Tribals tribal7 = new Tribals("Pskh",islandWest, 100, 3, "A tribal habitant of the island","Oonga Boongas");
    
    

    //Objects to be placed in locations
     Objects axe = new Objects("Axe", "An emergency glass breaking axe from the plane's debris. A good weapon to have."); 
     Objects stones = new Objects("Stones", "A Flint Stone. Looks like it can be used to start a fire...");
     Food apple1 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandNorth);
     Food apple2 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandNorth);
     Food apple3 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandSouth);
     Food apple4 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandSouth);
     Food apple5 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandEast);
     Food apple6 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandEast);
     Food apple7 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandWest);
     Food apple8 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandWest);
     Food apple9 = new Food(player, "Apple", "An apple. Gives 10 HP upon eating.", 10, islandWest);

    //making FedEx Packages

    Consumables box1 = new Consumables("Box","An unopened FedEx package, try to open to see what is inside", islandSouth, false);
    Consumables box2 = new Consumables("Box","An unopened FedEx package, try to open to see what is inside", islandWest, false);
    Consumables box3 = new Consumables("Box","An unopened FedEx package, try to open to see what is inside", islandWest, false);
    Objects wilson = new Objects("Wilson", "A tennis ball made by Wilson Sporting Goods Company. Found inside a washed up FedEx Package.");
    Objects lvbag = new Objects("Bag", "A Louis Vitton Bag with space to hold many items. Found inside a washed up FedEx Package.");
    Objects SwissKnife = new Objects("Knife", "A Swiss Army Knife, can be used as a weapon. Found inside a washed up FedEx Package.");
    Consumables Tree1 = new Consumables("Tree", "A beautiful palm tree. Can be cut to gather resources.", islandNorth, false);
    Objects Wood1 = new Objects("Wood", "A piece of palm wood");
    Objects Leaves1 = new Objects("Leaves", "Leaves of palm tree");
    Consumables Tree2 = new Consumables("Tree", "A beautiful palm tree. Can be cut to gather resources.", islandNorth, false);
    Objects Wood2 = new Objects("Wood", "A piece of palm wood");
    Objects Leaves2 = new Objects("Leaves", "Leaves of palm tree");

    Consumables Tree3 = new Consumables("Tree", "A beautiful oak tree. Can be cut to gather resources.", islandSouth, false);
    Objects Wood3 = new Objects("Wood", "A piece of oak wood");
    Objects Leaves3 = new Objects("Leaves", "Leaves of oak tree");
    Consumables Tree4 = new Consumables("Tree", "A beautiful oak tree. Can be cut to gather resources.", islandSouth, false);
    Objects Wood4 = new Objects("Wood", "A piece of oak wood");
    Objects Leaves4 = new Objects("Leaves", "Leaves of oak tree");

    Consumables Tree5 = new Consumables("Tree", "A beautiful birch tree. Can be cut to gather resources.", islandWest, false);
    Objects Wood5 = new Objects("Wood", "A piece of birch wood");
    Objects Leaves5 = new Objects("Leaves", "Leaves of birch tree");
    Consumables Tree6 = new Consumables("Tree", "A beautiful birch tree. Can be cut to gather resources.", islandWest, false);
    Objects Wood6 = new Objects("Wood", "A piece of birch wood");
    Objects Leaves6 = new Objects("Leaves", "Leaves of birch tree");

    Consumables Tree7 = new Consumables("Tree", "A beautiful acacia tree. Can be cut to gather resources.", islandEast, false);
    Objects Wood7 = new Objects("Wood", "A piece of acacia wood");
    Objects Leaves7 = new Objects("Leaves", "Leaves of acacia tree");
    Consumables Tree8 = new Consumables("Tree", "A beautiful acacia tree. Can be cut to gather resources.", islandEast, false);
    Objects Wood8 = new Objects("Wood", "A piece of acacia wood");
    Objects Leaves8 = new Objects("Leaves", "Leaves of acacia tree");

    Boolean L1Ran =false;
    Boolean L2Ran =false;
    Boolean L3Ran =false;
      public static Location findLocation(String l) throws NoSuchObjectException {
        for (Location loc : locationList) {
            if (loc.name.equalsIgnoreCase(l))
                return loc;
        }
        throw new NoSuchObjectException(l);
        }

        

     //Objective Mission Functions

    public void launchWolf()
    {
        if (Objectives.fightWolf==false && Objectives.foundAxe == true && clock.inGameHours>= 17) {
            Message m = new Message(this, "objective", "fightWolf");
            publishMessage(m);
            Animal wolf1 = new Animal("Wolf", "white", "fur", "wild");
            
            player.currentLocation.currentlyPlacedAnimals.add(wolf1);
           
            UI.printNormal("\n"
            + "                             .d$$b\n"
            + "                           .' TO$;\\\n"
            + "                          /  : TP._;\n"
            + "                         / _.;  :Tb|\n"
            + "                        /   /   ;j$j\n"
            + "                    _.-\"       d$$$$\n"
            + "                  .' ..       d$$$$;\n"
            + "                 /  /P'      d$$$$P. |\\\n"
            + "                /   \"      .d$$$P' |\\^\"l\n"
            + "              .'           `T$P^\"\"\"\"\"  :\n"
            + "          ._.'      _.'                ;\n"
            + "       `-.-\".-'-' ._.       _.-\"    .-\"\n"
            + "     `.-\" _____  ._              .-\"\n"
            + "    -(.g$$$$$$$b.              .'\n"
            + "      \"\"^^T$$$P^)            .(:\n"
            + "        _/  -\"  /.'         /:/;\n"
            + "     ._.'-'`-'  \")/         /;/;\n"
            + "  `-.-\"..--\"\"   \" /         /  ;\n"
            + " .-\" ..--\"\"        -'          :\n"
            + " ..--\"\"--.-\"         (\\      .-(\\\n"
            + "   ..--\"\"              `-\\(\\/;`\n"
            + "     _.                      :\n"
            + "                             ;`-\n"
            + "                            :\\\n"
            + "                            ;  ");
    
            UI.print("A wolf has appeared and it seems to be approaching you to attack!");
            wolf1.attack(player);
        }
    }
   
    public void TribalAttack(){
        
        if (Objectives.fightTribals==false && clock.inGameHours >= 0 && clock.inGameHours <= 6) { //tribals attack between 12 - 6am
            UI.print("The tribals appear to be attacking you!");
            tribal1.attack(player);
            publishMessage(new Message(this, "Objectives", "fightTribals"));

            
        }
        if(Objectives.fightTribals==true && clock.inGameHours >= 0 && clock.inGameHours <= 6){
            UI.print("Since you have fought the tribals in the past, the Tribe Leader of the Oonga Boonga Tribe, Mr. Oonga has an offer of peace for you. However, the only condition is that within the next 24 hours, you much bring him a valueable item... Choose this item wisely.. If he does not like this item, the tribe will attack you!");
            publishMessage(new Message(this, "Objectives", "peaceOffered"));
        }
    }
    public void ShipArrives(){
        if(Objectives.peaceAccepted==true){

        }
    }

    public void intro() {

        clock.registerObserver(player);
        clock.registerObserver(this);
        player.registerObserver(clock);

        //register all observers that couldn't be done before due to circular dependence.
        player.registerObserver(islandEast);
        player.registerObserver(islandNorth);
        player.registerObserver(islandWest);
        player.registerObserver(islandSouth);
        this.registerObserver(objectives);
        player.registerObserver(objectives);

       //UI.print(introduction);
        islandEast.currentlyPlacedObjects.add(axe);
        islandNorth.currentlyPlacedObjects.add(stones);
        
        islandSouth.currentlyPlacedObjects.add(box1);
        islandWest.currentlyPlacedObjects.add(box2);
        islandWest.currentlyPlacedObjects.add(box3);
        box1.dropObjects.add(SwissKnife);
        box2.dropObjects.add(wilson);
        box3.dropObjects.add(lvbag);

        Tree1.dropObjects.add(Wood1);
        Tree1.dropObjects.add(Leaves1);
        islandNorth.currentlyPlacedObjects.add(Tree1);
        Tree2.dropObjects.add(Wood2);
        Tree2.dropObjects.add(Leaves2);
        islandNorth.currentlyPlacedObjects.add(Tree2);

        Tree3.dropObjects.add(Wood3);
        Tree3.dropObjects.add(Leaves3);
        islandSouth.currentlyPlacedObjects.add(Tree3);
        Tree4.dropObjects.add(Wood4);
        Tree4.dropObjects.add(Leaves4);
        islandSouth.currentlyPlacedObjects.add(Tree4);

        Tree5.dropObjects.add(Wood5);
        Tree5.dropObjects.add(Leaves5);
        islandWest.currentlyPlacedObjects.add(Tree5);
        Tree6.dropObjects.add(Wood6);
        Tree6.dropObjects.add(Leaves6);
        islandWest.currentlyPlacedObjects.add(Tree6);

        Tree7.dropObjects.add(Wood7);
        Tree7.dropObjects.add(Leaves7);
        islandEast.currentlyPlacedObjects.add(Tree7);
        Tree8.dropObjects.add(Wood8);
        Tree8.dropObjects.add(Leaves8);
        islandEast.currentlyPlacedObjects.add(Tree8);

        FedExPilot.inventory.add(idCard);
        FedExPilot.inventory.add(wallet);
        FedExPilot.inventory.add(watch);
        FedExPilot.inventory.add(goldBracelet);
        //FedExPilot.nextState();
        UI.print("To begin the game, please pair your gamepad/phone.\n");
        UI.printNormal("IP Address: "); 
        IP = UI.read();
        UI.printNormal("Port: "); 
        Port = Integer.parseInt(UI.read());
        t = new TCP_Client(IP, Port);
        L1();
    }
  
   
    public void L1() {
        UI.print("Welcome to Level 1");
        UI.print("Your objectives for this level are:\n1)Find a place to stay (or build one)\n2)Find a way to keep warm\n3)Explore the island.");
        //house built
        //fire made
        //axe found
        //discover vikram
        //once these three are complete, L2 starts

       // while(Objectives.BuiltHouse==false && Objectives.MadeFire==false && Objectives.foundAxe==false){

        //}
        L1Ran=true;
    }

    public void L2() {
       
        UI.print("Welcome to Level 2");
        UI.print("As you spend days in the wilderness, you start to feel lonely...\nYour objective for this level is: find a friend.");
       //talk to tribals
        // make tribals say that they will not attack u if u bring something valueable to them within the next day.
        //take goldbracelet and give to tribal leader Mr. Oonga
        //to befriend do tribal1.type=="friend";
        //Objectives.BefriendTribals==true && Objectives.defeatVikram == true; 
        //Objectives:
            //befriend tribal
            //defeatVikram
            //
       // L3();
       L2Ran =true;
    }


    public void L3() {
        UI.print("Welcome to Level 3.");
        UI.print("Time has come for you to find a way to escape the island. Your objective: Find a way out.");
       //rescue ship comes
       //chuck waves
       //and he is saved :))))
       L3Ran = true;
    }

    //Commands for control panel
    CommandLook cLook = new CommandLook(player); //0
    CommandInspect cInspect = new CommandInspect(player); //1
    CommandAcquire cAcquire = new CommandAcquire(player);//2
    CommandTakeItem cTakeItem = new CommandTakeItem(player);//3
    CommandGiveItem cGiveItem = new CommandGiveItem(player);//4
    CommandBattle cBattle = new CommandBattle(player);//5
    CommandInteract cInteract = new CommandInteract(player);//6
    CommandEat cEat = new CommandEat(player);//7
    CommandTravel cTravel = new CommandTravel(player);//8
    CommandInventory cInventory = new CommandInventory(player);//9
    CommandHealth cHealth = new CommandHealth(player);//10
    CommandMap cMap = new CommandMap(player);//11
    CommandSleep cSleep = new CommandSleep(player);//12
    CommandTime cTime = new CommandTime(player); //13
    CommandMake cMake = new CommandMake(player);//14
    CommandHelp cHelp = new CommandHelp(player);//15

    //Control panel and command array
    Command [] cmds = {cLook, cInspect, cAcquire, cTakeItem, cGiveItem, cBattle, cInteract, cEat,cTravel, cInventory,cHealth, cMap, cSleep, cTime, cMake, cHelp}; // add more commands as needed
    ControlPanel cp = new ControlPanel(cmds);
    @Override
    public void run() {
        intro();

        String input;
        String[] commands;
        while (true) {
            if(L1Ran==false){
                L1();
            }
            else if(L2Ran==false && Objectives.BuiltHouse==true && Objectives.MadeFire==true && Objectives.discoverVikram==true && Objectives.foundAxe==true){
                L2();
            }
            else if(L2Ran==true && Objectives.peaceAccepted==false){
                TribalAttack();
            }
            else if(L3Ran==false && Objectives.peaceAccepted==true){
                L3();
            }
            if(tribal1.inventory.contains(goldBracelet) && Objectives.peaceAccepted==false){
                publishMessage(new Message(this, "Objective", "peaceAccepted")); //message intended for Objectives class
                tribal1.type="friend"; 
            }
            if(player.inventory.contains(watch) && Objectives.foundWatch==false){
                publishMessage(new Message(this, "Objective", "foundWatch")); //message intended for Objectives class
            }
            if(player.inventory.contains(lvbag) && Objectives.foundLVBag==false){
                publishMessage(new Message(this, "Objective", "foundLVBag")); //message intended for Objectives class
            }
            launchWolf(); //Second objective to clear in the game.
            UI.printnln("command > ");
            input = UI.read();
            commands = input.split(" ");
            switch(commands[0].toLowerCase()){
                case "look":
                    cp.buttonWasPressed(0,input);
                    break;
                case "inspect":
                    cp.buttonWasPressed(1, input);
                    break;
                case "collect":
                    cp.buttonWasPressed(2, input);
                    break;
                case "take":
                    cp.buttonWasPressed(3, input);
                    break;
                case "give":
                    cp.buttonWasPressed(4, input);
                    break;
                case "battle":
                    cp.buttonWasPressed(5, input);
                    break;
                case "chop":                    
                    cp.buttonWasPressed(6, input);
                    break;
                case "open":                    
                    cp.buttonWasPressed(6, input);
                    break;
                case "eat":
                    cp.buttonWasPressed(7, input);
                    break;
                case "travel":
                    cp.buttonWasPressed(8, input);
                    break;
                case "inventory":
                    cp.buttonWasPressed(9, input);
                    break;
                case "health":
                    cp.buttonWasPressed(10, input);
                    break;
                case "map":
                    cp.buttonWasPressed(11, input);
                    break;
                case "sleep":
                    cp.buttonWasPressed(12, input);
                    break;
                case "time":
                    cp.buttonWasPressed(13, input);
                    break;
                case "make":
                    cp.buttonWasPressed(14, input);
                    break;
                case "?": //case "help":
                    cp.buttonWasPressed(15,input);
                    break;
                default:
                UI.print("Invalid command!");
            }
        }

    }

    @Override
    public void update(Message m) {
        if(m.topic.equals("day")){
            UI.print("Day "+m.payload + " begins");
            UI.print("You lost 5 HP. Eat to restore your health."); // you lose 5 health everyday
            player.health=player.health-5;
        }
        
    }
}