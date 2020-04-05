package fi.nukkujat;

import java.util.Objects;

import static java.lang.Character.toUpperCase;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class Toive {
    private Character vuoroTyyppi;
    private int vuoroRyhma;
    private Boolean priorisoitu;
    private Boolean totetunut;

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

    public Boolean getPriorisoitu() {
        return priorisoitu;
    }

    public void setPriorisoitu(Boolean priorisoitu) {
        this.priorisoitu = priorisoitu;
    }

    public Boolean getTotetunut() {
        return totetunut;
    }

    public void setTotetunut(Boolean totetunut) {
        this.totetunut = totetunut;
    }

    public Toive(Character vuoroTyyppi, int vuoroRyhma, Boolean priorisoitu, Boolean totetunut) {
        this.vuoroTyyppi = vuoroTyyppi;
        this.vuoroRyhma = vuoroRyhma;
        this.priorisoitu = priorisoitu;
        this.totetunut = totetunut;
    }

    public Boolean isFree() {
        return toLowerCase(this.vuoroTyyppi).equals("v");
    }

    private String vuoroRyhmaString() {
        if (this.vuoroRyhma == 0) {
            return " ";
        }else {
            return String.valueOf(this.vuoroRyhma);
        }
    }

    private String prioString() {
        if (!this.priorisoitu) {
            return " ";
        }else {
            return "p";
        }
    }

    private String toteutuString() {
        if (!this.totetunut) {
            return " ";
        }else {
            return "+";
        }
    }

    @Override
    public String toString() {
        return "" + toUpperCase(vuoroTyyppi)  + vuoroRyhmaString() + prioString() + toteutuString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toive)) return false;
        Toive toive = (Toive) o;
        return vuoroRyhma == toive.vuoroRyhma &&
                Objects.equals(vuoroTyyppi, toive.vuoroTyyppi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vuoroTyyppi, vuoroRyhma);
    }
}
