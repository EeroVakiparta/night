package fi.nukkujat;

//NOTE: this should not be confused with any illegal substance
public class Viiva {
    int lineIndex;
    ViivaType viivaType;

    Viiva(int lineIndex, ViivaType riviType) {
        this.lineIndex = lineIndex;
        this.viivaType = riviType;
    }

    ViivaType getLineType() {
        return viivaType;
    }

    int getLineIndex() {
        return lineIndex;
    }

    boolean isHorizontal() {
        return viivaType == ViivaType.RIVI;
    }

    @Override
    public String toString() {
        return "Viiva{" +
                "lineIndex=" + lineIndex +
                ", viivaType=" + viivaType +
                '}';
    }
}
