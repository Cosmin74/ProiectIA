package predicate;

public class Place {
    private Elev elev;
    private MateriePreferata materie;

    public Place(Elev elev, MateriePreferata materie) {
        this.elev = elev;
        this.materie = materie;
    }

    public void setMaterie(MateriePreferata materie) {
        this.materie = materie;
    }

    public MateriePreferata getMaterie() {
        return materie;
    }

    public Elev getElev() {
        return elev;
    }

    public void setElev(Elev elev) {
        this.elev = elev;
    }
}