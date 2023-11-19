package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Processor {

    private final List<PlayerAction> playerActionList;
    private final List<MatchData> matchDataList;

    public Processor(List<PlayerAction> playerActionList, List<MatchData> matchDataList) {
        this.playerActionList = playerActionList;
        this.matchDataList = matchDataList;
    }


    private Player playerDeposits(Player p, PlayerAction pa){
        p.setAccountBalance(p.getAccountBalance() + pa.getCoinAmount());

        return p;
    }
    private Player playerBets(Player p,PlayerAction pa){
        if (p.getAccountBalance() >= pa.getCoinAmount()) {
            for (MatchData match : this.matchDataList) {
                if (match.getId().equals(pa.getMatchId())) {


                    //LOSS
                    if (match.getWinSide() != pa.getBetSide()) {
                        p.setAccountBalance(p.getAccountBalance() - pa.getCoinAmount());
                        p.setPlayCount(p.getPlayCount() + 1);

                        p.setMoneyWon(p.getMoneyWon() - pa.getCoinAmount());

                        //WIN
                    } else if (match.getWinSide() == pa.getBetSide()) {

                        int moneyWon = (int) (pa.getCoinAmount() * match.getCoef(match.getWinSide()));
                        p.setAccountBalance(p.getAccountBalance() + moneyWon);
                        p.setPlayCount(p.getPlayCount() + 1);
                        p.setWinCount(p.getWinCount() + 1);

                        p.setMoneyWon(p.getMoneyWon() + pa.getCoinAmount());

                    }
                }
            }


        } else {
            p.setLegal(false);
            p.setIllegalAction(pa);
        }

        return p;
    }
    private Player playerWithdraws(Player p,PlayerAction pa){
        if (p.getAccountBalance() >= pa.getCoinAmount() && pa.getCoinAmount() > 0) {
            p.setAccountBalance(p.getAccountBalance() - pa.getCoinAmount());
        } else {
            p.setLegal(false);
            p.setIllegalAction(pa);
        }
        return p;
    }
    public List<Player> processLists(){

        List<Player> players = new ArrayList<>();
        int index;

        for (PlayerAction pa : this.playerActionList){
            index = -1;
            for (Player p : players) {
                index++;

                if (!p.isLegal()) {
                    continue;
                }


                if (p.getId().equals(pa.getId())){

                    if (pa.getAction() == PlayerAction.Action.DEPOSIT && pa.getCoinAmount() > 0){
                        players.set(index,playerDeposits(p,pa));

                    }else if (pa.getAction() == PlayerAction.Action.BET){
                        players.set(index,playerBets(p,pa));

                    }else if (pa.getAction() == PlayerAction.Action.WITHDRAW){
                        players.set(index,playerWithdraws(p,pa));
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

            if(playerExists) continue;

            Player p;
            if (pa.getAction() == PlayerAction.Action.DEPOSIT) {
                p = new Player(pa.getId(), pa.getCoinAmount(),0, 0, 0, true, null);
            } else{
                p = new Player(pa.getId(), 0, 0, 0, 0, false, pa);
            }

            players.add(p);
        }

        Collections.sort(players);

        return players;
    }
}
