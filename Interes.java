package predicate;

public class Interes {
    private Elev elev;
    private String domeniu;

    Interes(Elev elev, String domeniu) {
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
