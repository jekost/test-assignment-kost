package src;

import java.io.File;
import java.util.List;

public class Main {


    public static void main(String[] args){

        String addressFirstHalf = "resource" + File.separator;

        List<PlayerAction> playerActionList = IOStreamer.readPlayerAction(addressFirstHalf + "player_data.txt");
        List<MatchData> matchDataList = IOStreamer.readMatchData(addressFirstHalf + "match_data.txt");

        Processor processor = new Processor(playerActionList,matchDataList);

        List<Player> players = processor.processLists();

        IOStreamer.writeToFile("src" + File.separator + "results.txt", players);
    }
}
