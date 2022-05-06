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
    public void showmap(){
        //ideally i wanna pass something as a parameter to this function which will then determine what names of the ocations we have to show on the map.
        //still need to add the names of the places in this map.
        UI.print("\n                                                                                "+
        "\n                                                                                "+
        "\n                                                    ███████                     "+
        "\n    ████            ███                        █████       ██                   "+
        "\n    █  █ █       ████ ██                    ███             ██                  "+
        "\n    █  █████  ███       ██               ████                ██                 "+
        "\n    █      ███           ████████████████                     █                 "+
        "\n    █                                                          █                "+
        "\n    ██                   North Island                          █                "+
        "\n     █                                                         █                "+
        "\n     ██                                                        ██               "+
        "\n      █                                                         ███             "+
        "\n      ██                                                          ████          "+
        "\n       ██                                                            ██         "+
        "\n        █                                                             ██        "+
        "\n        █                                                              ██       "+
        "\n         █                                                              █       "+
        "\n         █                                                               █      "+
        "\n         █                                                               █      "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n        █                                                                 ██    "+
        "\n       ██                                                                 █     "+
        "\n       █                                                                ██      "+
        "\n      ██                                                               ██       "+
        "\n      █                                                              ███        "+
        "\n      █                                                            ███          "+
        "\n      ██                                                         ███            "+
        "\n       ██                             ██████                 ████               "+
        "\n         ██                   █████████    ██████████████████                   "+
        "\n           █████         ██████                                                 "+
        "\n                █████████                                                       "+
        "\n                                                                                ");
    }
}
