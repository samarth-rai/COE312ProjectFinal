import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Scanner;

//Controls the gameplay behavior of the class
//Runnable as this thread will control the game progression
public class GameMaster implements Runnable {

    private static GameMaster instance;
    
    public String introduction = "In 1995, Chuck Noland, a systems analyst executive," +
            "travels the world resolving productivity problems at FedEx depots. " +
            "He lives with his girlfriend Kelly Frears in Memphis, Tennessee," +
            "but his workaholism interferes with their relationship. " +
            "During a family Christmas dinner," +
            "Chuck is summoned to resolve a work problem in Malaysia. " +
            "The FedEx cargo plane he is on gets caught in a storm and" +
            "crashes into the Pacific Ocean. Chuck is the only survivor and" +
            "escapes with an inflatable life raft. The next day+," +
            "he washes up on an uninhabited island.\n\nNow you, Chuck Noland, must survive.";
            
    FileInputStream configFile;
    FileInputStream logFile;
    // Creating all locations
    IslandNorth islandNorth = new IslandNorth();
    IslandSouth islandSouth = new IslandSouth();
    IslandEast islandEast = new IslandEast();
    IslandWest islandWest = new IslandWest();
    
    Location[] locationList = {islandNorth,islandSouth,islandEast,islandWest};
    
    Character[] characterList;
    Clock clock = new Clock();
    Map map = new Map();
    ArrayList<Boolean> VisitedLocations = new ArrayList<Boolean>();
    Subject[] stdSubjects = { clock }; // standard subjects that all objects and characters observe
    Player player = Player.getInstance(stdSubjects,"Chuck Noland",islandNorth,100,3);
    CommandLook cLook = new CommandLook(player);
    CommandInspect cInspect = new CommandInspect(player);
    CommandAcquire cAcquire = new CommandAcquire(player);
    CommandTakeItem cTakeItem = new CommandTakeItem(player);
    CommandAttack cAttack = new CommandAttack(player);

    Command [] cmds = {cLook, cInspect}; // add more commands as needed
    ControlPanel cp = new ControlPanel(cmds);

      public Location findLocation(String l) throws NoSuchObjectException {
        for (Location loc : locationList) {
            if (loc.name.equalsIgnoreCase(l))
                return loc;
        }
        throw new NoSuchObjectException(l);
    }

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

    public void intro() {
        UI.print(introduction);
        L1();
    }

    public   void L1() {
        
        UI.print("You discover next to you the corpse of pilot Vikram Kumar");

        // DONE - Notification(from location): New Location Unlocked

        // Time: Day night cycle based on time - SAAD
        // Will send messages -> player ------ done
        // Time takes it's observers as characterlist -> In other works, all characters
        // observe time. ---- done
        // Day 1 longer - Day counter, when daycount == 0 sleep(longertime) --- done

        // COME BACK TO THIS // Disable functions at night unless there is some object

      
        // Create Undead Pilot
        Undead FedExPilot = new Undead("Vikram Kumar");
        Objects idCard = new Objects(stdSubjects, "Vikram Kumar's ID Card",
                "A pilot license that belongs to vikram kumar");
        Objects wallet = new Objects(stdSubjects, "Vikram Kumar's Wallet",
                "A wallet that belongs to vikram kumar, unfortunately the money in it is useless here.");
        Objects watch = new Objects(stdSubjects, "Casio Wristwatch",
                "A geo-tracking timepiece that changes time according to location. It must be shockproof, hence surviving the crash");
        Objects goldBracelet = new Objects(stdSubjects, "Gold Bracelet",
                "An expensive gold bracelet, may or may not come in handy later");

        FedExPilot.inventory.add(idCard);
        FedExPilot.inventory.add(wallet);
        FedExPilot.inventory.add(watch);
        FedExPilot.inventory.add(goldBracelet);
        islandNorth.currentlyPlacedCharacters.add(FedExPilot);

        // SAMARTH

        // Undead has method called activate -> dead -> undead state
        // Discover and search Vikram corpse //OBJECTIVE 1 -> Triggered by search()
        // function for the corpse.
        // He will find
        // Pilot License
        // Wallet
        // Watch -> used to track time
        // VALUABLES
        // Gold bracelet

        // SAAD
        // Viewtime -> Morning, Afternoon, Evening, Night
        // Viewtime -> Exact time

        // Message format for time, updates every minute
        // (origin,"time","5:00")
        // Before finding watch (Strategy)

        // SAMARTH

        // Can watch out for tribals that attack at 0:00, if Objective(buildhouse -
        // completed)

        // Create the instances for boxes
        // Array to hold objects
        // Wilson
        // LV Bag -> increase inventory space from 2 objects to 30 (Send message to
        // gamemaster -> sends message to player to increase inventory)
        //
        //

        // Find axe while looking around ->objective

        // ROSHNI

        // If time is past 5:00 and player has an axe, create a wolf at islandEast.
        
        

        // Two classes attack types extend AttackType-> instances in animal-> DONE
        // scratch attack & pounce attack -> DONE
        // Implement attacks - Strategy to implement attacks (Sends a message to what is ->DONE
        // being attacked -> to reduce health) ->DONE

        //SAMARTH

        // Every living thing must have a state -> when killed state changes to dead
        // When state changes -> actions that can be performed change
        // Player thinks to himself, looks like this makes a good blanket, I can use
        // this to spend the night
    }

    public void L2() {
        // ROSHNI

        // Cut wood.
        // Use axe to cut wood (5x wood)
        // collect leaves(3x leaves)
        // get this from one tree

        // Create Tree

        // --Not Assigned--//
        // Crafting anything -> makes player tired, reduces health
        // COME BACK TO - Reduce health when crafting objects
        // Two types of objects -> Large & small, deteriorate health differently

        // if Objective(buildhouse - completed), tribals appear everyday at 00:00

        // COME BACK TO -- Fighting logic for tribals -
        // Obj: Collect
        // Eats egg from hen everyday
        // L2();
        // Build a House
        // Fight the corpse of Vikram Kumar
        // Befriend a Tribal
        // Optional(Befriend William)

        // L2();
    }

    public void L3() {
        // Portable toilet washes up on shore
        // Create raft using craftable class
        // rescued by cargo ship
    }

    @Override
    public void run() {
        intro();
        String input;
        String[] commands;
        while (true) {
            
            
                if (Objectives.fightWolf==false && Objectives.foundAxe == true && clock.inGameHours >= 5) {
                    Animal wolf1 = new Animal("Wolf", "white", "fur");
                    islandEast.currentlyPlacedAnimals.add(wolf1);
                   
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
                    wolf1.registerObserver(player);
                }
    
            UI.printNormal("Enter a command");
            input = UI.read();
            commands = input.split(" ");
            switch(commands[0]){
                case "look":
                    cp.buttonWasPressed(0,input);
                case "inspect":
                    cp.buttonWasPressed(1, input);
            }
        }

    }
}