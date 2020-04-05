package fi.nukkujat;

import java.util.Objects;

public class Toive {
    private String vuoro;
    private Boolean priorisoitu;

    public String getVuoro() {
        return vuoro;
    }

    public void setVuoro(String vuoro) {
        this.vuoro = vuoro;
    }

    public Boolean getPriorisoitu() {
        return priorisoitu;
    }

    public void setPriorisoitu(Boolean priorisoitu) {
        this.priorisoitu = priorisoitu;
    }

    public Toive(String vuoro, Boolean priorisoitu) {
        this.vuoro = vuoro;
        this.priorisoitu = priorisoitu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toive)) return false;
        Toive toive = (Toive) o;
        return vuoro.equals(toive.vuoro) &&
                priorisoitu.equals(toive.priorisoitu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vuoro, priorisoitu);
    }

    @Override
    public String toString() {
        return "Toive{" +
                "vuoro='" + vuoro + '\'' +
                ", priorisoitu=" + priorisoitu +
                '}';
    }
}
