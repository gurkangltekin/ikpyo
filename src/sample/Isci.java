package sample;

import java.util.List;

public class Isci extends Personel{
    private List<Integer> gunlukUretim;
    private int aylikHedef;
    private int ToplamUretim;

    public List<Integer> getGunlukUretim() {
        return gunlukUretim;
    }

    public void setGunlukUretim(List<Integer> gunlukUretim) {
        this.gunlukUretim = gunlukUretim;
    }

    public int getAylikHedef() {
        return aylikHedef;
    }

    public void setAylikHedef(int aylikHedef) {
        this.aylikHedef = aylikHedef;
    }

    public int getToplamUretim() {
        return ToplamUretim;
    }

    public void setToplamUretim(int toplamUretim) {
        ToplamUretim = toplamUretim;
    }
}
