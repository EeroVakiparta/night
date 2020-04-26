package fi.nukkujat;

public enum ViivaType {

    NULL("null"),
    RIVI("rivi"),
    SARAKE("sarake");

    ViivaType() {
    }

    private String value;

    ViivaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ViivaType{" +
                "value='" + value + '\'' +
                '}';
    }
}
