public class Main {
    public static void main(String[] args) {
        BiuroUbezpieczen biuro = new BiuroUbezpieczen("Secure Future");

        Polisa p1 = new Polisa("CAR-101", "Anna Nowak", 900.0, 3, 72000.0, true, true);
        Polisa p2 = new Polisa("CAR-102", "Piotr Lis", 840.0, 4, 54000.0, false, false);
        Polisa p3 = new Polisa("CAR-103", "Karolina Maj", 780.0, 2, 46000.0, true, false);

        biuro.dodajPolise(p1);
        biuro.dodajPolise(p2);
        biuro.dodajPolise(p3);

        biuro.wypiszRaport();
        System.out.println("Laczna skladka: " + biuro.policzLacznaSkladke());
        System.out.println("Laczna prognoza odnowien: " + biuro.policzLacznaPrognozeOdnowien());
        System.out.println("Polisy wysokiego ryzyka: " + biuro.policzPolisyWysokiegoRyzyka());
        System.out.println("Liczba utworzonych polis: " + Polisa.pobierzLiczbeUtworzonychPolis());

        System.out.println(p1.pobierzPodsumowanieRyzyka());
        System.out.println("Prognoza odnowienia dla p1: " + p1.obliczSkladkeOdnowieniowa());
        System.out.println(p1.equals(new Polisa("CAR-101", "Inny klient", 700.0, 1, 30000.0, false, false)));
        System.out.println(p2);

        Polisa znaleziona = biuro.znajdzPoNumerze("CAR-102");
        System.out.println("Znaleziono: " + znaleziona);
    }
}