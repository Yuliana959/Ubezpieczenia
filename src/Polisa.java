import java.util.Objects;

public class Polisa {
    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;

    private static int liczbaUtworzonychPolis = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 50.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka,
                  double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonychPolis++;
    }

    // Gettery
    public String getNumerPolisy() { return numerPolisy; }
    public String getKlient() { return klient; }
    public double getSkladkaBazowa() { return skladkaBazowa; }
    public int getPoziomRyzyka() { return poziomRyzyka; }
    public double getWartoscPojazdu() { return wartoscPojazdu; }
    public boolean isCzyMaAlarm() { return czyMaAlarm; }
    public boolean isCzyBezszkodowyKlient() { return czyBezszkodowyKlient; }

    // Metody obliczeniowe
    public double obliczSkladkeKoncowa() {
        double skladka = skladkaBazowa + OPLATA_ADMINISTRACYJNA;
        skladka += poziomRyzyka * 120; // dopłata za ryzyko
        if (wartoscPojazdu > 60000) skladka += 150; // dopłata za drogi pojazd
        if (czyMaAlarm) skladka *= 0.95; // zniżka za alarm
        if (czyBezszkodowyKlient) skladka *= 0.92; // zniżka bezszkodowy
        return Math.max(skladka, skladkaBazowa); // minimalna wartość
    }

    public double obliczSkladkeOdnowieniowa() {
        double skladka = obliczSkladkeKoncowa();

        // zwiększenie w zależności od ryzyka
        if (poziomRyzyka == 4) skladka *= 1.10;
        if (poziomRyzyka >= 5) skladka *= 1.20;

        // dopłata dla wartości pojazdu
        if (wartoscPojazdu > 60000) skladka += 150;

        // zniżki
        if (czyBezszkodowyKlient) skladka *= 0.92;
        if (czyMaAlarm) skladka *= 0.95;

        // ograniczenia minimalne/maksymalne
        double min = obliczSkladkeKoncowa() * 0.9;
        double max = obliczSkladkeKoncowa() * 1.25;
        if (skladka < min) skladka = min;
        if (skladka > max) skladka = max;

        return Math.round(skladka * 100.0) / 100.0; // zaokrąglenie
    }

    public String pobierzPodsumowanieRyzyka() {
        return "Polisa " + numerPolisy + " klient: " + klient + ", ryzyko: " + poziomRyzyka;
    }

    // Metody statyczne
    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonychPolis;
    }

    // toString i equals
    @Override
    public String toString() {
        return "Polisa{" +
                "numerPolisy='" + numerPolisy + '\'' +
                ", klient='" + klient + '\'' +
                ", skladkaKoncowa=" + obliczSkladkeKoncowa() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Polisa)) return false;
        Polisa other = (Polisa) obj;
        return Objects.equals(numerPolisy, other.numerPolisy);
    }
}