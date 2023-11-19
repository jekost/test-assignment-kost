package src;

import java.io.File;
import java.util.List;

/*
/
/ Test assignment for Playtech Winternship 2024
/ author: Jan Erik Köst
/ email: jan.erik.kost@hotmail.com
/
*/

public class Main {


    public static void main(String[] args){

        String readFromFileFirstHalf = "resource" + File.separator;
        List<PlayerAction> playerActionList = IOStreamer.readPlayerAction(readFromFileFirstHalf + "player_data.txt");
        List<MatchData> matchDataList = IOStreamer.readMatchData(readFromFileFirstHalf + "match_data.txt");

        Processor processor = new Processor(playerActionList,matchDataList);
        List<Player> players = processor.processLists();

        String writeToFileFirstHalf = "src" + File.separator;
        IOStreamer.writeToFile(writeToFileFirstHalf + "results.txt", players);
    }
}
