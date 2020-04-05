package fi.nukkujat;

import java.util.Objects;

import static java.lang.Character.toUpperCase;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class TyoVuoro {
    private Character vuoroTyyppi;
    private int vuoroRyhma;

    public Character getVuoroTyyppi() {
        return vuoroTyyppi;
    }

    public void setVuoroTyyppi(Character vuoroTyyppi) {
        this.vuoroTyyppi = vuoroTyyppi;
    }

    public int getVuoroRyhma() {
        return vuoroRyhma;
    }

    public void setVuoroRyhma(int vuoroRyhma) {
        this.vuoroRyhma = vuoroRyhma;
    }

    public TyoVuoro(Character vuoroTyyppi, int vuoroRyhma) {
        this.vuoroTyyppi = vuoroTyyppi;
        this.vuoroRyhma = vuoroRyhma;
    }

    public Boolean isFree() {
        return toLowerCase(this.vuoroTyyppi).equals("v");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TyoVuoro)) return false;
        TyoVuoro tyoVuoro = (TyoVuoro) o;
        return vuoroRyhma == tyoVuoro.vuoroRyhma &&
                Objects.equals(vuoroTyyppi, tyoVuoro.vuoroTyyppi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vuoroTyyppi, vuoroRyhma);
    }

    private String vuoroRyhmaString() {
        if (this.vuoroRyhma == 0) {
            return "";
        }else {
            return String.valueOf(this.vuoroRyhma);
        }
    }

    @Override
    public String toString() {
        return "" + toUpperCase(vuoroTyyppi)  + vuoroRyhmaString();
    }
}
