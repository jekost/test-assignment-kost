import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class DataReader{

    public static List<MatchData> readMatchData(String address){

        List<MatchData> matchDataList = new ArrayList<>();

        File file = new File(address);
        if (file.exists() && file.canRead()) {
            try (Scanner scanner = new Scanner(file)) {

                while (scanner.hasNextLine()) {
                    String row = scanner.nextLine();
                    String[] data = row.split(",");

                    MatchData match = new MatchData(UUID.fromString(data[0]), Float.parseFloat(data[1]), Float.parseFloat(data[2]), data[3].charAt(0));
                    matchDataList.add(match);
                }

            } catch(Exception e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Can't access the text file.");
        }
        return matchDataList;
    }

    public static List<PlayerAction> readPlayerAction(String address){

        List<PlayerAction> playerDataList = new ArrayList<>();

        File file = new File(address);
        if (file.exists() && file.canRead()) {
            try (Scanner scanner = new Scanner(file)) {

                int actionInt;
                PlayerAction player;

                while (scanner.hasNextLine()) {
                    String row = scanner.nextLine();
                    if(row.equals("")){
                        continue;
                    }
                    String[] data = row.split(",");

                    switch (data[1]) {
                        case "DEPOSIT" -> {
                            actionInt = 0;
                            player = new PlayerAction(UUID.fromString(data[0]), actionInt, null, Integer.parseInt(data[3]), '0');
                            playerDataList.add(player);
                        }
                        case "BET" -> {
                            actionInt = 1;
                            player = new PlayerAction(UUID.fromString(data[0]), actionInt, UUID.fromString(data[2]), Integer.parseInt(data[3]), data[4].charAt(0));
                            playerDataList.add(player);
                        }
                        default -> {//"WITHDRAW"
                            actionInt = 2;
                            player = new PlayerAction(UUID.fromString(data[0]), actionInt, null, Integer.parseInt(data[3]), '0');
                            playerDataList.add(player);
                        }
                    }
                }

            } catch(Exception e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Can't access the text file.");
        }
        return playerDataList;
    }


    public static void writeToFile(String fileName, List<Player> players) {

        int houseProfit = 0;


        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            boolean legalPlayerExists = false;
            boolean illegalPlayerExists = false;



            //System.out.println("legal players:::");
            for (Player p : players) {
                if (p.isLegal()) {
                    //System.out.print(p.getId() + " ");
                    //System.out.print(p.getAccountBalance() + " ");
                    //System.out.println( (float)p.getWinCount() / (float)p.getPlayCount() );


                    printWriter.print(p.getId() + " ");
                    printWriter.print(p.getAccountBalance() + " ");
                    printWriter.println((float)p.getWinCount() / (float)p.getPlayCount());


                    houseProfit -= p.getMoneyWon();
                    legalPlayerExists = true;
                }
            }

            if(legalPlayerExists){
                printWriter.println("");
            }else{
                printWriter.println("\n");
            }


            System.out.println("illegal players:::");
            for (Player p : players) {
                if (!p.isLegal()) {

                    printWriter.print(p.getIllegalAction().getId() + " ");
                    printWriter.print(p.getIllegalAction().getActionString() + " ");
                    printWriter.print(p.getIllegalAction().getMatchId() + " ");
                    printWriter.print(p.getIllegalAction().getCoinAmount() + " ");

                    if(p.getIllegalAction().getBetSide() == '0'){
                        //System.out.println("null");
                        printWriter.println("null");
                    }else{
                        //System.out.println(p.getIllegalAction().getBetSide());
                        printWriter.println(p.getIllegalAction().getBetSide());
                    }

                    illegalPlayerExists = true;
                }
            }

            if(illegalPlayerExists){
                printWriter.println("");
            }else{
                printWriter.println("\n");
            }

            //System.out.println("house profit: " + houseProfit);
            printWriter.print(houseProfit);

            printWriter.close();
        }catch (Exception e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }


}
