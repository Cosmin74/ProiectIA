package predicate;

public class Pasionat {
    private Elev elev;
    private String domeniu;

    public Pasionat(Elev elev, String domeniu) {
        this.elev = elev;
        this.domeniu = domeniu;
    }

    public void setElev(Elev elev) {
        this.elev = elev;
    }

    public Elev getElev() {
        return elev;
    }

    public void setDomeniu(String domeniu) {
        this.domeniu = domeniu;
    }

    public String getDomeniu() {
        return domeniu;
    }
}