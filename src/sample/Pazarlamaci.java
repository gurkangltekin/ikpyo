package sample;

import java.util.List;

public class Pazarlamaci extends Personel {
    private List<Integer> gunlukSatis;
    private int aylikHedef;
    private int toplamSatis;

    public Pazarlamaci(int aylikHedef, int toplamSatis) {
        this.aylikHedef = aylikHedef;
        this.toplamSatis = toplamSatis;
    }

    public void gunlukSatisGuncelle(){

    }

    public void hedefGuncelle(){

    }

    public List<Integer> getGunlukSatis() {
        return gunlukSatis;
    }

    public void setGunlukSatis(List<Integer> gunlukSatis) {
        this.gunlukSatis = gunlukSatis;
    }

    public int getAylikHedef() {
        return aylikHedef;
    }

    public void setAylikHedef(int aylikHedef) {
        this.aylikHedef = aylikHedef;
    }

    public int getToplamSatis() {
        return toplamSatis;
    }

    public void setToplamSatis(int toplamSatis) {
        this.toplamSatis = toplamSatis;
    }
}
