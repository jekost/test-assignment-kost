package src;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class IOStreamer {

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
        if (file.exists() && file.canRead()){
            try (Scanner scanner = new Scanner(file)){

                PlayerAction player;

                while (scanner.hasNextLine()){
                    String row = scanner.nextLine();
                    if(row.equals("")){
                        continue;
                    }
                    String[] data = row.split(",");

                    switch (data[1]) {
                        case "DEPOSIT" -> {
                            player = new PlayerAction(UUID.fromString(data[0]), PlayerAction.Action.DEPOSIT, null, Integer.parseInt(data[3]), '0');
                            playerDataList.add(player);
                        }
                        case "BET" -> {
                            player = new PlayerAction(UUID.fromString(data[0]), PlayerAction.Action.BET, UUID.fromString(data[2]), Integer.parseInt(data[3]), data[4].charAt(0));
                            playerDataList.add(player);
                        }
                        default -> {//"WITHDRAW"
                            player = new PlayerAction(UUID.fromString(data[0]), PlayerAction.Action.WITHDRAW, null, Integer.parseInt(data[3]), '0');
                            playerDataList.add(player);
                        }
                    }
                }

            }catch(Exception e){
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Can't access the text file.");
        }
        return playerDataList;
    }


    public static void writeToFile(String fileName, List<Player> players){

        int houseProfit = 0;


        try{
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            boolean legalPlayerExists = false;
            boolean illegalPlayerExists = false;


            for (Player p : players){
                if (p.isLegal()){

                    printWriter.print(p.getId() + " " +
                            p.getAccountBalance() + " ");


                    BigDecimal winRatio = BigDecimal.valueOf(0);

                    if(p.getPlayCount() > 0) {
                        BigDecimal bd1 = new BigDecimal(p.getWinCount());
                        BigDecimal bd2 = new BigDecimal(p.getPlayCount());
                        winRatio = bd1.divide(bd2, 2, RoundingMode.HALF_UP);
                    }

                    printWriter.println(winRatio);

                    houseProfit -= p.getMoneyWon();
                    legalPlayerExists = true;
                }
            }

            if(legalPlayerExists){
                printWriter.println("");
            }else{
                printWriter.println("\n");
            }


            for (Player p : players){
                if (!p.isLegal()){

                    printWriter.print(p.getIllegalAction().getId() + " " +
                            p.getIllegalAction().getAction() + " " +
                            p.getIllegalAction().getMatchId() + " " +
                            p.getIllegalAction().getCoinAmount() + " "
                    );

                    if(p.getIllegalAction().getBetSide() == '0'){
                        printWriter.println("null");
                    }else{
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

            printWriter.print(houseProfit);

            printWriter.close();
        }catch (Exception e){
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }


}
