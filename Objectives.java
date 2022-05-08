public class Objectives extends AbstractObserver{
    


//Objectives are listed here

public static Boolean foundAxe = false;
public static Boolean discoverVikram = false;
public static Boolean fightWolf = false;
public static Boolean foundWatch = false;
public static Boolean foundLVBag = false;
public static Boolean BuiltHouse = false;
public static Boolean MadeFire = false;

@Override
public void update(Message m) {
   if(m.topic=="Objective")
   {
        switch(m.payload)
        {
            case "foundAxe":
                foundAxe=true;
                break;
            case "discoverVikram":
                discoverVikram=true;
                break;
            case "fightWolf":
                fightWolf=true;
                break;
            case "foundWatch":
                foundWatch=true;
                break;
            case "foundLVBag":
                foundLVBag=true;
                break;
            case "BuiltHouse":
                BuiltHouse=true;
                break;
            case "MadeFire":
                MadeFire = true;
                break;

        }
   } 
}



}
