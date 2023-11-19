package src;

import java.util.UUID;


public class MatchData extends UUIDComparable{
    private final UUID id;
    private final float coefA;
    private final float coefB;
    private final char winSide;

    public MatchData(UUID id, float coefA, float coefB, char winSide){
        this.id = id;
        this.coefA = coefA;
        this.coefB = coefB;
        this.winSide = winSide;
    }

    public UUID getId(){
        return id;
    }

    public float getCoef(char side){
        if(side == 'A'){
            return coefA;
        } else if (side == 'B') {
            return coefB;
        }else{
            return 1;
        }
    }

    public char getWinSide(){
        return winSide;
    }

}
