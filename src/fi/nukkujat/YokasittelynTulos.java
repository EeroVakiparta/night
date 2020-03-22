package fi.nukkujat;

import java.util.List;

final class YokasittelynTulos {
    public List<Character> yoPutki;
    public int yoPutkenSakot;

    public YokasittelynTulos(List<Character> yoPutki, int yoPutkenSakot) {
        this.yoPutki = yoPutki;
        this.yoPutkenSakot = yoPutkenSakot;
    }

    public List<Character> getYoPutki() {
        return yoPutki;
    }

    public int getYoPutkenSakot() {
        return yoPutkenSakot;
    }

    @Override
    public String toString() {
        return "YokasittelynTulos= " +
                "putki: " + yoPutki +
                " putkenSakot: " + yoPutkenSakot ;

    }
}
