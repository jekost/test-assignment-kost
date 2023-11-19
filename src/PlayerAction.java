package src;

import java.util.UUID;

public class PlayerAction extends UUIDComparable{
    private final UUID id;
    private final int action;
    private final UUID matchId;
    private final int coinAmount;
    private final char betSide;

    public PlayerAction(UUID id, int action, UUID matchId, int coinAmount, char betSide){
        this.id = id;
        this.action = action;
        this.matchId = matchId;
        this.coinAmount = coinAmount;
        this.betSide = betSide;
    }


    public UUID getId(){
        return id;
    }
    public int getAction(){
        return action;
    }
    public UUID getMatchId(){
        return matchId;
    }
    public int getCoinAmount(){
        return coinAmount;
    }
    public char getBetSide(){
        return betSide;
    }
    public String getActionString(){
        if(getAction() == 0){
            return "DEPOSIT";

        }else if(getAction() == 1){
            return "BET";

        }else if(getAction() == 2){
            return "WITHDRAW";
        }
        return "action not found";
    }
}
