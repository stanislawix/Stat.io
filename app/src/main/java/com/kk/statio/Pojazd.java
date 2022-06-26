package com.kk.statio;

public class Pojazd {

    private Integer id_pojazdu;
    private String wyswietlana_nazwa;
    private String marka;
    private String model;
    private String nr_rejestracyjny;
    private Integer przebieg_km;
    private Integer rok_produkcji;
    private Double pojemnosc_baku;
    private Double stan_baku;

    public Pojazd() {}

    public Pojazd(Integer id_pojazdu, String wyswietlana_nazwa, String marka, String model, String nr_rejestracyjny, Integer przebieg_km, Integer rok_produkcji, Double pojemnosc_baku) {
        this.id_pojazdu = id_pojazdu;
        this.wyswietlana_nazwa = wyswietlana_nazwa;
        this.marka = marka;
        this.model = model;
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.przebieg_km = przebieg_km;
        this.rok_produkcji = rok_produkcji;
        this.pojemnosc_baku = pojemnosc_baku;
        this.stan_baku = pojemnosc_baku * 0.5;
    }

    public Pojazd(String[] pojazdString) {
        this.id_pojazdu = Integer.parseInt(pojazdString[0]);
        this.wyswietlana_nazwa = pojazdString[1];
        this.marka = pojazdString[2];
        this.model = pojazdString[3];
        this.nr_rejestracyjny = pojazdString[4];
        this.przebieg_km = Integer.parseInt(pojazdString[5]);
        this.rok_produkcji = Integer.parseInt(pojazdString[6]);
        this.pojemnosc_baku = Double.parseDouble(pojazdString[7]);
        this.stan_baku = Double.parseDouble(pojazdString[8]);
    }

    @Override
    public String toString() {
        return "" + id_pojazdu + ';'
                + wyswietlana_nazwa + ';'
                + marka + ';'
                + model + ';'
                + nr_rejestracyjny + ';'
                + przebieg_km + ';'
                + rok_produkcji + ';'
                + pojemnosc_baku + ';'
                + stan_baku + '\r'+'\n';
    }

    public Integer dodajPrzebieg(Integer dodawany_przebieg) {
        this.przebieg_km += dodawany_przebieg;
        return this.przebieg_km;
    }

    public String getWyswietlana_nazwa() {
        return wyswietlana_nazwa;
    }

    public Integer getPrzebieg_km() {
        return przebieg_km;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pojazd pojazd = (Pojazd) o;

        if (id_pojazdu != null ? !id_pojazdu.equals(pojazd.id_pojazdu) : pojazd.id_pojazdu != null) return false;
        if (wyswietlana_nazwa != null ? !wyswietlana_nazwa.equals(pojazd.wyswietlana_nazwa) : pojazd.wyswietlana_nazwa != null)
            return false;
        if (marka != null ? !marka.equals(pojazd.marka) : pojazd.marka != null) return false;
        if (model != null ? !model.equals(pojazd.model) : pojazd.model != null) return false;
        if (nr_rejestracyjny != null ? !nr_rejestracyjny.equals(pojazd.nr_rejestracyjny) : pojazd.nr_rejestracyjny != null)
            return false;
        if (przebieg_km != null ? !przebieg_km.equals(pojazd.przebieg_km) : pojazd.przebieg_km != null) return false;
        if (rok_produkcji != null ? !rok_produkcji.equals(pojazd.rok_produkcji) : pojazd.rok_produkcji != null)
            return false;
        if (pojemnosc_baku != null ? !pojemnosc_baku.equals(pojazd.pojemnosc_baku) : pojazd.pojemnosc_baku != null)
            return false;
        return stan_baku != null ? stan_baku.equals(pojazd.stan_baku) : pojazd.stan_baku == null;
    }

    @Override
    public int hashCode() {
        int result = id_pojazdu != null ? id_pojazdu.hashCode() : 0;
        result = 31 * result + (wyswietlana_nazwa != null ? wyswietlana_nazwa.hashCode() : 0);
        result = 31 * result + (marka != null ? marka.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (nr_rejestracyjny != null ? nr_rejestracyjny.hashCode() : 0);
        result = 31 * result + (przebieg_km != null ? przebieg_km.hashCode() : 0);
        result = 31 * result + (rok_produkcji != null ? rok_produkcji.hashCode() : 0);
        result = 31 * result + (pojemnosc_baku != null ? pojemnosc_baku.hashCode() : 0);
        result = 31 * result + (stan_baku != null ? stan_baku.hashCode() : 0);
        return result;
    }
}
