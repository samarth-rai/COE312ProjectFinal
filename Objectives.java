public class Objectives extends AbstractObserver{
    


//Objectives are listed here

public static Boolean foundAxe = false;
public static Boolean discoverVikram = false;
public static Boolean fightWolf = false;

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

        }
   } 
}



}
