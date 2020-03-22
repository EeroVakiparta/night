package fi.nukkujat;

import java.util.List;

final class YokasittelynTulos {
    public List<Character> rimpsu;
    public int rimpsunSakot;

    public YokasittelynTulos(List<Character> rimpsu, int rimpsunSakot) {
        this.rimpsu = rimpsu;
        this.rimpsunSakot = rimpsunSakot;
    }

    public List<Character> getRimpsu() {
        return rimpsu;
    }

    public int getRimpsunSakot() {
        return rimpsunSakot;
    }

    @Override
    public String toString() {
        return "YokasittelynTulos {" +
                "rimpsu =" + rimpsu +
                ", rimpsunSakot =" + rimpsunSakot +
                '}';
    }
}
