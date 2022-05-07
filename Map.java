public class Map {
    private static Map instance;
    public Map() {
    }

    public static synchronized Map getInstance(){
		if(instance == null){
		instance = new Map();
		}
		return instance;
	}
    public void showmap(Location currentLocation){
        if(currentLocation.name.equalsIgnoreCase("Island North"))
        {
            UI.printNormal("\n                                                                                "+
            "\n                                                                                "+
            "\n                                                    ███████                     "+
            "\n    ████            ███                        █████       ██                   "+
            "\n    █  █ █       ████ ██                    ███             ██                  "+
            "\n    █  █████  ███       ██               ████                ██                 "+
            "\n    █      ███           ████████████████                     █                 "+
            "\n    █                                                          █                "+
            "\n    ██                     Island North                        █                "+
            "\n     █                                                         █                "+
            "\n     ██                      (X)  <- You are here               ██               "+
            "\n      █                                                         ███             "+
            "\n      ██                                                          ████          "+
            "\n       ██                                                            ██         "+
            "\n        █                                                             ██        "+
            "\n        █                                                              ██       "+
            "\n         █                                                              █       "+
            "\n         █ Island East                                    Island West    █      "+
            "\n         █                                                               █      "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n        █                                                                 ██    "+
            "\n       ██                                                                 █     "+
            "\n       █                                                                ██      "+
            "\n      ██                                                               ██       "+
            "\n      █                                                              ███        "+
            "\n      █                        Island South                        ███          "+
            "\n      ██                                                         ███            "+
            "\n       ██                             ██████                 ████               "+
            "\n         ██                   █████████    ██████████████████                   "+
            "\n           █████         ██████                                                 "+
            "\n                █████████                                                       "+
            "\n                                                                                ");
        }
        else if(currentLocation.name.equalsIgnoreCase("Island East"))
        {
            UI.printNormal("\n                                                                                "+
            "\n                                                                                "+
            "\n                                                    ███████                     "+
            "\n    ████            ███                        █████       ██                   "+
            "\n    █  █ █       ████ ██                    ███             ██                  "+
            "\n    █  █████  ███       ██               ████                ██                 "+
            "\n    █      ███           ████████████████                     █                 "+
            "\n    █                                                          █                "+
            "\n    ██                     Island North                        █                "+
            "\n     █                                                         █                "+
            "\n     ██                                                         ██               "+
            "\n      █                                                         ███             "+
            "\n      ██                                                          ████          "+
            "\n       ██                                                            ██         "+
            "\n        █                                                             ██        "+
            "\n        █                                                              ██       "+
            "\n         █                                                              █       "+
            "\n         █ Island East                                    Island West    █      "+
            "\n         █  (X)  <- You are here                                          █      "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n        █                                                                 ██    "+
            "\n       ██                                                                 █     "+
            "\n       █                                                                ██      "+
            "\n      ██                                                               ██       "+
            "\n      █                                                              ███        "+
            "\n      █                        Island South                        ███          "+
            "\n      ██                                                         ███            "+
            "\n       ██                             ██████                 ████               "+
            "\n         ██                   █████████    ██████████████████                   "+
            "\n           █████         ██████                                                 "+
            "\n                █████████                                                       "+
            "\n                                                                                ");
        }

        else if(currentLocation.name.equalsIgnoreCase("Island West"))
        {
            UI.printNormal("\n                                                                                "+
            "\n                                                                                "+
            "\n                                                    ███████                     "+
            "\n    ████            ███                        █████       ██                   "+
            "\n    █  █ █       ████ ██                    ███             ██                  "+
            "\n    █  █████  ███       ██               ████                ██                 "+
            "\n    █      ███           ████████████████                     █                 "+
            "\n    █                                                          █                "+
            "\n    ██                     Island North                        █                "+
            "\n     █                                                         █                "+
            "\n     ██                                                         ██               "+
            "\n      █                                                         ███             "+
            "\n      ██                                                          ████          "+
            "\n       ██                                                            ██         "+
            "\n        █                                                             ██        "+
            "\n        █                                                              ██       "+
            "\n         █                                                              █       "+
            "\n         █ Island East                                    Island West    █      "+
            "\n         █                                      You are here ->  (X)      █      "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n        █                                                                 ██    "+
            "\n       ██                                                                 █     "+
            "\n       █                                                                ██      "+
            "\n      ██                                                               ██       "+
            "\n      █                                                              ███        "+
            "\n      █                        Island South                        ███          "+
            "\n      ██                                                         ███            "+
            "\n       ██                             ██████                 ████               "+
            "\n         ██                   █████████    ██████████████████                   "+
            "\n           █████         ██████                                                 "+
            "\n                █████████                                                       "+
            "\n                                                                                ");
        }

        else if(currentLocation.name.equalsIgnoreCase("Island South"))
        {
            UI.printNormal("\n                                                                                "+
            "\n                                                                                "+
            "\n                                                    ███████                     "+
            "\n    ████            ███                        █████       ██                   "+
            "\n    █  █ █       ████ ██                    ███             ██                  "+
            "\n    █  █████  ███       ██               ████                ██                 "+
            "\n    █      ███           ████████████████                     █                 "+
            "\n    █                                                          █                "+
            "\n    ██                     Island North                        █                "+
            "\n     █                                                         █                "+
            "\n     ██                                                         ██               "+
            "\n      █                                                         ███             "+
            "\n      ██                                                          ████          "+
            "\n       ██                                                            ██         "+
            "\n        █                                                             ██        "+
            "\n        █                                                              ██       "+
            "\n         █                                                              █       "+
            "\n         █ Island East                                    Island West    █      "+
            "\n         █                                                               █      "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n         █                                                                █     "+
            "\n        █                                                                 ██    "+
            "\n       ██                                                                 █     "+
            "\n       █                                                                ██      "+
            "\n      ██                                                               ██       "+
            "\n      █                                                              ███        "+
            "\n      █                  You are here ->  (X)                       ███        "+
            "\n      █                        Island South                        ███          "+
            "\n      ██                                                         ███            "+
            "\n       ██                             ██████                 ████               "+
            "\n         ██                   █████████    ██████████████████                   "+
            "\n           █████         ██████                                                 "+
            "\n                █████████                                                       "+
            "\n                                                                                ");
        }
       
    }
}
