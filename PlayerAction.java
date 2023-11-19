import java.util.UUID;

public class PlayerAction extends UUIDComparable {
    private UUID id;
    private int action;
    private UUID matchId;
    private int coinAmount;
    private char betSide;

    public PlayerAction(UUID id, int action, UUID matchId, int coinAmount, char betSide) {
        this.id = id;
        this.action = action;
        this.matchId = matchId;
        this.coinAmount = coinAmount;
        this.betSide = betSide;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID playerId) {
        this.id = playerId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public char getBetSide() {
        return betSide;
    }

    public void setBetSide(char betSide) {
        this.betSide = betSide;
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
