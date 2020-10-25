package sk.jaro.fiktivnyprogram.entity;

import sk.jaro.simpleormframework.anotacie.Id;
import sk.jaro.simpleormframework.anotacie.Stlpec;
import sk.jaro.simpleormframework.anotacie.Tabulka;

@Tabulka("OSOBA")
public class Osoba {
    @Id
    @Stlpec("ID")
    private Long id;

    @Stlpec("MENO")
    private String meno;

    @Stlpec("PRIEZVISKO")
    private String priezvsiko;

    private Integer vek;

    private String adresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvsiko() {
        return priezvsiko;
    }

    public void setPriezvsiko(String priezvsiko) {
        this.priezvsiko = priezvsiko;
    }

    public Integer getVek() {
        return vek;
    }

    public void setVek(Integer vek) {
        this.vek = vek;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}