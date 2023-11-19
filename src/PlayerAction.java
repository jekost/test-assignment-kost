package src;

import java.util.UUID;

public class PlayerAction extends UUIDComparable{

    public enum Action{
        DEPOSIT,
        BET,
        WITHDRAW
    }
    private final UUID id;
    private final PlayerAction.Action action;
    private final UUID matchId;
    private final int coinAmount;
    private final char betSide;

    public PlayerAction(UUID id, Action action, UUID matchId, int coinAmount, char betSide){
        this.id = id;
        this.action = action;
        this.matchId = matchId;
        this.coinAmount = coinAmount;
        this.betSide = betSide;
    }

    public UUID getId(){
        return id;
    }
    public Action getAction(){
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

}
