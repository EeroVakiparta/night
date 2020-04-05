package fi.nukkujat;

import java.util.Objects;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class Toive {
    private String vuoroTyyppi;
    private int vuoroRyhma;
    private Boolean priorisoitu;

    public String getVuoroTyyppi() {
        return vuoroTyyppi;
    }

    public void setVuoroTyyppi(String vuoroTyyppi) {
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

    public Toive(String vuoroTyyppi, int vuoroRyhma, Boolean priorisoitu) {
        this.vuoroTyyppi = vuoroTyyppi;
        this.vuoroRyhma = vuoroRyhma;
        this.priorisoitu = priorisoitu;
    }

    public Boolean isFree() {
        return toLowerCase(this.vuoroTyyppi).equals("v");
    }

    @Override
    public String toString() {
        return "Toive{" +
                "vuoroTyyppi='" + vuoroTyyppi + '\'' +
                ", vuoroRyhma=" + vuoroRyhma +
                ", priorisoitu=" + priorisoitu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toive)) return false;
        Toive toive = (Toive) o;
        return vuoroRyhma == toive.vuoroRyhma &&
                Objects.equals(vuoroTyyppi, toive.vuoroTyyppi) &&
                Objects.equals(priorisoitu, toive.priorisoitu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vuoroTyyppi, vuoroRyhma, priorisoitu);
    }
}
