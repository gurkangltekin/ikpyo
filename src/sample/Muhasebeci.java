package sample;

public class Muhasebeci extends Personel {
    private int ZimmetEdilenMiktar;
    private int odeyecegiMiktar;
    private int geriGetirilenTutar;

    public void zimmetGuncelle(){

    }

    public int getZimmetEdilenMiktar() {
        return ZimmetEdilenMiktar;
    }

    public void setZimmetEdilenMiktar(int zimmetEdilenMiktar) {
        ZimmetEdilenMiktar = zimmetEdilenMiktar;
    }

    public int getOdeyecegiMiktar() {
        return odeyecegiMiktar;
    }

    public void setOdeyecegiMiktar(int odeyecegiMiktar) {
        this.odeyecegiMiktar = odeyecegiMiktar;
    }

    public int getGeriGetirilenTutar() {
        return geriGetirilenTutar;
    }

    public void setGeriGetirilenTutar(int geriGetirilenTutar) {
        this.geriGetirilenTutar = geriGetirilenTutar;
    }
}
