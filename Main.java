import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static List<Player> processLists(List<PlayerAction> playerActionList, List<MatchData> matchDataList) {



        List<Player> players = new ArrayList<>();


        for (PlayerAction pa : playerActionList) {
            //flag that tells whether to create a new instance of Player or not
            for (Player p : players) {
                //skip the Player if an illegal action has been done by Player
                if (!p.isLegal()) {
                    continue;
                }


                //find player from action
                if (p.getId().equals(pa.getId())) {
                    //aplayerExists = true;


                    //action is DEPOSIT
                    if (pa.getAction() == 0 && pa.getCoinAmount() > 0) {
                        p.setAccountBalance(p.getAccountBalance() + pa.getCoinAmount());


                        //action is BET
                    }else if (pa.getAction() == 1 && p.isLegal()) {
                        if (p.getAccountBalance() >= pa.getCoinAmount()) {
                            for (MatchData match : matchDataList) {
                                if (match.getId().equals(pa.getMatchId())) {

                                    //TIE
                                    if (match.getWinSide() == 'D') {

                                        //LOSS
                                    } else if (match.getWinSide() != pa.getBetSide()) {
                                        p.setAccountBalance(p.getAccountBalance() - pa.getCoinAmount());
                                        p.setPlayCount(p.getPlayCount() + 1);

                                        //houseProfit += pa.getCoinAmount();
                                        p.setMoneyWon(p.getMoneyWon() - pa.getCoinAmount());

                                        //WIN
                                    } else if (match.getWinSide() == pa.getBetSide()) {

                                        int moneyWon = (int) (pa.getCoinAmount() * match.getCoef(match.getWinSide()));
                                        p.setAccountBalance(p.getAccountBalance() + moneyWon);
                                        p.setPlayCount(p.getPlayCount() + 1);
                                        p.setWinCount(p.getWinCount() + 1);


                                        //houseProfit -= moneyWon;
                                        p.setMoneyWon(p.getMoneyWon() + pa.getCoinAmount());

                                    }

                                }
                            }


                        } else {//if player wants to bet more than his account

                            p.setLegal(false);
                            p.setIllegalAction(pa);
                        }

                        //action is WITHDRAW
                    } else if (pa.getAction() == 2) {
                        if (p.getAccountBalance() >= pa.getCoinAmount() && pa.getCoinAmount() > 0) {
                            p.setAccountBalance(p.getAccountBalance() - pa.getCoinAmount());
                        } else {
                            p.setLegal(false);
                            p.setIllegalAction(pa);
                        }
                    }
                }
            }

            boolean playerExists = false;


            for(Player p: players){
                if(p.getId().equals(pa.getId())){
                    playerExists = true;
                    break;
                }
            }

            if(playerExists){
                continue;
            }

            //add new Player to players List
            Player p;

            if (pa.getAction() == 0) {//legal user
                p = new Player(pa.getId(), pa.getCoinAmount(),0, 0, 0, true, null);
            } else{//illegal user
                p = new Player(pa.getId(), 0, 0, 0, 0, false, pa);
            }

            players.add(p);


        }

        Collections.sort(players);

        return players;

    }




    public static void main(String[] args) {

        String addressFirstHalf = "resource" + File.separator;

        List<PlayerAction> playerActionList = DataReader.readPlayerAction(addressFirstHalf + "player_data.txt");
        List<MatchData> matchDataList = DataReader.readMatchData(addressFirstHalf + "match_data.txt");

        List<Player> players = processLists(playerActionList,matchDataList);

        DataReader.writeToFile(addressFirstHalf+"failike.txt", players);

    }
}
