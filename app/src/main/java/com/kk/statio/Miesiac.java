package com.kk.statio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Miesiac {
    private Integer nabity_przebieg;
    private BigDecimal koszty_paliwa;
    private Double zatankowane_paliwo;
    private YearMonth rok_miesiac;

    public Miesiac() {}

    public Miesiac(Integer nabity_przebieg, BigDecimal koszty_paliwa, Double zatankowane_paliwo, YearMonth rok_miesiac) {
        this.nabity_przebieg = nabity_przebieg;
        this.koszty_paliwa = koszty_paliwa;
        this.zatankowane_paliwo = zatankowane_paliwo;
        this.rok_miesiac = rok_miesiac;
    }

    public Miesiac(String[] str) {
        this.nabity_przebieg = Integer.parseInt(str[0]);
        this.koszty_paliwa = new BigDecimal(str[1]);
        this.rok_miesiac = YearMonth.parse(str[2]);
        this.zatankowane_paliwo = Double.parseDouble(str[3]);
    }

    @Override
    public String toString() {
        return "" + nabity_przebieg + ';'
                + koszty_paliwa + ';'
                + rok_miesiac + ';'
                + zatankowane_paliwo + '\r'+'\n';
    }

    public void dodajPrzebieg(Integer zmiana_przebiegu) {
        this.nabity_przebieg += zmiana_przebiegu;
    }

    public void dodajKosztyPaliwa(BigDecimal koszt_paliwa) {
        this.koszty_paliwa = this.koszty_paliwa.add(koszt_paliwa);
    }

    public void dodajZatankowanePaliwo(Double zatankowane_paliwo) {
        this.zatankowane_paliwo += zatankowane_paliwo;
    }

    public Integer getNabity_przebieg() {
        return nabity_przebieg;
    }

    public BigDecimal getKoszty_paliwa() {
        return koszty_paliwa;
    }

    public Double getZatankowane_paliwo() {
        return zatankowane_paliwo;
    }

    public YearMonth getRok_miesiac() {
        return rok_miesiac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Miesiac miesiac = (Miesiac) o;

        if (nabity_przebieg != null ? !nabity_przebieg.equals(miesiac.nabity_przebieg) : miesiac.nabity_przebieg != null)
            return false;
        if (koszty_paliwa != null ? !koszty_paliwa.equals(miesiac.koszty_paliwa) : miesiac.koszty_paliwa != null)
            return false;
        if (zatankowane_paliwo != null ? !zatankowane_paliwo.equals(miesiac.zatankowane_paliwo) : miesiac.zatankowane_paliwo != null)
            return false;
        return rok_miesiac != null ? rok_miesiac.equals(miesiac.rok_miesiac) : miesiac.rok_miesiac == null;
    }

    @Override
    public int hashCode() {
        int result = nabity_przebieg != null ? nabity_przebieg.hashCode() : 0;
        result = 31 * result + (koszty_paliwa != null ? koszty_paliwa.hashCode() : 0);
        result = 31 * result + (zatankowane_paliwo != null ? zatankowane_paliwo.hashCode() : 0);
        result = 31 * result + (rok_miesiac != null ? rok_miesiac.hashCode() : 0);
        return result;
    }
}
