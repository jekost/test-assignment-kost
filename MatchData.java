import java.util.UUID;


public class MatchData extends UUIDComparable {
    private UUID id;
    private float coefA;
    private float coefB;
    private char winSide;

    public MatchData(UUID id, float coefA, float coefB, char winSide) {
        this.id = id;
        this.coefA = coefA;
        this.coefB = coefB;
        this.winSide = winSide;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getCoefA() {
        return coefA;
    }

    public float getCoefB() {
        return coefB;
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

    public void setCoefA(float coefA) {
        this.coefA = coefA;
    }

    public void setCoefB(float coefB) {
        this.coefB = coefB;
    }

    public char getWinSide() {
        return winSide;
    }

    public void setWinSide(char winSide) {
        this.winSide = winSide;
    }

}
