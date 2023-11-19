package src;

import java.util.UUID;

public class Player extends UUIDComparable {
    private final UUID id;
    private long accountBalance;
    private int moneyWon;
    private int playCount;
    private int winCount;
    private boolean isLegal;

    private PlayerAction illegalAction;

    public Player(UUID id, long accountBalance, int moneyWon, int playCount, int winCount, boolean isLegal, PlayerAction illegalAction){
        this.id = id;
        this.accountBalance = accountBalance;
        this.moneyWon = moneyWon;
        this.playCount = playCount;
        this.winCount = winCount;
        this.isLegal = isLegal;
        this.illegalAction = illegalAction;
    }


    public UUID getId(){
        return id;
    }

    public long getAccountBalance(){
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance){
        this.accountBalance = accountBalance;
    }


    public int getMoneyWon(){
        return moneyWon;
    }

    public void setMoneyWon(int moneyWon){
        this.moneyWon = moneyWon;
    }

    public int getPlayCount(){
        return playCount;
    }

    public void setPlayCount(int playCount){
        this.playCount = playCount;
    }

    public int getWinCount(){
        return winCount;
    }

    public void setWinCount(int winCount){
        this.winCount = winCount;
    }

    public boolean isLegal(){
        return isLegal;
    }

    public void setLegal(boolean isLegal){
        this.isLegal = isLegal;
    }

    public PlayerAction getIllegalAction(){
        return illegalAction;
    }

    public void setIllegalAction(PlayerAction illegalAction){
        this.illegalAction = illegalAction;
    }
}
