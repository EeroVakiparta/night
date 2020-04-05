package fi.nukkujat;

import java.util.List;

final class ToiveidenTulos {
    public List<Toive> toiveet;
    public int toiveSakot;
    public int prioriteettejaRikottu;

    public ToiveidenTulos(List<Toive> toiveet, int toiveSakot, int prioriteettejaRikottu) {
        this.toiveet = toiveet;
        this.toiveSakot = toiveSakot;
        this.prioriteettejaRikottu = prioriteettejaRikottu;
    }

    @Override
    public String toString() {
        return "Toiveet= " + toiveet +
                "\nToivesakot: " + toiveSakot +
                " \nPrioriteetteja rikottu: " + prioriteettejaRikottu;

    }
}
