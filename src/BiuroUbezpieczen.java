import java.util.ArrayList;

public class BiuroUbezpieczen {
    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa) {
        this.nazwa = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolise(Polisa polisa) {
        polisy.add(polisa);
    }

    public void wypiszRaport() {
        System.out.println("Raport biura: " + nazwa);
        for (Polisa p : polisy) {
            System.out.println(p);
        }
    }

    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();
        }
        return suma;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeOdnowieniowa();
        }
        return suma;
    }

    public int policzPolisyWysokiegoRyzyka() {
        int count = 0;
        for (Polisa p : polisy) {
            if (p.getPoziomRyzyka() >= 4) count++;
        }
        return count;
    }

    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numerPolisy)) return p;
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog) {
        System.out.println("Polisy z skladka < " + prog);
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) System.out.println(p);
        }
    }
}
