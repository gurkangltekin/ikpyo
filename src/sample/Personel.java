package sample;

import java.sql.Timestamp;
import java.util.*;

public class Personel {

    private String adi;
    private String soyadi;
    private Long TCKimlik;
    private Date iseGiris;
    private String departman;
    private int maas;
    private List<Timestamp> girisSaati;
    private List<Timestamp> cikisSaati;

    public void maasGuncelle(int maas){
        this.setMaas(maas);
    }

    public void personelGirdi(Timestamp girisSaati){
        this.getGirisSaati().add(girisSaati);
    }

    public void personelCikti(Timestamp cikisSaati){
        this.getCikisSaati().add(cikisSaati);
    }

    public String getAdi() {
        if(this.adi == null)
            this.adi = "Adı Belirtilmemiş!";
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        if(this.soyadi == null)
            this.soyadi = "Soyadı Belirtilmemiş!";
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Long getTCKimlik() {
        if(this.TCKimlik == 0)
            this.TCKimlik = 1000000000L;
        return TCKimlik;
    }

    public void setTCKimlik(Long TCKimlik) {
        this.TCKimlik = TCKimlik;
    }

    public Date getIseGiris() {
        if(this.iseGiris == null)
            this.iseGiris = new Date();
        return iseGiris;
    }

    public void setIseGiris(Date iseGiris) {
        this.iseGiris = iseGiris;
    }

    public String getDepartman() {
        if(this.departman == null)
            this.departman = "Departman Belirtilmemiş!";
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public int getMaas() {
        if(this.maas == 0)
            this.maas = 2350;
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    public List<Timestamp> getGirisSaati() {
        if(this.girisSaati == null)
            this.girisSaati = new ArrayList<Timestamp>();
        return girisSaati;
    }

    public void setGirisSaati(List<Timestamp> girisSaati) {
        this.girisSaati = girisSaati;
    }

    public List<Timestamp> getCikisSaati() {
        if(this.cikisSaati == null)
            this.cikisSaati = new ArrayList<Timestamp>();
        return cikisSaati;
    }

    public void setCikisSaati(List<Timestamp> cikisSaati) {
        this.cikisSaati = cikisSaati;
    }
}
